package com.store.pacific.stage.networks

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.platform.Platform
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import java.nio.charset.Charset

object NetworkService {


    private val URL = "http://18.228.39.120/uniqargent/" //for release

    private fun getUrl() = WEB.TEST.url

    private var okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(LogInterceptor())
        .addInterceptor { chain ->
            val request = chain.request()
            val requestbuild = request.newBuilder()
            requestbuild.addHeader("Content-Type", "application/json")
            return@addInterceptor chain.proceed(requestbuild.build())
        }

    private val retrofit = Retrofit.Builder()
        .baseUrl(getUrl())
        .client(okHttpClient.build())
//        .addCallAdapterFactory(FlowCallAdapterFactory.create())
//        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())

        .build()

//    private val romUpdateService: RomUpdateCheck = retrofit.create(RomUpdateCheck::class.java)
//
//    fun romUpdateCheck(arg0:Map<String,String>,arg1:String,arg2:String): Flow<ResponseBody> {
//        return romUpdateService.getRomInfo(arg0,arg1,arg2,"0")
//    }


}

sealed class WEB(val url: String) {
    object TEST : WEB("http://18.228.39.120/uniqargent/")
    object ONLINETEST : WEB("https://test.购买的域名.com/")
    object ONLINE : WEB("https://www.购买的域名.com/")

}