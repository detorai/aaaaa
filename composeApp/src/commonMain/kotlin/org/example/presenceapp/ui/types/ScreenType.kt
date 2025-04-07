package org.example.presenceapp.ui.types

import org.example.presenceapp.someData.SelectedLessonHolder

enum class ScreenType {
    SCHEDULE,
    GROUP,
    CHAT,
    BUTTON
}

fun getScreenType(screenType: ScreenType): String {
    return when (screenType) {
        ScreenType.SCHEDULE -> "01.01"
        ScreenType.GROUP -> SelectedLessonHolder.selectedLesson?.subject?.name ?: ""
        ScreenType.CHAT -> "ChatName"
        ScreenType.BUTTON -> ""
    }
}