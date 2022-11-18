package com.hermawan.compose.moviedb.utils

sealed class ResultData<out T : Any?>(val data: T? = null, val message: String? = null) {
    class Default<T>() : ResultData<T>()
    class Loading<T>(data: T? = null) : ResultData<T>(data)
    class Empty<T>() : ResultData<T>()
    class Success<T>(data: T?) : ResultData<T>(data)
    class Error<T>(message: String?, data: T? = null) : ResultData<T>(data, message)
}
