package org.example.presenceapp.data.remote

import kotlinx.datetime.LocalDate
import org.example.presenceapp.data.remote.api.AttendanceApi
import org.example.presenceapp.domain.models.Attendance
import org.example.presenceapp.domain.models.AttendanceType

class RemoteDataSource(private val api: AttendanceApi) {
    suspend fun getWeeklyAttendance(groupId: Int): Map<String, List<Attendance>> {
        val dtos = api.getGroupPresetting(groupId)
        return dtos
            .groupBy { it.presenceDate }
            .mapValues { entry ->
                entry.value.map { dto ->
                    Attendance(
                        studentId = dto.studentId,
                        date = LocalDate.parse(dto.presenceDate),
                        type = when (dto.attendanceTypeId) {
                            1 -> AttendanceType.PRESENT
                            else -> AttendanceType.ABSENT
                        }
                    )
                }
            }
    }
}