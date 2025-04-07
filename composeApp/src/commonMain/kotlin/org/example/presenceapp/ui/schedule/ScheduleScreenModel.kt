package org.example.presenceapp.ui.schedule

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.presenceapp.someData.SampleData
import org.example.presenceapp.someData.Schedule

class ScheduleScreenModel: ScreenModel {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _lessons = MutableStateFlow<List<Schedule>>(emptyList())
    val lessons: StateFlow<List<Schedule>> = _lessons.asStateFlow()

    private val _selectedLesson = MutableStateFlow<Schedule?>(null)
    val selectedLesson: StateFlow<Schedule?> = _selectedLesson.asStateFlow()

    private val _currentDayIndex = MutableStateFlow(0)
    val currentDayIndex: StateFlow<Int> = _currentDayIndex.asStateFlow()

    private var isUserSwiping = false

    fun setSwipeState(swiping: Boolean) {
        isUserSwiping = swiping
    }

    init {
        loadLessons()
    }

    fun selectDay(index: Int) {
        if (!isUserSwiping) {
            _currentDayIndex.value = index
        }
    }

    fun selectLesson(lesson: Schedule) {
        _selectedLesson.value = lesson
    }

    fun clearSelection() {
        _selectedLesson.value = null
    }

    private fun loadLessons() {
        _isLoading.value = true
        _lessons.value = SampleData.lessons
        _isLoading.value = false
    }
}