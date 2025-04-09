package org.example.presenceapp.ui.schedule

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.presenceapp.domain.someData.SampleData
import org.example.presenceapp.domain.someData.Schedule

class ScheduleScreenModel: ScreenModel {
    val state = MutableStateFlow(ScheduleScreenState())

    private val _currentDayIndex = MutableStateFlow(0)
    val currentDayIndex: StateFlow<Int> = _currentDayIndex.asStateFlow()

    fun setSwipeState(swiping: Boolean) {
        state.update {
            it.copy(
                isUserSwiping = swiping
            )
        }
    }

    fun selectDay(index: Int) {
        if (!state.value.isUserSwiping) {
            _currentDayIndex.value = index
        }
    }

    fun selectLesson(lesson: Schedule) {
        state.update {
            it.copy(
                lessons = lesson
            )
        }
    }
}