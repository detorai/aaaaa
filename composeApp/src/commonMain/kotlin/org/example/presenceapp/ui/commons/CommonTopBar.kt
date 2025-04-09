package org.example.presenceapp.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.presenceapp.composeapp.resources.Res
import com.presenceapp.composeapp.resources.arrow_back
import com.presenceapp.composeapp.resources.info
import com.presenceapp.composeapp.resources.is_here
import com.presenceapp.composeapp.resources.isnt_here
import com.presenceapp.composeapp.resources.name
import org.example.presenceapp.ui.attendance.AttendanceScreenModel
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.types.ButtonType
import org.example.presenceapp.ui.types.ScreenType
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommonTopBar(
    screenType: ScreenType,
    onChangeSortType: ((AttendanceScreenModel.SortType) -> Unit) = {},
    text: String
) {
    val navigator = LocalNavigator.currentOrThrow
    val selectedIconState = remember { mutableStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.white)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .height(75.dp)
    ) {
        CommonIconButton(
            background = AppTheme.colors.black,
            icon = painterResource(Res.drawable.arrow_back),
            iconColor = AppTheme.colors.white,
            buttonType = ButtonType.notSWITCHABLE,
            onClick = { navigator.pop() },
            modifier = Modifier
        )
        Text(
            text = text,
            color = AppTheme.colors.black,
            style = AppTheme.typography.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .padding(end = if (screenType == ScreenType.GROUP) 0.dp else 44.dp)
        )
        if (screenType == ScreenType.GROUP) {
            val newSortType = when (selectedIconState.value) {
                0 -> AttendanceScreenModel.SortType.BY_ABSENCE
                1 -> AttendanceScreenModel.SortType.BY_PRESENCE
                2 -> {AttendanceScreenModel.SortType.BY_NAME}
                else -> {AttendanceScreenModel.SortType.BY_NAME}
            }

            CommonIconButton(
                background = AppTheme.colors.black,
                iconP = painterResource(Res.drawable.is_here),
                iconO = painterResource(Res.drawable.isnt_here),
                iconName = painterResource(Res.drawable.name),
                iconColor = AppTheme.colors.white,
                buttonType = ButtonType.SWITCHABLE,
                selectedIconState = selectedIconState,
                onClick = {
                    selectedIconState.value = (selectedIconState.value + 1) % 3
                    onChangeSortType(newSortType)
                },
                modifier = Modifier,
                icon = painterResource(Res.drawable.info)
            )
        }
    }
}