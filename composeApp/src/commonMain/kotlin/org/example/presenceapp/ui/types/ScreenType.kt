package org.example.presenceapp.ui.types

import org.example.presenceapp.someData.SelectedLessonHolder

enum class ScreenType {
    SCHEDULE,
    GROUP
}

fun getScreenType(screenType: ScreenType): String {
    return when (screenType) {
        ScreenType.SCHEDULE -> ""
        ScreenType.GROUP -> SelectedLessonHolder.selectedLesson?.subject?.name ?: ""
    }
}