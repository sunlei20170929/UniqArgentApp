package com.store.pacific.stage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MainViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel(){

    val shownSplash = mutableStateOf(SplashState.Shown)
}