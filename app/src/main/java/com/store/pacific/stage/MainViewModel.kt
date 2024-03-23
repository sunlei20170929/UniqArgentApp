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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.store.pacific.stage.repository.UniqRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


class MainViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle,
                                        private val application: Application,
    private val repo:UniqRepository
): AndroidViewModel(application){

    private val LANGUAGE = "language"

    val shownSplash = mutableStateOf(SplashState.Shown)

    var languageSetting: Int? = if(savedStateHandle.get<Int>(LANGUAGE)==null)  0
    else savedStateHandle[LANGUAGE]

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun setLanguage(lan:Int){
        viewModelScope.launch {
            languageSetting = lan
            savedStateHandle.set(LANGUAGE,languageSetting)
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


    private val _data = MutableStateFlow<String>("")
    val data: StateFlow<String> = _data
    fun fetchData(num:String) {
        viewModelScope.launch {
            // 模拟从网络或数据库中获取数据 delay(1000)
            _data.value = repo.getSmscode(num).toString()
        }
    }
}