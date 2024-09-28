package com.ddaypunk.datapad5e.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import datapad5e.composeapp.generated.resources.Res
import datapad5e.composeapp.generated.resources.acid_splash
import datapad5e.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun PowerCard(
    state: PowerCardState,
) {
    with(state) {
        Card(
            modifier = Modifier
                .height(200.dp)
                .clickable {
                    onClick.invoke()
                }
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(painter = painterResource(Res.drawable.acid_splash), contentDescription = null)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 16.dp,
                            horizontal = 16.dp
                        )
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.LightGray
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}