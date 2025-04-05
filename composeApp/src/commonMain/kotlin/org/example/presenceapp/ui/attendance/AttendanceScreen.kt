package org.example.presenceapp.ui.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.getPlatformContext
import org.example.presenceapp.data.local.storage.AttendanceStorageProvider
import org.example.presenceapp.data.remote.RemoteDataSource
import org.example.presenceapp.data.repository.AttendanceRepository
import org.example.presenceapp.network.KtorfitClient
import org.example.presenceapp.someData.Lesson
import org.example.presenceapp.someData.SampleData
import org.example.presenceapp.someData.SelectedLessonHolder
import org.example.presenceapp.ui.attendance.components.AttendanceColumn
import org.example.presenceapp.ui.types.ScreenType
import org.example.presenceapp.ui.commons.CommonTopBar
import org.example.presenceapp.ui.theme.AppTheme

class AttendanceScreen(private val selectedLesson: Lesson): Screen {
    @Composable
    override fun Content() {
        val platformContext = getPlatformContext()
        val attendanceStorage = AttendanceStorageProvider(platformContext).provide()
        val localDataSource = LocalDataSource(attendanceStorage)
        val attendanceApi = KtorfitClient.createAttendanceApi()
        val remoteDataSource = RemoteDataSource(attendanceApi)
        val attendanceRepository = AttendanceRepository(localDataSource, remoteDataSource)

        val screenModel = rememberScreenModel {
            AttendanceScreenModel(attendanceRepository = attendanceRepository)
        }

        Attendance(
            screenModel = screenModel,
            selectedLesson = selectedLesson)
    }
}

@Composable
fun Attendance(screenModel: AttendanceScreenModel,
               selectedLesson: Lesson) {
    SelectedLessonHolder.selectedLesson = SampleData.lessons[0]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.white)
    ) {
        Scaffold(
            topBar = { CommonTopBar(
                screenType = ScreenType.GROUP,
                onChangeSortType = { newSortType ->
                    screenModel.changeSortType(newSortType)
                }
            ) }
        ) { padding ->
            AttendanceColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                screenModel = screenModel
            )
        }
    }
}