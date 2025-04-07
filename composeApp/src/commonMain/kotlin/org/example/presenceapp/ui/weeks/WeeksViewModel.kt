package org.example.project.ui.weeks

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
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
}