package org.example.project.ui.weeks

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import org.example.project.domain.models.MonthWithWeeks
import org.example.project.domain.models.Week


class WeeksViewModel: ScreenModel {
    val state = MutableStateFlow(WeeksScreenState())

    init {
        loadWeeks()

    }

    private fun loadWeeks() {
        val weeks = getWeeksInCurrentMonth().map { (start, end) ->
            Week(
                startDate = start,
                endDate = end
            )
        }

        val currentMonth = Clock.System.todayIn(TimeZone.currentSystemDefault()).month
        val currentYear = Clock.System.todayIn(TimeZone.currentSystemDefault()).year

        state.update {
            it.copy(
                data = listOf(
                    MonthWithWeeks(
                        month = currentMonth,
                        year = currentYear,
                        weeks = weeks
                    )
                )
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

    fun getWeeksInCurrentMonth(): List<Pair<LocalDate, LocalDate>> {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val firstDayOfMonth = LocalDate(today.year, today.month, 1)
        val lastDayOfMonth = firstDayOfMonth.plus(1, DateTimeUnit.MONTH).minus(1, DateTimeUnit.DAY)

        val weeks = mutableListOf<Pair<LocalDate, LocalDate>>()
        var currentWeekStart = firstDayOfMonth.findPreviousMonday()

        while (currentWeekStart <= lastDayOfMonth.plus(6, DateTimeUnit.DAY)) {
            val currentWeekEnd = currentWeekStart.plus(6, DateTimeUnit.DAY)

            // Добавляем неделю только если она пересекается с текущим месяцем
            if (currentWeekStart <= lastDayOfMonth || currentWeekEnd >= firstDayOfMonth) {
                val weekStart = maxOf(currentWeekStart, firstDayOfMonth)
                val weekEnd = minOf(currentWeekEnd, lastDayOfMonth)

                weeks.add(weekStart to weekEnd)
            }

            currentWeekStart = currentWeekEnd.plus(1, DateTimeUnit.DAY)
        }

        return weeks.distinctBy { it.first }
    }

    private fun LocalDate.findPreviousMonday(): LocalDate {
        var date = this
        while (date.dayOfWeek != DayOfWeek.MONDAY) {
            date = date.minus(1, DateTimeUnit.DAY)
        }
        return date
    }
}