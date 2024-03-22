package dev.youdroid.basketbee.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun AddInputBoxWithAction(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    label: String = "",
    onTextChanged: (String) -> Unit
) {
    var inputFieldText by remember { mutableStateOf(TextFieldValue("")) }
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = inputFieldText,
            onValueChange = { newText ->
                inputFieldText = newText
            },
            placeholder = {
                Text(text = placeHolder)
            },
            label = { Text(text = label) },
            trailingIcon = {
                Box(Modifier.padding(PaddingValues(horizontal = 8.dp))) {
                    Button(
                        onClick = {
                            onTextChanged(inputFieldText.text)
                            inputFieldText = inputFieldText.copy("")
                        },
                        shape = FloatingActionButtonDefaults.largeShape,
                        modifier = Modifier.defaultMinSize(minWidth = 30.dp, minHeight = 30.dp),
                        enabled = inputFieldText.text.isNotEmpty()
                    ) {
                        Icon(Icons.Outlined.Add, contentDescription = null)
                    }
                }
            }
        )
    }
}