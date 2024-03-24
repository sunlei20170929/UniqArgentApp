package com.store.pacific.stage.networks

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject



class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        /**
         *
         *  .addHeader("client-id",_header.getString("client-id"))
         *             .addHeader("token",_header.getString("token"))
         *             .addHeader("userId",_header.getString("userId"))
         *             .addHeader("currentUserId",_header.getString("currentUserId"))
         *             .addHeader("channel",_header.getString("channel"))
         *             .addHeader("versionName",_header.getString("versionName"))
         *             .addHeader("versionCode",_header.getString("versionCode"))
         *             .addHeader("device-id",_header.getString("device-id"))
         *             .addHeader("imei",_header.getString("imei"))
         *             .addHeader("mulFlag",_header.getString("mulFlag"))
         *             .addHeader("v-flag",_header.getString("v-flag"))
         *             .addHeader("deviceId",_header.getString("deviceId"))
         * */
        val request = _header?.let {
            chain.request().newBuilder()
                .addHeader("unknownSpeakerSoap", it.getString("unknownSpeakerSoap"))
                .addHeader("chiefPandaTerminalHolyBallet",it.getString("chiefPandaTerminalHolyBallet"))
                .addHeader("spanishSoilAdmission",it.getString("spanishSoilAdmission"))
                .addHeader("passiveRubberAllAvenue",it.getString("passiveRubberAllAvenue"))
                .addHeader("basicPrivateHousework",it.getString("basicPrivateHousework"))
                .addHeader("nervousRainbowClass",it.getString("nervousRainbowClass"))
                .addHeader("uncertainEasternSpecialBasket",it.getString("uncertainEasternSpecialBasket"))
                .addHeader("femalePermissionSelf",it.getString("femalePermissionSelf"))
                .addHeader("spokenRadiationFormerChoiceBill",it.getString("spokenRadiationFormerChoiceBill"))
                .addHeader("arcticPilotFinalSleep",it.getString("arcticPilotFinalSleep"))
                .addHeader("newHungerRecentBeingSquid",it.getString("newHungerRecentBeingSquid"))
                .addHeader("fondLoudTroopModernPassage",it.getString("fondLoudTroopModernPassage"))
                .build()
        }


        if(request!=null) return chain.proceed(request)
        else return chain.proceed(chain.request())
    }

    private var _header : JSONObject?= null


    fun setHeader(headerjson:JSONObject){
        _header = headerjson
    }

    fun setHeaderObj(header:Header){
        val gson = Gson()
        setHeader(JSONObject(gson.toJson(header)))
    }

}

data class Header(
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
    val newHungerRecentBeingSquid:String,//vflag
    val fondLoudTroopModernPassage:String//deviceid
)

