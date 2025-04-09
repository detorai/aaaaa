package org.example.presenceapp.ui.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import kotlinx.datetime.LocalDate
import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.getPlatformContext
import org.example.presenceapp.data.local.storage.attendance.AttendanceStorageProvider
import org.example.presenceapp.data.remote.impl.AttendanceApiImpl
import org.example.presenceapp.data.repository.AttendanceRepository
import org.example.presenceapp.data.remote.network.KtorfitClient
import org.example.presenceapp.domain.models.Attendance
import org.example.presenceapp.domain.someData.Schedule
import org.example.presenceapp.domain.someData.SelectedLessonHolder
import org.example.presenceapp.domain.someData.Student
import org.example.presenceapp.ui.attendance.components.AttendanceColumn
import org.example.presenceapp.ui.types.ScreenType
import org.example.presenceapp.ui.commons.CommonTopBar
import org.example.presenceapp.ui.commons.ErrorDialog
import org.example.presenceapp.ui.theme.AppTheme

class AttendanceScreen(
    private val selectedLesson: Schedule,
    private val group: List<Student>,
    private val attendance: List<Attendance>,
    private val currentDay: LocalDate
): Screen {
    @Composable
    override fun Content() {
        val screenModel: AttendanceScreenModel = koinScreenModel()
        LaunchedEffect(Unit){
            screenModel.getGroup(group, attendance, currentDay, selectedLesson)
        }
        Attendance(
            screenModel = screenModel,
            selectedLesson = selectedLesson
        )
    }


    @Composable
    fun Attendance(
        screenModel: AttendanceScreenModel,
        selectedLesson: Schedule
    ) {
        SelectedLessonHolder.selectedLesson = selectedLesson
        val state = screenModel.state.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.white)
        ) {
            state.error?.let {
                ErrorDialog(
                    onDismiss = {screenModel.resetError()},
                    text = it
                )
            }
            Scaffold(
                topBar = {
                    CommonTopBar(
                        screenType = ScreenType.GROUP,
                        onChangeSortType = { newSortType ->
                            screenModel.changeSortType(newSortType)
                        },
                        text = SelectedLessonHolder.selectedLesson?.subject?.name ?: ""
                    )
                }
            ) { padding ->
                AttendanceColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    screenModel = screenModel,
                )
            }
        }
    }
}