package com.store.pacific.stage

import android.app.Application
import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.store.pacific.stage.repository.UniqRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle,
                                        private val application: Application,
    private val repo:UniqRepository): AndroidViewModel(application){

    private val LANGUAGE = "language"
    private val REMINDER = "reminder"

    val shownSplash = mutableStateOf(SplashState.Shown)

    var languageSetting: Int? = if(savedStateHandle.get<Int>(LANGUAGE)==null)  0
    else savedStateHandle[LANGUAGE]

    var hasRead:Boolean = savedStateHandle.get<Boolean>(REMINDER) == true


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun setLanguage(lan:Int){
        viewModelScope.launch {
            languageSetting = lan
            savedStateHandle[LANGUAGE] = languageSetting
            when(lan){
                1->{
                    application.applicationContext.getSystemService(LocaleManager::class.java
                    ).applicationLocales = LocaleList(Locale.forLanguageTag("en-US"))
                }

                2->{
                    application.applicationContext.getSystemService(LocaleManager::class.java
                    ).applicationLocales = LocaleList(Locale.forLanguageTag("fr"))
                }
            }
        }
    }

    fun onAcceptReminder(){
        hasRead = true
        savedStateHandle[REMINDER] = true
    }

    fun onRefuseReminder(){
        hasRead = true
        savedStateHandle[REMINDER] = false
    }

    private var _sms:MutableState<String> = mutableStateOf("")
    val sms = _sms

    fun getSms(num:String){
        viewModelScope.launch {
            repo.getSmscode(num).catch {
                _sms.value = it.message.toString()
            }.collect{
                _sms.value = it
            }
        }
    }

}

// Represents different states for the LatestNews screen
sealed class LatestNewsUiState {
    data class Success(val news: List<String>): LatestNewsUiState()
    data class Error(val exception: Throwable): LatestNewsUiState()
}