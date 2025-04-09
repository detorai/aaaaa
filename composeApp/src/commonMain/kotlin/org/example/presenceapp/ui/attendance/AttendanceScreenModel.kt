package org.example.presenceapp.ui.attendance

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import org.example.presenceapp.data.repository.AttendanceRepository
import org.example.presenceapp.domain.models.Attendance
import org.example.presenceapp.domain.models.AttendanceType
import org.example.presenceapp.domain.models.ResponseState
import org.example.presenceapp.domain.models.StudentAttendanceCard
import org.example.presenceapp.domain.someData.Schedule
import org.example.presenceapp.domain.someData.Student

class AttendanceScreenModel(
    private val attendanceRepository: AttendanceRepository
) : ScreenModel {
    val state = MutableStateFlow(AttendanceScreenState())

    companion object {
        const val PRESENT = "✔️"
        const val ABSENT = "❌"
    }

    enum class SortType {
        BY_PRESENCE,
        BY_ABSENCE,
        BY_NAME
    }

    init {
        getAttendanceType()
        state.update {
            it.copy(
                studentCard = combineAndSortData()
            )
        }
    }

    fun resetError(){
        state.update {
            it.copy(
                error = null
            )
        }
    }

    private fun combineAndSortData(): List<StudentAttendanceCard> {

        val presenceStatusId = state.value.attendanceTypes
            .firstOrNull { it.id == 1 }

        val absenceStatusId = state.value.attendanceTypes
            .firstOrNull{ it.id == 3 }

        return state.value.attendance.filter {
             it.presenceDate == state.value.currentDay && it.scheduleId == state.value.lesson?.id
        }.mapNotNull { attendance ->
            val student = state.value.groupList.firstOrNull{it.id == attendance.studentId}
            val status = state.value.attendanceTypes.firstOrNull{it.id == attendance.attendanceType}

            if (student != null && status != null){
                StudentAttendanceCard(
                    studentId = student.id,
                    studentName = student.name,
                    attendanceStatus = status,
                    scheduleInfo = state.value.lesson?.let {
                        StudentAttendanceCard.ScheduleInfo(
                            lessonNumber = it.lessonNumber,
                            audience = it.audience,
                            subjectName = it.subject.name
                        )
                    }!!,
                    presenceDate = attendance.presenceDate
                )
            } else null
        }.sortedWith(
            when (state.value.sortedType) {
                SortType.BY_PRESENCE -> compareByDescending {
                    it.attendanceStatus.id == presenceStatusId?.id
                }
                SortType.BY_ABSENCE -> compareByDescending {
                    it.attendanceStatus.id == absenceStatusId?.id
                }
                SortType.BY_NAME -> compareBy { it.studentName }
            }
        )
    }
    fun changeSortType(newSortType: SortType) {
        state.value.sortedType = newSortType
    }

    fun updateAttendance(studentId: Int, status: String) {
        val statusId = state.value.attendanceTypes
            .first { it.name.equals(status, ignoreCase = true) }.id
        state.update {
            it.copy(
                attendance = it.attendance.map { attendance ->
                    if (attendance.studentId == studentId) {
                        attendance.copy(attendanceType = statusId)
                    } else attendance
                }
            )
        }
    }

    fun updateAttendanceForSelected(studentIds: Set<Int>, newStatus: String) {
        val statusId = state.value.attendanceTypes
            .first { it.name.equals(newStatus, ignoreCase = true) }.id

        state.update {
            it.copy(
                attendance = it.attendance.map { attendance ->
                    if (studentIds.contains(attendance.studentId)) {
                        attendance.copy(attendanceType = statusId)
                    } else attendance
                }
            )
        }
    }
//
//    fun changeSortType(newSortType: SortType) {
//        _sortType.value = newSortType
//    }

    fun getGroup(group: List<Student>, attendance: List<Attendance>, day: LocalDate, lesson: Schedule){
        screenModelScope.launch {
            state.update {
                it.copy(
                    groupList = group,
                    attendance = attendance,
                    currentDay = day,
                    lesson = lesson
                )
            }
        }
    }

    private fun getAttendanceType(){
        screenModelScope.launch {
            val result = attendanceRepository.getAttendanceType()
            result.collect{response ->
                when (response) {
                    is ResponseState.Success<*> -> {
                        state.update {
                            it.copy(
                                attendanceTypes = response.data as List<AttendanceType>
                            )
                        }
                    }
                    is ResponseState.Error -> {
                        state.update {
                            it.copy(
                                error = response.error
                            )
                        }
                    }
                }
            }
        }
    }
}