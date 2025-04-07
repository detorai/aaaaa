package org.example.presenceapp.ui.weeks.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.datetime.Month
import org.example.project.domain.models.toRussianName

@Composable
    fun MonthHeader(month: Month) {
        Text(
            text = month.toRussianName(),
            modifier = Modifier
                .fillMaxWidth()
        )
    }