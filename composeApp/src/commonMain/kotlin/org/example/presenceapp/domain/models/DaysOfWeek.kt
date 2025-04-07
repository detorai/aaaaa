package org.example.project.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

data class DaysOfWeek(
    val first_date: LocalDate,
    val last_date: LocalDate
)
data class MonthWithWeek(
    val weekList: List<DaysOfWeek>
)

