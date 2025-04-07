package org.example.project.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PresenceButton(
    text: String,
    onClick: () -> Unit,
    bottom: Int
){
    Button(
        modifier = Modifier.padding(bottom = bottom.dp).fillMaxWidth().height(40.dp),
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
    ){
        Text(
            text
        )
    }
}