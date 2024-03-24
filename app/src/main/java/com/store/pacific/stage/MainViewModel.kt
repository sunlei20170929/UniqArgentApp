package com.store.pacific.stage

import android.app.Application
import android.app.LocaleManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.LocaleList
import android.provider.Settings
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




    /**
     * FOR HEADER
     *
     * */

    companion object{
        var unknownSpeakerSoap = "134"//client-id
        var chiefPandaTerminalHolyBallet = "" //token
        var spanishSoilAdmission = "" //userId
        var passiveRubberAllAvenue = ""//currentUserId
        var basicPrivateHousework = "googleplay"//channel
        var uncertainEasternSpecialBasket = "0"//versionCode = 0
        var nervousRainbowClass = "" //versionName
        var fondLoudTroopModernPassage = ""//device-id
        var spokenRadiationFormerChoiceBill = ""//imei
        var arcticPilotFinalSleep = "1"//mulFlag
        var femaleFuelMildVoyage = ""//v-flag
        var femalePermissionSelf = ""//deviceId
    }

    fun getVersion(){
        try{
            application.applicationContext.packageManager.getPackageInfo("com.store.pacific.stage",1).also {
                nervousRainbowClass = it.versionName
                uncertainEasternSpecialBasket = it.versionCode.toString()
            }
        }catch ( e: PackageManager.NameNotFoundException) {
            e.printStackTrace();
        }
    }

    //token
    fun getchiefPandaTerminalHolyBallet(){
        chiefPandaTerminalHolyBallet =  savedStateHandle.get<String>("chiefPandaTerminalHolyBallet").toString()
        passiveRubberAllAvenue = chiefPandaTerminalHolyBallet
    }
    //device-id
    fun getfondLoudTroopModernPassage(){
        fondLoudTroopModernPassage = Settings.Secure.getString(application.applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
        spokenRadiationFormerChoiceBill = fondLoudTroopModernPassage
        femalePermissionSelf = fondLoudTroopModernPassage
    }

}

// Represents different states for the LatestNews screen
sealed class LatestNewsUiState {
    data class Success(val news: List<String>): LatestNewsUiState()
    data class Error(val exception: Throwable): LatestNewsUiState()
}