package org.example.presenceapp.ui.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.presenceapp.someData.Lesson
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun ScheduleLessonList(lessons: List<Lesson>,
                       onLessonClick: (Lesson) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.white)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        itemsIndexed(lessons) { index, lesson ->
            ScheduleLessonItem(
                lesson = lesson,
                index = index,
                onLessonClick = onLessonClick
            )
        }
    }
}