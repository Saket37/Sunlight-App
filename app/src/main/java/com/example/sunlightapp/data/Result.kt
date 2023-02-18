package com.example.sunlightapp.data

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}