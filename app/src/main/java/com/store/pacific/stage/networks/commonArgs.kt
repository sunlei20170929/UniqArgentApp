package com.store.pacific.stage.networks

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


data class commonArgs(
    val appssid:String,
    val userId:String,
    val lbs:String = "0,0",
    val language:String = "es",
    val versionName:String,
    val versionCode:String,
    val deviceId:String,
    val imei:String,
    val ip:String,
    val channel:String = "googleplay",
    val systemMode:String,
    val googleMobileNo:String,
    val googleGaid:String,
    val googleUserAgent:String
    )
fun  generateRequestBody(requestDataMap:Map<String, String>):Map<String, RequestBody> {
    var requestBodyMap:MutableMap<String, RequestBody> = emptyMap<String, RequestBody>().toMutableMap()
    for(key in requestDataMap.keys){
        val requestBody =
            (requestDataMap[key]?:"").toRequestBody("multipart/form-data".toMediaTypeOrNull())

        requestBodyMap[key] = requestBody
    }
    return requestBodyMap;
}

fun toRequestBody(key:String):RequestBody{
    return key.toRequestBody("multipart/form-data".toMediaTypeOrNull())
}

fun strToRequestBody(p: String): RequestBody {
    return p.toRequestBody("multipart/form-data".toMediaTypeOrNull())
}



