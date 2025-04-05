package org.example.presenceapp.domain.models

import kotlinx.datetime.LocalDate

data class Attendance(
    val studentId: Int,
    val date: LocalDate,
    val type: AttendanceType
)

enum class AttendanceType {
    PRESENT, ABSENT
}