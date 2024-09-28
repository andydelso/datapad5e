package com.ddaypunk.datapad5e.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddaypunk.datapad5e.ui.extension.getFormattedLevel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState()

    when(state.value) {
        is HomeScreenUiState.Error -> Error(state.value as HomeScreenUiState.Error)
        HomeScreenUiState.Loading -> Loading()
        is HomeScreenUiState.Ready -> HomeScreenReady(state.value as HomeScreenUiState.Ready)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenReady(
    state: HomeScreenUiState.Ready,
//    onInput: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        state.powers?.let { nonNullPowers ->
            nonNullPowers.keys.forEach { level ->
                item(
                    key = level
                ) {
                    val powers = nonNullPowers[level]
                    val carouselState = rememberCarouselState(
                        initialItem = 0,
                        itemCount = { powers?.size ?: 0 }
                    )

                    Text(
                        text = level.getFormattedLevel(),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalMultiBrowseCarousel(
                        state = carouselState,
                        preferredItemWidth = 160.dp,
                        itemSpacing = 8.dp
                    ) { page ->
                        powers?.get(page)?.let { nonNullPage ->
                            // Todo this should be in the screen state
                            PowerCard(
                                state = nonNullPage
                            )
                        }
                    }
                }
            }
        }
    }
    if (state.isDialogDisplayed) {
        state.onDialogClose?.let { nonNullCloseCallback ->
            Dialog(
                onDismissRequest = nonNullCloseCallback,
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    state.dialogContent?.let { nonNullDialogContent ->
                        Column(
                            modifier = Modifier
                                .padding(16.dp),
//                                verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 16.dp)
                            ) {
                                Text(
                                    text = nonNullDialogContent.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    text = nonNullDialogContent.powerType.displayText,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Row {
                                Text(
                                    text = "Casting Period:",
                                    modifier = Modifier.padding(end = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = nonNullDialogContent.castingPeriodText)
                            }

                            Row {
                                Text(
                                    text = "Range:",
                                    modifier = Modifier.padding(end = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = nonNullDialogContent.range)
                            }

                            Row {
                                Text(
                                    text = "Duration:",
                                    modifier = Modifier.padding(end = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = nonNullDialogContent.duration)
                            }

                            Row(
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                                Text(
                                    text = "Concentration:",
                                    modifier = Modifier.padding(end = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (nonNullDialogContent.concentration) {
                                        "Yes"
                                    } else {
                                        "-"
                                    }
                                )
                            }

                            // Todo: clicks are wrong on this
                            Text(text = nonNullDialogContent.description)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Error(state: HomeScreenUiState.Error) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.message ?: "Wipe them out, all of them...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

//@Preview
//@Composable
//fun HomeScreenPreview() {
//     {
//        HomeScreenReady(
//            state = (),
//            onInput = {}
//        )
//    }
//}