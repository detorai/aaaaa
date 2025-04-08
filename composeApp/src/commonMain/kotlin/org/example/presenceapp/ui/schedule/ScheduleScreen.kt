package org.example.presenceapp.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.presenceapp.someData.Schedule
import org.example.presenceapp.someData.Student
import org.example.presenceapp.ui.attendance.AttendanceScreen
import org.example.presenceapp.ui.commons.CommonTopBar
import org.example.presenceapp.ui.commons.ErrorDialog
import org.example.presenceapp.ui.schedule.components.ScheduleDaySelector
import org.example.presenceapp.ui.schedule.components.ScheduleLessonList
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.types.ScreenType

data class ScheduleScreen(private val lessons: List<Schedule>, private val students: List<Student>): Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { ScheduleScreenModel() }
        Schedule(screenModel = screenModel)
    }

    @Composable
    fun Schedule(screenModel: ScheduleScreenModel) {
        val navigator = LocalNavigator.currentOrThrow
        val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб")
        val currentDayIndex by screenModel.currentDayIndex.collectAsState()
        val pagerState = rememberPagerState(
            initialPage = currentDayIndex,
            pageCount = { daysOfWeek.size }
        )
        val currentPage = pagerState.currentPage + pagerState.currentPageOffsetFraction

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.isScrollInProgress }
                .collect { isScrolling ->
                    screenModel.setSwipeState(isScrolling)
                    if (!isScrolling) {
                        screenModel.selectDay(pagerState.currentPage)
                    }
                }
        }
        LaunchedEffect(currentDayIndex) {
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(currentDayIndex)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.white)
        ) {
            Scaffold(
                topBar = {
                    CommonTopBar(
                        screenType = ScreenType.SCHEDULE,
                    )
                },
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    ScheduleDaySelector(
                        currentPage = currentPage,
                        daysOfWeek = daysOfWeek,
                        onDaySelected = { screenModel.selectDay(it) },
                        indicatorColor = AppTheme.colors.black
                    )
                    HorizontalPager(state = pagerState) { page ->
                        val day = page + 1
                        ScheduleLessonList(
                            lessons = lessons.filter { it.dayOfWeek == day }
                                .sortedBy { it.lessonNumber },
                            onLessonClick = { lesson ->
                                screenModel.selectLesson(lesson)
                                navigator.push(AttendanceScreen(lesson, students))
                            }
                        )
                    }
                }
            }
        }
    }
}