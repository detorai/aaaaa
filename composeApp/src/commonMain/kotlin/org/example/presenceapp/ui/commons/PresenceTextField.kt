package org.example.project.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun PresenceTextField(
    onValue: (String) -> Unit,
    value: String,
    placeholder: String,
    top: Int    ,
    text: String
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = top.dp),
    ) {
        Text(
            text,
            color = AppTheme.colors.black,
            style = AppTheme.typography.regular
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValue,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(top = 9.dp).fillMaxWidth(),
            placeholder = {
                Text(
                    placeholder,
                    color = AppTheme.colors.gray,
                    style = AppTheme.typography.message
                )
            }
        )
    }
}