package org.example.presenceapp.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.example.presenceapp.ui.commons.CommonBottomBar

class InfoScreen: Screen {
    @Composable
    override fun Content() {
        Info()
    }
}

@Composable
fun Info() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { CommonBottomBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {

        }
    }
}