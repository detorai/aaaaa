package org.example.presenceapp.ui.schedule

import kotlinx.datetime.LocalDate
import org.example.presenceapp.someData.CurrentDay.WeekDay
import org.example.presenceapp.someData.Schedule
import org.example.project.domain.models.Week

data class ScheduleScreenState(
    var currentWeek: Week? = null,
    val weekDays: List<WeekDay> = emptyList(),
    val selectedDate: LocalDate? = null,
    var lessons: Schedule? = null,
    var isUserSwiping: Boolean = false
)
