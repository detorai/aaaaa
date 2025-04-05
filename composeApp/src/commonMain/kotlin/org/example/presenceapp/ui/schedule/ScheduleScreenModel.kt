package org.example.presenceapp.ui.schedule

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.presenceapp.someData.Lesson
import org.example.presenceapp.someData.SampleData

class ScheduleScreenModel: ScreenModel {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _lessons = MutableStateFlow<List<Lesson>>(emptyList())
    val lessons: StateFlow<List<Lesson>> = _lessons.asStateFlow()

    private val _selectedLesson = MutableStateFlow<Lesson?>(null)
    val selectedLesson: StateFlow<Lesson?> = _selectedLesson.asStateFlow()

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

    fun selectLesson(lesson: Lesson) {
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