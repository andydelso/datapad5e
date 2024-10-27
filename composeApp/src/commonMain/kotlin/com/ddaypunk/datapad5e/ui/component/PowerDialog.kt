package com.ddaypunk.datapad5e.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.ddaypunk.datapad5e.data.CastingPeriod
import com.ddaypunk.datapad5e.data.ContentSource
import com.ddaypunk.datapad5e.data.ForceAlignment
import com.ddaypunk.datapad5e.ui.model.PowerDialogState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PowerDialog(
    state: PowerDialogState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        with(state) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PowerDialogHeading(
                    primary = title,
                    secondary = subtitle
                )
                PowerDialogField(
                    label = "Casting Period",
                    value = castingPeriod
                )
                PowerDialogField(
                    label = "Range",
                    value = range
                )
                PowerDialogField(
                    label = "Duration",
                    value = duration
                )
                PowerDialogField(
                    label = "Concentration",
                    value = concentration
                )
                Text(text = description)
            }
        }
    }
}

@Composable
fun PowerDialogHeading(
    primary: String,
    secondary: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(primary)
                append(" ")
            }
            append(secondary)
        },
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun PowerDialogField(
    label: String,
    value: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(label)
                append(": ")
            }
            append(value)
        }
    )
}

@Preview
@Composable
private fun PowerDialogPreview() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        PowerDialog(
            state = PowerDialogState(
                title = "Acid Dart",
                subtitle = "Tech Power",
                alignment = ForceAlignment.NONE.name,
                castingPeriod = CastingPeriod.ACTION.name,
                range = "90 feet",
                duration = "Instantaneous",
                concentration = "-",
                prerequisite = null,
                description = "A shimmering green dart streaks toward a target within range and bursts in a spray of acid. Make a ranged tech attack against the target. On a hit, the target takes 4d4 acid damage immediately and 2d4 acid damage at the end of its next turn. On a miss, the dart splashes the target with acid for half as much of the initial damage and no damage at the end of its next turn.\r\n\r\n***Overcharge Tech.*** When you cast this power using a tech slot of 3rd level or higher, the damage (both initial and later) increases by 1d4 for each slot level above 2nd.",
                source = ContentSource.PHB.name
            )
        )
    }
}