package org.example.presenceapp.ui.attendance

import kotlinx.datetime.LocalDate
import org.example.presenceapp.domain.models.Attendance
import org.example.presenceapp.domain.models.AttendanceType
import org.example.presenceapp.domain.models.StudentAttendanceCard
import org.example.presenceapp.domain.someData.Schedule
import org.example.presenceapp.domain.someData.Student

data class AttendanceScreenState(
    var error: String? = null,
    var success: Boolean = false,
    var groupList: List<Student> = emptyList(),
    var attendanceTypes: List<AttendanceType> = emptyList(),
    var currentDay: LocalDate? = null,
    var lesson: Schedule? = null,
    var attendance: List<Attendance> = emptyList(),
    var sortedType: AttendanceScreenModel.SortType = AttendanceScreenModel.SortType.BY_NAME,
    val studentCard: List<StudentAttendanceCard> = emptyList()
)
