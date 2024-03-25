package com.store.pacific.stage.networks

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {


    private val URL = "http://18.228.39.120/uniqargent/" //for release

    private fun getUrl() = WEB.TEST.url

    private var okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .run {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)//错误重连
           .addInterceptor(LogInterceptor())
                .addInterceptor { chain ->
                    val request = chain.request()
                    val requestbuild = request.newBuilder()
                    requestbuild.addHeader("Content-Type", "application/json")
                    return@addInterceptor chain.proceed(requestbuild.build())
                }
                .addInterceptor(HeaderInterceptor())
        }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(getUrl())
        .client(okHttpClient.build())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
//        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

sealed class WEB(val url: String) {
    object TEST : WEB("http://18.228.39.120/uniqargent/")
    object ONLINETEST : WEB("https://test.购买的域名.com/")
    object ONLINE : WEB("https://www.购买的域名.com/")

}