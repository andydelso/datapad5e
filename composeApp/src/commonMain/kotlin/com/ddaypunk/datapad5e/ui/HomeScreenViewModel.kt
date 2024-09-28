package com.ddaypunk.datapad5e.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddaypunk.datapad5e.data.HttpClientFactory
import com.ddaypunk.datapad5e.data.KtorRemotePowersClient
import com.ddaypunk.datapad5e.data.PowerModel
import com.ddaypunk.datapad5e.domain.Resource
import datapad5e.composeapp.generated.resources.Res
import datapad5e.composeapp.generated.resources.acid_splash
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource

class HomeScreenViewModel (
//    private val powersClient: KtorRemotePowersClient,
//    private val coroutineScope: CoroutineScope?
) : ViewModel() {
    private lateinit var response: Resource<Map<Int, List<PowerModel>>>

//    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val httpClient = HttpClientFactory().create()
    private val powersClient = KtorRemotePowersClient(httpClient)
    private val repository = PowersRepository(powersClient)


    private val _state: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(
        HomeScreenUiState.Loading
    )
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            response = repository.retrieve()
            when(response) {
                is Resource.Error -> {
                    _state.value = HomeScreenUiState.Error(response.throwable?.message)
                }
                is Resource.Success -> {
                    val screenState = response.data?.toHomeScreenState()
                    _state.value = HomeScreenUiState.Ready(screenState)
                }
            }
        }
    }

    fun onInput(input: HomeScreenUserInput) {
        when (input) {
            is HomeScreenUserInput.PowerTapped -> handlePowerTapped(input.power)
            HomeScreenUserInput.PowerDialogClosed -> handlePowerDialogClosed()
        }
    }

    private fun Map<Int, List<PowerModel>>?.toHomeScreenState(): Map<Int, List<PowerCardState>>? {
        return this?.mapValues {
            it.value.map { model ->
                PowerCardState(
                    title = model.name,
                    subtitle = model.powerType.displayText,
                    level = model.level,
                    image = getPowerImageFrom(model.name),
                    onClick = { onInput(HomeScreenUserInput.PowerTapped(model)) }
                )
            }
        }
    }

    private fun getPowerImageFrom(name: String): DrawableResource? {
        return when(name.lowercase()) {
            "acid splash" -> Res.drawable.acid_splash
            else -> null
        }
        return null
    }

    private fun handlePowerTapped(power: PowerModel) {
        _state.update {
            (it as HomeScreenUiState.Ready).copy(
                isDialogDisplayed = true,
                dialogContent = power,
                onDialogClose = { onInput(HomeScreenUserInput.PowerDialogClosed) }
            )
        }
    }

    private fun handlePowerDialogClosed() {
        _state.update {
            (it as HomeScreenUiState.Ready).copy(
                isDialogDisplayed = false,
                dialogContent = null,
                onDialogClose = null
            )
        }
    }
}

sealed class HomeScreenUiState {
    data object Loading : HomeScreenUiState()
    data class Error(val message: String? = "Wipe them out... All of them...") : HomeScreenUiState()
    data class Ready(
        val powers: Map<Int, List<PowerCardState>>? = emptyMap(),
        val isDialogDisplayed: Boolean = false,
        val dialogContent: PowerModel? = null,
        val onDialogClose: (() -> Unit)? = null
    ) : HomeScreenUiState()
}

 sealed class HomeScreenUserInput {
    data class PowerTapped(val power: PowerModel) : HomeScreenUserInput()
     data object PowerDialogClosed : HomeScreenUserInput()
 }