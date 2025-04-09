package org.example.presenceapp.data.model.dto.attendance

import kotlinx.serialization.Serializable

@Serializable
data class AttendanceTypeDto(
    val id: Int,
    val name: String
)