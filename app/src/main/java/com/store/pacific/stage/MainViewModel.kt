package com.store.pacific.stage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel(){

    private val LANGUAGE = "language"

    val shownSplash = mutableStateOf(SplashState.Shown)

    var languageSetting: Int? = if(savedStateHandle.get<Int>(LANGUAGE)==null)  0
    else savedStateHandle[LANGUAGE]

    fun setLanguage(lan:Int){
        viewModelScope.launch {
            languageSetting = lan
            savedStateHandle.set(LANGUAGE,languageSetting)
        }

    }
}