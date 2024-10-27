package com.ddaypunk.datapad5e.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddaypunk.datapad5e.ui.component.PowerCard
import com.ddaypunk.datapad5e.ui.component.PowerDialog
import com.ddaypunk.datapad5e.ui.extension.getFormattedLevel
import com.ddaypunk.datapad5e.ui.model.PowerDialogState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Datapad 5e")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (state.value) {
                is HomeScreenUiState.Error -> {
                    item {
                        Error(state.value as HomeScreenUiState.Error)
                    }
                }
                HomeScreenUiState.Loading -> {
                    item {
                        Loading()
                    }
                }
                is HomeScreenUiState.Ready ->
                    item {
                        HomeScreenReady(state.value as HomeScreenUiState.Ready)
                    }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenReady(
    state: HomeScreenUiState.Ready,
) {
    state.powers?.let { nonNullPowers ->
        nonNullPowers.keys.forEach { level ->
            val powers = nonNullPowers[level]
            val carouselState = rememberCarouselState(
                initialItem = 0,
                itemCount = { powers?.size ?: 0 }
            )

            Text(
                text = level.getFormattedLevel(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalUncontainedCarousel(
                state = carouselState,
                itemWidth = 228.dp,
                itemSpacing = 8.dp,
                flingBehavior = CarouselDefaults.singleAdvanceFlingBehavior(carouselState),
                modifier = Modifier.padding(start = 16.dp)
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
    if (state.isDialogDisplayed) {
        state.onDialogClose?.let { nonNullCloseCallback ->
            state.dialogContent?.let { nonNullDialogContent ->
                Dialog(
                    onDismissRequest = nonNullCloseCallback,
                    ) {
                    with(nonNullDialogContent) {
                        PowerDialog(
                            // TODO map this in the VM
                            state = PowerDialogState(
                                title = name,
                                subtitle = powerType.displayText,
                                alignment = forceAlignment.name,
                                castingPeriod = castingPeriodText,
                                range = range,
                                duration = duration,
                                concentration = if (concentration) "Yes" else "-",
                                prerequisite = prerequisite,
                                description = description,
                                source = contentSource.name
                            )
                        )
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
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Powers Loading...",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
