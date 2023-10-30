package com.example.recipes.utils

sealed class Response<T>(
    val data : T? = null,
    val error : String? = null
){
    class Success<T> (data : T) : Response<T>(data = data)
    class Failure<T> (error : String) : Response<T>(error = error)
}