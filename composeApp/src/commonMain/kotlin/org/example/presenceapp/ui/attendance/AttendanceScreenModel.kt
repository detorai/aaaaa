package org.example.presenceapp.ui.attendance

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.presenceapp.someData.SomeStudents
import org.example.presenceapp.data.repository.AttendanceRepository
import org.example.presenceapp.someData.Student

class AttendanceScreenModel(
    private val attendanceRepository: AttendanceRepository
) : ScreenModel {

    val state = MutableStateFlow(AttendanceScreenState())
    private val _students = MutableStateFlow(state.value.groupList)
    private val _attendanceMap = MutableStateFlow<Map<Int, String>>(emptyMap())
    val attendanceMap: StateFlow<Map<Int, String>> = _attendanceMap.asStateFlow()

    private val _sortType = MutableStateFlow(SortType.BY_PRESENCE)

    companion object {
        const val PRESENT = "✔️"
        const val ABSENT = "❌"
    }

    enum class SortType {
        BY_PRESENCE,
        BY_ABSENCE
    }

    init {
        loadAttendance()
    }

    private fun loadAttendance() {
        screenModelScope.launch {
            attendanceRepository.observeLocalAttendance().collect { savedAttendance ->
                _attendanceMap.value = savedAttendance
            }
        }
    }

    private suspend fun saveAttendanceToStorage(map: Map<Int, String>) {
        attendanceRepository.saveAttendanceLocally(map)
    }

    val groupedStudents = combine(_students, _attendanceMap, _sortType) { students, attendance, sortType ->
        val present = students.filter { attendance[it.id] == PRESENT }.sortedBy { it.name }
        val absent = students.filter { attendance[it.id] == ABSENT }.sortedBy { it.name }
        val undefined = students.filter {
            attendance[it.id] != PRESENT && attendance[it.id] != ABSENT
        }.sortedBy { it.name }

        when (sortType) {
            SortType.BY_PRESENCE -> listOf(
                "Присутствующие" to present,
                "Отсутствующие" to absent,
                "Статус не указан" to undefined
            )
            SortType.BY_ABSENCE -> listOf(
                "Отсутствующие" to absent,
                "Присутствующие" to present,
                "Статус не указан" to undefined
            )
        }
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateAttendance(studentId: Int, status: String) {
        val updatedMap = _attendanceMap.value.toMutableMap().apply {
            this[studentId] = status
        }
        _attendanceMap.value = updatedMap
        screenModelScope.launch {
            saveAttendanceToStorage(updatedMap)
        }
    }

    fun updateAttendanceForSelected(studentIds: Set<Int>, status: String) {
        val updatedMap = _attendanceMap.value.toMutableMap().apply {
            studentIds.forEach { studentId ->
                this[studentId] = status
            }
        }
        _attendanceMap.value = updatedMap
        screenModelScope.launch {
            saveAttendanceToStorage(updatedMap)
        }
    }

    fun changeSortType(newSortType: SortType) {
        _sortType.value = newSortType
    }

    fun getGroup(group: List<Student>){
        state.update {
            it.copy(
                groupList = group
            )
        }
    }
}