package org.example.presenceapp.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.example.presenceapp.ui.types.ButtonType

@Composable
fun CommonIconButton(
    background: Color,
    icon: Painter,
    iconName: Painter? = null,
    iconO: Painter? = null,
    iconP: Painter? = null,
    iconColor: Color,
    buttonType: ButtonType,
    selectedIconState: State<Int>? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentIcon = when {
        buttonType == ButtonType.SWITCHABLE && selectedIconState != null -> {
            when (selectedIconState.value) {
                0 -> { iconO?:icon}
                1 -> {iconP?:icon}
                2 -> {iconName?:icon}
                else -> icon
            }
        }
        else -> icon
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
    ) {
        Icon(
            painter = currentIcon,
            contentDescription = null,
            tint = iconColor
        )
    }
}