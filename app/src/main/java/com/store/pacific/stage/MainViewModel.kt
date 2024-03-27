package com.store.pacific.stage

import android.app.Application
import android.app.LocaleManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.LocaleList
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.store.pacific.stage.repository.UniqRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.Locale


class MainViewModel(private val repo: UniqRepository,
                    private val savedStateHandle: SavedStateHandle,
                    private val application: Application,
): AndroidViewModel(application){

    private val LANGUAGE = "language"
    private val REMINDER = "reminder"


    val shownSplash = mutableStateOf(SplashState.Shown)

    var languageSetting: Int? = if(savedStateHandle.get<Int>(LANGUAGE)==null)  0
    else savedStateHandle[LANGUAGE]

    var hasRead:Boolean = savedStateHandle.get<Boolean>(REMINDER) == true



    fun setLanguage(context: Context, lan:Int){
        viewModelScope.launch(Dispatchers.Default) {
            languageSetting = lan
            savedStateHandle[LANGUAGE] = languageSetting

// Call this on the main thread as it may require Activity.restart()

            when(lan){
                1->{
//                    context.getSystemService(LocaleManager::class.java
//                    ).applicationLocales = LocaleList(Locale.forLanguageTag("en-GB"))
                    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en-GB")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }

                2->{
//                    context.getSystemService(LocaleManager::class.java
//                    ).applicationLocales = LocaleList(Locale.forLanguageTag("fr"))
                    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("fr")
                    AppCompatDelegate.setApplicationLocales(appLocale)
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

    companion object{
        /**
         * header
         * */
        var unknownSpeakerSoap = "134"//client-id
        var chiefPandaTerminalHolyBallet = "" //token
        var spanishSoilAdmission = "134" //userId
        var passiveRubberAllAvenue = ""//currentUserId
        var basicPrivateHousework = "googleplay"//channel
        var uncertainEasternSpecialBasket = "0"//versionCode = 0
        var nervousRainbowClass = "" //versionName
        var fondLoudTroopModernPassage = ""//device-id
        var spokenRadiationFormerChoiceBill = ""//imei
        var arcticPilotFinalSleep = "1"//mulFlag
        var newHungerRecentBeingSquid:Boolean? = true//v-flag
        var femalePermissionSelf = ""//deviceId

        /**
         * common params
         * */

        var unfitVariousBrokenCity = "134"//appssid
        var highPacket = "0,0"//lbs
        var rectangleZebraSleeve = "es" //language
        var finalEmbassyLightning = "0.0.0.0"  //ip
        var gayEnvelopeSalesmanSilver = Build.MODEL//systemMode
        var betterEarFoolishCourtSelf = "" //googleMobileNo
        var furnishedContinentSuggestionFlashlight = ""//googleUserAgent
        lateinit var headerP:HeaderParam
        lateinit var commonP:CommonParam
        var headerMap:MutableMap<String,String> = mapOf("unknownSpeakerSoap" to "unknownSpeakerSoap").toMutableMap()
        var commonMap:MutableMap<String,String> = mapOf("unfitVariousBrokenCity" to "unfitVariousBrokenCity").toMutableMap()




        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()
                return MainViewModel((application as UniqArgentApp).uniqRepository, savedStateHandle,application) as T
            }
        }

    }
    init{
        viewModelScope.launch(Dispatchers.Default) {
            getVersion()
            getchiefPandaTerminalHolyBallet()
            getfondLoudTroopModernPassage()
            getIPAddress(true)

            headerP = HeaderParam(unknownSpeakerSoap = unknownSpeakerSoap,
                chiefPandaTerminalHolyBallet = chiefPandaTerminalHolyBallet,
                spanishSoilAdmission = spanishSoilAdmission,
                passiveRubberAllAvenue = passiveRubberAllAvenue,
                basicPrivateHousework = basicPrivateHousework,
                nervousRainbowClass = nervousRainbowClass,
                uncertainEasternSpecialBasket = uncertainEasternSpecialBasket,
                femalePermissionSelf = femalePermissionSelf,
                spokenRadiationFormerChoiceBill = spokenRadiationFormerChoiceBill,
                arcticPilotFinalSleep = arcticPilotFinalSleep,
                fondLoudTroopModernPassage = fondLoudTroopModernPassage,
                newHungerRecentBeingSquid = newHungerRecentBeingSquid


            )
            headerMap["unknownSpeakerSoap"] = unknownSpeakerSoap
            headerMap["chiefPandaTerminalHolyBallet"] = chiefPandaTerminalHolyBallet
            headerMap["spanishSoilAdmission"] = spanishSoilAdmission
            headerMap["passiveRubberAllAvenue"] = passiveRubberAllAvenue
            headerMap["basicPrivateHousework"] = basicPrivateHousework
            headerMap["nervousRainbowClass"] = nervousRainbowClass
            headerMap["uncertainEasternSpecialBasket"] = uncertainEasternSpecialBasket
            headerMap["femalePermissionSelf"] = femalePermissionSelf
            headerMap["spokenRadiationFormerChoiceBill"] = spokenRadiationFormerChoiceBill
            headerMap["arcticPilotFinalSleep"] = arcticPilotFinalSleep
            headerMap["fondLoudTroopModernPassage"] = fondLoudTroopModernPassage


            commonP = CommonParam(
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
            commonMap["unfitVariousBrokenCity"] = unfitVariousBrokenCity
            commonMap["spanishSoilAdmission"] = spanishSoilAdmission
            commonMap["highPacket"] = highPacket
            commonMap["rectangleZebraSleeve"] = rectangleZebraSleeve
            commonMap["nervousRainbowClass"] = nervousRainbowClass
            commonMap["uncertainEasternSpecialBasket"] = uncertainEasternSpecialBasket
            commonMap["femalePermissionSelf"] = femalePermissionSelf
            commonMap["spokenRadiationFormerChoiceBill"] = spokenRadiationFormerChoiceBill
            commonMap["finalEmbassyLightning"] = finalEmbassyLightning
            commonMap["basicPrivateHousework"] = basicPrivateHousework
            commonMap["gayEnvelopeSalesmanSilver"] = gayEnvelopeSalesmanSilver
            commonMap["betterEarFoolishCourtSelf"] = betterEarFoolishCourtSelf
            commonMap["furnishedContinentSuggestionFlashlight"] = furnishedContinentSuggestionFlashlight

        }

    }
    fun getSms(num:String){
        viewModelScope.launch {
            repo.getSmscode(headerMap, commonMap ,num)
                ?.catch {e->
                _sms.value = e.toString()
            }?.flowOn(Dispatchers.Main)
                ?.collect{
                val ret = JSONObject(it.string())
                _sms.value = ret.toString()
            }
        }
    }

    private suspend fun getVersion(){
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
    private suspend fun getchiefPandaTerminalHolyBallet(){
        chiefPandaTerminalHolyBallet =  savedStateHandle.get<String>("chiefPandaTerminalHolyBallet").toString()
        passiveRubberAllAvenue = chiefPandaTerminalHolyBallet
    }
    //device-id
    private suspend fun getfondLoudTroopModernPassage(){
        fondLoudTroopModernPassage = Settings.Secure.getString(application.applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
        spokenRadiationFormerChoiceBill = fondLoudTroopModernPassage
        femalePermissionSelf = fondLoudTroopModernPassage
    }

    private suspend fun getIPAddress(useIPv4: Boolean) {
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
    val newHungerRecentBeingSquid:Boolean? = true,//vflag
    val fondLoudTroopModernPassage:String//deviceid,

)





interface AdvertisingIdCallback {
    fun onAdvertisingIdInfoFetched(advertisingId: String?, isLATVisible: Boolean)
}
// Represents different states for the LatestNews screen
sealed class LatestNewsUiState {
    data class Success(val news: List<String>): LatestNewsUiState()
    data class Error(val exception: Throwable): LatestNewsUiState()
}