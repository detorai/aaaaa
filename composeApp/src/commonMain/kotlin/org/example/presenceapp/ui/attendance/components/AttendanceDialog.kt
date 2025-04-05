package org.example.presenceapp.ui.attendance.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun AttendanceDialog(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onStatusSelected: (String) -> Unit,
    onStatusForAll: (String) -> Unit
) {
    if (expanded) {
        AlertDialog(
            containerColor = AppTheme.colors.textField,
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnClickOutside = true),
            confirmButton = {},
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Детали отсутствия",
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.messageFrag,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                }
            }
        )
    }
}