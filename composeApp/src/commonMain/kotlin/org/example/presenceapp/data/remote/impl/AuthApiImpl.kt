package org.example.presenceapp.data.remote.impl

import org.example.presenceapp.data.remote.api.AuthApi
import org.example.presenceapp.data.model.dto.auth.AuthRequestDto
import org.example.presenceapp.data.model.dto.auth.AuthResponseDto

class AuthApiImpl(private val authApi: AuthApi) {
    suspend fun login(requestDto: AuthRequestDto): AuthResponseDto = authApi.login(requestDto)
}