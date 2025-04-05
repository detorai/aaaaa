package org.example.presenceapp.data.remote.dto.attendance

import kotlinx.serialization.Serializable

@Serializable
data class AttendanceTypeDto(
    val id: Int,
    val name: String
)