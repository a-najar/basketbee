package dev.youdroid.basketbee.ui.items

import android.content.res.Resources.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlin.random.Random

@Composable
fun GridItem(icon: String, title: String, subtitle: String? = null, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(1.dp),
        onClick = onClick
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .height(120.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val randomColor = generateRandomColor()
            val tint = calculateTint(randomColor)

            Box(
                Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .background(
                        randomColor, CircleShape.copy(CornerSize(100.dp))
                    ), contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    colorFilter = ColorFilter.tint(tint),
                    model = icon,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onSurface),
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }

            if (!subtitle.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                SuggestionChip(
                    enabled = true,
                    onClick = {},
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        labelColor = MaterialTheme.colorScheme.onSurface
                    ),
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        true, borderColor = MaterialTheme.colorScheme.onSurface
                    ),
                    label = { Text(text = subtitle, style = MaterialTheme.typography.titleSmall) },
                )
            }


        }
    }
}

private fun generateRandomColor(): Color {
    val random = Random.Default
    return Color(
        red = random.nextFloat(), green = random.nextFloat(), blue = random.nextFloat(), alpha = 1f
    )
}

private fun calculateTint(backgroundColor: Color): Color {
    val luminance = backgroundColor.luminance()
    val threshold = 0.5
    return if (luminance > threshold) Color.Black else Color.White
}
