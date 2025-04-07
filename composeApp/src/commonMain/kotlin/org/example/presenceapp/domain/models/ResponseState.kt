package org.example.presenceapp.domain.models

sealed class ResponseState{
    class Error(val error: String): ResponseState()
    class Success<T>(val data: T): ResponseState()
}

