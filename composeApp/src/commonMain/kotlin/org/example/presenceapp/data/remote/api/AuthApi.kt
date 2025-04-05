package org.example.presenceapp.data.remote.api

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import org.example.presenceapp.data.remote.dto.auth.AuthRequestDto
import org.example.presenceapp.data.remote.dto.auth.AuthResponseDto

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: AuthRequestDto): AuthResponseDto
}