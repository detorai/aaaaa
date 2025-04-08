package org.example.presenceapp.data.remote

import kotlinx.datetime.LocalDate
import org.example.presenceapp.data.remote.api.AttendanceApi
import org.example.presenceapp.domain.models.Attendance
import org.example.presenceapp.domain.models.AttendanceType

class AttendanceApiImpl(private val attendance: AttendanceApi) {
    suspend fun getWeeklyAttendance(groupId: Int): Map<LocalDate, List<Attendance>> {
        val dtos = attendance.getGroupPresetting(groupId)
        return dtos
            .groupBy { it.presenceDate }
            .mapValues { entry ->
                entry.value.map { dto ->
                    Attendance(
                        studentId = dto.studentId,
                        date = LocalDate.parse(dto.presenceDate.toString()),
                        type = when (dto.attendanceTypeId) {
                            1 -> AttendanceType.PRESENT
                            else -> AttendanceType.ABSENT
                        }
                    )
                }
            }
    }
}