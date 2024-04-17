package com.store.pacific.stage.uniqargent.ui.mine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class MineViewModel  constructor(
    savedStateHandle: SavedStateHandle,
    repository: MineRepository
) : ViewModel(){

    private val navigationChannel  = Channel<NavigationEvent>()
    val navigationEventsChannelFlow = navigationChannel.receiveAsFlow()

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEventsSharedFlow = _navigationEvents.asSharedFlow()

    var isLoggedIn by mutableStateOf(false)
        private set

    var state by mutableStateOf(LoginState())
        private set

    fun login(){
        viewModelScope.launch{
            state = state.copy(isLoading = true)

            delay(3_000)
            navigationChannel.send(NavigationEvent.NavigateToProfile)

            state = state.copy(isLoading = false,)
        }
    }

    sealed interface NavigationEvent{
        object NavigateToProfile:NavigationEvent
    }

    data class LoginState(val isLoading: Boolean =false, val isLoggedIn: Boolean =false,)
}