package org.example.presenceapp.ui.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.theme.themeManager.ThemeState
import org.example.presenceapp.ui.theme.themeManager.ThemeTypes

@Composable
fun SettingsThemeOption(
    themeState: ThemeState
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SettingsThemeItem(
            title = "Светлая тема",
            isSelected = themeState.currentTheme == ThemeTypes.LIGHT,
            onClick = { themeState.setTheme(ThemeTypes.LIGHT) }
        )
        SettingsThemeItem(
            title = "Тёмная тема",
            isSelected = themeState.currentTheme == ThemeTypes.DARK,
            onClick = { themeState.setTheme(ThemeTypes.DARK) }
        )
        SettingsThemeItem(
            title = "Как в системе",
            isSelected = themeState.currentTheme == ThemeTypes.SYSTEM,
            onClick = { themeState.setTheme(ThemeTypes.SYSTEM) }
        )
    }
}

@Composable
fun SettingsThemeItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = AppTheme.typography.messageFrag,
                modifier = Modifier
                    .weight(1f)
            )
            if (isSelected) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null
                )
            }
        }
    }
}