package org.example.presenceapp.domain.usecases

import org.example.presenceapp.data.repository.AttendanceRepository
import org.example.presenceapp.domain.models.Attendance
import org.example.presenceapp.domain.models.AttendanceType
//
//class GetWeeklyAttendanceUseCase(
//    private val repository: AttendanceRepository
//) {
//    suspend operator fun invoke(groupId: Int): Map<String, List<Attendance>> {
//        return repository.getWeeklyAttendance(groupId)
//            .filterValues { attendances -> attendances.all { it.type == AttendanceType.PRESENT } }
//    }
//}