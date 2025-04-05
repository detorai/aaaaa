package org.example.presenceapp.data.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto(
    val login: String,
    val password: String
)