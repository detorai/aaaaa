package org.example.presenceapp.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.theme.themeManager.ThemeState

@Composable
fun SettingsColumn(themeState: ThemeState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.white)
    ) {
        SettingsThemeOption(
            themeState = themeState
        )
    }
}