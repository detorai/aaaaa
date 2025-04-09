package org.example.presenceapp.data.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val user: UserResponse,
    val token: String
)

@Serializable
data class UserResponse(
    val uuid: String,
    val email: String,
    val number: String,
    val fio: String,
    val role: RoleResponse,
    var responsible: List<ResponsibleResponse> = emptyList()
)

@Serializable
data class RoleResponse(
    val id: Int,
    val name: String
)

@Serializable
data class GroupDto(
    val id: Int,
    val name: String
)

@Serializable
data class ResponsibleTypeDto(
    val id: Int,
    val name: String
)

@Serializable
data class ResponsibleResponse(
    val group: GroupDto,
    val responsibleType: ResponsibleTypeDto
)