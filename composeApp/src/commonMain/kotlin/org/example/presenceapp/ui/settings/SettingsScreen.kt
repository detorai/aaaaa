package org.example.presenceapp.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.example.presenceapp.ui.commons.CommonBottomBar
import org.example.presenceapp.ui.settings.components.SettingsColumn
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.theme.rememberThemeState

class SettingsScreen: Screen {
    @Composable
    override fun Content() {
        Settings()
    }
}

@Composable
fun Settings() {
    val themeState = rememberThemeState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.white)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { CommonBottomBar() }
        ) { padding ->
            SettingsColumn(
                themeState = themeState
            )
        }
    }
}