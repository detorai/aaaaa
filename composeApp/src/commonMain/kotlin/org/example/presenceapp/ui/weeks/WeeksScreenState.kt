package org.example.project.ui.weeks

import org.example.project.domain.models.MonthWithWeek

data class WeeksScreenState(
    var error: String? = null,
    var success: Boolean = false,
    var data: MonthWithWeek
)
