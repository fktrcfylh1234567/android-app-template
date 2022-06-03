package com.nut.retrofit_example.utils

import okhttp3.Request
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Тип для резулятатов запроса к RestAPI
 *
 * Может принимать значения 3 видов: Success, Failure и NetworkError
 *
 */
sealed class ResultApi<out T> {
    data class Success<T>(val data: T) : ResultApi<T>()
    data class Failure(val statusCode: Int?) : ResultApi<Nothing>()
    object NetworkError : ResultApi<Nothing>()
}

internal abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    final override fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    final override fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
}

internal class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, ResultApi<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<ResultApi<T>>) = proxy.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val code = response.code()

            val result = if (code in 200 until 300) {
                response.body()?.let { ResultApi.Success(it) } ?: ResultApi.Failure(code)
            } else {
                ResultApi.Failure(code)
            }

            callback.onResponse(this@ResultCall, Response.success(result))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val result = if (t is IOException) {
                ResultApi.NetworkError
            } else {
                ResultApi.Failure(null)
            }

            callback.onResponse(this@ResultCall, Response.success(result))
        }
    })

    override fun cloneImpl() = ResultCall(proxy.clone())
}

internal class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<ResultApi<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<ResultApi<Type>> = ResultCall(call)
}

internal class ResultCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ) = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                ResultApi::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    ResultAdapter(resultType)
                }
                else -> null
            }
        }
        else -> null
    }
}
