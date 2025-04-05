package org.example.presenceapp.data.remote.dto.attendance

import kotlinx.serialization.Serializable

@Serializable
data class AttendanceRequestDto(
    val studentId: Int,
    val scheduleId: Int,
    val attendanceTypeId: Int,
    val presenceDate: String
)