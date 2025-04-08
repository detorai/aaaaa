package org.example.presenceapp.data.remote.dto.attendance

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class AttendanceRequestDto(
    val presenceId: Int,
    val scheduleId: Int,
    val attendanceTypeId: Int,
    val presenceDate: LocalDate,
    val studentId: Int
)