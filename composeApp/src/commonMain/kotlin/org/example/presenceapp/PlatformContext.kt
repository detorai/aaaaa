package org.example.presenceapp

import androidx.compose.runtime.Composable

expect class PlatformContext

@Composable
expect fun getPlatformContext(): PlatformContext