package org.example.presenceapp.domain.models

import kotlinx.datetime.LocalDate

data class Attendance(
    val presenceId: Int,
    val scheduleId: Int,
    val attendanceType: Int,
    val presenceDate: LocalDate,
    val studentId: Int
)



data class AttendanceType (
    val id: Int,
    val name: String
)


data class StudentAttendanceCard(
    val studentId: Int,
    val studentName: String,
    val attendanceStatus: AttendanceType,
    val scheduleInfo: ScheduleInfo,
    val presenceDate: LocalDate
) {
    data class ScheduleInfo(
        val lessonNumber: Int,
        val audience: String,
        val subjectName: String
    )
}