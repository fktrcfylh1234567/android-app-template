package com.nut.retrofit_example.models

data class HttpBinGetResponse(val origin: String, val url: String, val args: HttpBinGetArgs)

data class HttpBinGetArgs(val userId: String)
