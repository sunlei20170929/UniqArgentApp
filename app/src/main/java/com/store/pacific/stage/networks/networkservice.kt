package com.store.pacific.stage.networks

import com.coder.vincent.sharp_retrofit.call_adapter.flow.FlowCallAdapterFactory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//@Singleton
//@InstallIn(UniqRepository::class)
object NetworkService {

//    private fun getUrl() = WEB.TEST.url
    private fun getUrl() = "https://cmr.ultracreditosmx.com/"


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
        .baseUrl("https://cmr.ultracreditosmx.com/")
        .client(okHttpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory.create(async=false))
        .build()
}

sealed class WEB(val url: String) {
    object TEST : WEB("https://cmr.ultracreditosmx.com/uniqargent/")//182111111  111111
    object ONLINETEST : WEB("https://test.购买的域名.com/")
    object ONLINE : WEB("https://www.购买的域名.com/")

}