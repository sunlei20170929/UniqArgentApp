package com.store.pacific.stage.networks

import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

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
}