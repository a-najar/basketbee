package dev.youdroid.basketbee.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.youdroid.basketbee.ui.theme.BasketBeeTheme

@Composable
fun CheckableListItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    endIcon: @Composable (() -> Unit)? = null,
    checked: Boolean,
    onCheckedChange: (isChecked: Boolean) -> Unit,
) {
    Card(
        Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
            Column(
                Modifier
                    .padding(PaddingValues(top = 8.dp, bottom = 8.dp))
                    .weight(1f),
            ) {
                Text(
                    title, style = MaterialTheme.typography.titleMedium.copy(
                        textMotion = TextMotion.Animated,
                        textDecoration = if (checked) TextDecoration.LineThrough else null
                    )
                )
                subtitle?.let {
                    Text(
                        text = it, style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.tertiary,
                            textMotion = TextMotion.Animated,
                            textDecoration = if (checked) TextDecoration.LineThrough else null
                        )
                    )
                }
            }
            endIcon?.let { it() }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CheckableListItemPrev() {
    BasketBeeTheme {
        LazyColumn {
            items(10) {
                CheckableListItem(
                    title = "Test One",
                    subtitle = "test two",
                    checked = false
                ) {}
            }
        }
    }
}