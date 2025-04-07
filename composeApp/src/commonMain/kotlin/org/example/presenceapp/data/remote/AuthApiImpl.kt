package org.example.presenceapp.data.remote

import org.example.presenceapp.data.remote.api.AuthApi
import org.example.presenceapp.data.remote.dto.auth.AuthRequestDto
import org.example.presenceapp.data.remote.dto.auth.AuthResponseDto

class AuthApiImpl(private val authApi: AuthApi) {
    suspend fun login(requestDto: AuthRequestDto): AuthResponseDto = authApi.login(requestDto)
}