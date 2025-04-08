package org.example.project.ui.weeks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.presenceapp.someData.Schedule
import org.example.presenceapp.someData.Student
import org.example.presenceapp.ui.commons.CommonBottomBar
import org.example.presenceapp.ui.commons.ErrorDialog
import org.example.presenceapp.ui.schedule.ScheduleScreen
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.weeks.components.MonthHeader
import org.example.presenceapp.ui.weeks.components.ScheduleCard
import org.example.project.domain.models.formatWeek

data class WeeksScreen(private val lessons: List<Schedule>, private val students: List<Student>): Screen {
    @Composable
    override fun Content() {
        val navigator  = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel { WeeksViewModel() }
        Scaffold(
            bottomBar = {
                CommonBottomBar()
            },
        ) {
            Weeks(viewModel, navigator)
        }
    }


    @Composable
    fun Weeks(viewModel: WeeksViewModel, navigator: Navigator){
        val state = viewModel.state.collectAsState().value
        val groupedWeeks = remember(state.data) {
            state.data.weekList.groupBy { it.first_date.month }
                .toList()
                .sortedByDescending { (month, _) -> month }
        }
        Column(
            modifier = Modifier.fillMaxSize().background(AppTheme.colors.white).padding(32.dp)
        ) {
            state.error?.let {
                ErrorDialog(
                    onDismiss = viewModel::resetError,
                    text = it
                )
            }
            LazyColumn {
                groupedWeeks.forEach { (month, weeks) ->
                    item {
                        MonthHeader(month = month)
                    }
                    items(weeks.sortedByDescending { it.first_date }) { week ->
                        ScheduleCard(text = week.formatWeek(), onClick = {
                            navigator.push(ScheduleScreen(lessons, students))
                        })
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}