package org.example.project.ui.weeks

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.domain.models.DaysOfWeek
import org.example.project.domain.models.MonthWithWeek



class WeeksViewModel: ScreenModel {
    val list = listOf(
        DaysOfWeek(
            first_date = LocalDate(2025, Month.MARCH, 24),
            last_date = LocalDate(2025,Month.MARCH,28)
        ),
        DaysOfWeek(
            first_date = LocalDate(2025, Month.APRIL, 24),
            last_date = LocalDate(2025,Month.MAY,28)
        ),
        DaysOfWeek(
            first_date = LocalDate(2025, Month.APRIL, 17),
            last_date = LocalDate(2025,Month.APRIL,21)
        ),
        DaysOfWeek(
            first_date = LocalDate(2025, Month.MAY, 24),
            last_date = LocalDate(2025,Month.MAY,28)
        )
    )
    val state = MutableStateFlow(WeeksScreenState(data = MonthWithWeek(list)))

    fun resetError(){
        state.update {
            it.copy(
                error = null
            )
        }
    }

//    fun getWeeksInCurrentMonth(): List<Pair<LocalDate, LocalDate>> {
//        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
//        val firstDayOfMonth = LocalDate(today.year, today.month, 1)
//        val lastDayOfMonth = LocalDate()
//
//        val weeks = mutableListOf<Pair<LocalDate, LocalDate>>()
//
//        var currentWeekStart = firstDayOfMonth
//        if (currentWeekStart.dayOfWeek != DayOfWeek.MONDAY) {
//            currentWeekStart = firstDayOfMonth.minus((currentWeekStart.dayOfWeek.number - DayOfWeek.MONDAY.value).toLong())
//        }
//
//        while (currentWeekStart <= lastDayOfMonth) {
//            var currentWeekEnd = currentWeekStart.plus(6)
//
//            // Если конец недели выходит за пределы месяца, корректируем
//            if (currentWeekEnd > lastDayOfMonth) {
//                currentWeekEnd = lastDayOfMonth
//            }
//
//            weeks.add(currentWeekStart to currentWeekEnd)
//
//            currentWeekStart = currentWeekEnd.plus(1)
//            // Если следующая неделя начинается в следующем месяце, выходим
//            if (currentWeekStart.month != today.month) {
//                break
//            }
//        }
//
//        return weeks
//    }
}