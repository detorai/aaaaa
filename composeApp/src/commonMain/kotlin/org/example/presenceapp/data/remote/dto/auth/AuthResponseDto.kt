package org.example.presenceapp.data.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val token: String
)