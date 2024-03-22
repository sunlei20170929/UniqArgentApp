package com.store.pacific.stage.networks

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject


data class Header(
    val id:String,
    val token:String,
    val userId:String,
    val currentUserId:String,
    val channel:String,
    val versionName:String,
    val versionCode:String,
    val device_Id:String,
    val imei:String,
    val mulFlag:String,
    val vFlag:String,
    val deviceId:String
)
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("client-id",_header.getString("client-id"))
            .addHeader("token",_header.getString("token"))
            .addHeader("userId",_header.getString("userId"))
            .addHeader("currentUserId",_header.getString("currentUserId"))
            .addHeader("channel",_header.getString("channel"))
            .addHeader("versionName",_header.getString("versionName"))
            .addHeader("versionCode",_header.getString("versionCode"))
            .addHeader("device-id",_header.getString("device-id"))
            .addHeader("imei",_header.getString("imei"))
            .addHeader("mulFlag",_header.getString("mulFlag"))
            .addHeader("v-flag",_header.getString("v-flag"))
            .addHeader("deviceId",_header.getString("deviceId"))
            .build()

        return chain.proceed(request)

    }

    var _header = JSONObject()


    fun setHeader(headerjson:JSONObject){
        _header = headerjson
    }

    fun setHeaderObj(header:Header){
        val gson = Gson()
        setHeader(JSONObject(gson.toJson(header)))
    }
}

