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
import kotlinx.coroutines.launch
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
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
        /**
         * header
         * */
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
        var newHungerRecentBeingSquid:Boolean? = null//v-flag
        var femalePermissionSelf = ""//deviceId

        /**
         * common params
         * */

        var unfitVariousBrokenCity = "134"//appssid
//        var spanishSoilAdmission = ""//userId
        var highPacket = "0,0"//lbs
        var rectangleZebraSleeve = "es" //language
//        var nervousRainbowClass//versionName
//        var uncertainEasternSpecialBasket = ""//versionCode
//        var uncertainEasternSpecialBasket = ""//versionCode
//        var femalePermissionSelf = ""//deviceId
//        var spokenRadiationFormerChoiceBill = ""//imei
        var finalEmbassyLightning = "0.0.0.0"  //ip
//        var basicPrivateHousework = "" //channel
        var gayEnvelopeSalesmanSilver = Build.MODEL//systemMode
        var betterEarFoolishCourtSelf = "" //googleMobileNo

        var furnishedContinentSuggestionFlashlight = ""//googleUserAgent
    }
    init{
        getVersion()
        getchiefPandaTerminalHolyBallet()
        getfondLoudTroopModernPassage()
        getIPAddress(true)

        var headerP = HeaderParam(unknownSpeakerSoap = unknownSpeakerSoap,
            chiefPandaTerminalHolyBallet = chiefPandaTerminalHolyBallet,
            spanishSoilAdmission = spanishSoilAdmission,
            passiveRubberAllAvenue = passiveRubberAllAvenue,
            basicPrivateHousework = basicPrivateHousework,
            nervousRainbowClass = nervousRainbowClass,
            uncertainEasternSpecialBasket = uncertainEasternSpecialBasket,
            femalePermissionSelf = femalePermissionSelf,
            spokenRadiationFormerChoiceBill = spokenRadiationFormerChoiceBill,
            arcticPilotFinalSleep = arcticPilotFinalSleep,
            fondLoudTroopModernPassage = fondLoudTroopModernPassage
            )

        var commonP = CommonParam(
            unfitVariousBrokenCity = unfitVariousBrokenCity,
            spanishSoilAdmission = spanishSoilAdmission,
            highPacket = highPacket,
            rectangleZebraSleeve = rectangleZebraSleeve,
            nervousRainbowClass = nervousRainbowClass,
            uncertainEasternSpecialBasket = uncertainEasternSpecialBasket,
            femalePermissionSelf = femalePermissionSelf,
            spokenRadiationFormerChoiceBill = spokenRadiationFormerChoiceBill,
            finalEmbassyLightning = finalEmbassyLightning,
            basicPrivateHousework = basicPrivateHousework,
            gayEnvelopeSalesmanSilver = gayEnvelopeSalesmanSilver,
            betterEarFoolishCourtSelf = betterEarFoolishCourtSelf,
            furnishedContinentSuggestionFlashlight = furnishedContinentSuggestionFlashlight
        )
    }

    private fun getVersion(){
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
    private fun getchiefPandaTerminalHolyBallet(){
        chiefPandaTerminalHolyBallet =  savedStateHandle.get<String>("chiefPandaTerminalHolyBallet").toString()
        passiveRubberAllAvenue = chiefPandaTerminalHolyBallet
    }
    //device-id
    private fun getfondLoudTroopModernPassage(){
        fondLoudTroopModernPassage = Settings.Secure.getString(application.applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
        spokenRadiationFormerChoiceBill = fondLoudTroopModernPassage
        femalePermissionSelf = fondLoudTroopModernPassage
    }

    private fun getIPAddress(useIPv4: Boolean) {
        try {
            for (intf in NetworkInterface.getNetworkInterfaces()) {
                for (addr in intf.getInetAddresses()) {
                    if (InetAddress.getLocalHost() != addr) {
                        if (addr is Inet4Address) {
                            finalEmbassyLightning =  addr.getHostAddress()
                        } else if (addr is Inet6Address) {
                            finalEmbassyLightning =  addr.getHostAddress()
                        }
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }

    }

    var nobleHayLateInsect = (application as UniqArgentApp).ADID//googleGaid

}
data class CommonParam(
    var unfitVariousBrokenCity :String = "134",//appssid
    var spanishSoilAdmission :String = "",//userId
    var highPacket :String= "0,0",//lbs
    var rectangleZebraSleeve:String = "es", //language
    var nervousRainbowClass:String,//versionName
    var uncertainEasternSpecialBasket:String = "",//versionCode
    var femalePermissionSelf :String = "",//deviceId
    var spokenRadiationFormerChoiceBill:String = "",//imei
    var finalEmbassyLightning:String = "0.0.0.0" , //ip
    var basicPrivateHousework:String = "", //channel
    var gayEnvelopeSalesmanSilver:String = Build.MODEL,//systemMode
    var betterEarFoolishCourtSelf:String = "", //googleMobileNo
    var furnishedContinentSuggestionFlashlight:String = "",//googleUserAgent

)
data class HeaderParam(
    val unknownSpeakerSoap:String,//clientid
    val chiefPandaTerminalHolyBallet:String,//token
    val spanishSoilAdmission:String,//userid
    val passiveRubberAllAvenue:String,//currentUserId
    val basicPrivateHousework:String,//chanel
    val nervousRainbowClass:String,//versionName
    val uncertainEasternSpecialBasket:String,//versionCode
    val femalePermissionSelf:String,//deviceid
    val spokenRadiationFormerChoiceBill:String,//imei
    val arcticPilotFinalSleep:String,//mulFlag
    val newHungerRecentBeingSquid:String? = null,//vflag
    val fondLoudTroopModernPassage:String//deviceid
)





interface AdvertisingIdCallback {
    fun onAdvertisingIdInfoFetched(advertisingId: String?, isLATVisible: Boolean)
}
// Represents different states for the LatestNews screen
sealed class LatestNewsUiState {
    data class Success(val news: List<String>): LatestNewsUiState()
    data class Error(val exception: Throwable): LatestNewsUiState()
}