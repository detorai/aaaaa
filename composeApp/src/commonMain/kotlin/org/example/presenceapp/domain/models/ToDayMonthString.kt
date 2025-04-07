package org.example.project.domain.models

import kotlinx.datetime.Month
import kotlinx.datetime.number


fun DaysOfWeek.formatWeek(): String {
    val start = "${first_date.dayOfMonth.toString().padStart(2, '0')}.${first_date.month.number.toString().padStart(2, '0')}"
    val end = "${last_date.dayOfMonth.toString().padStart(2, '0')}.${last_date.month.number.toString().padStart(2, '0')}"
    return "$start - $end"
}


val russianMonths = mapOf(
    Month.JANUARY to "Январь",
    Month.FEBRUARY to "Февраль",
    Month.MARCH to "Март",
    Month.APRIL to "Апрель",
    Month.MAY to "Май",
    Month.JUNE to "Июнь",
    Month.JULY to "Июль",
    Month.AUGUST to "Август",
    Month.SEPTEMBER to "Сентябрь",
    Month.OCTOBER to "Октябрь",
    Month.NOVEMBER to "Ноябрь",
    Month.DECEMBER to "Декабрь"
)

fun Month.toRussianName(): String = russianMonths[this] ?: name