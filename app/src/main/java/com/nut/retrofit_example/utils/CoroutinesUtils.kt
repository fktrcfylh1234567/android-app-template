package com.nut.retrofit_example.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> io(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.IO, block)
