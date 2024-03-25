package com.store.pacific.stage.networks

//import com.coder.vincent.sharp_retrofit.call_adapter.flow.FlowCallAdapterFactory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


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
        }.apply { setSSL(this) }


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://cmr.ultracreditosmx.com/")
        .client(okHttpClient.build())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun setSSL(builder: OkHttpClient.Builder): OkHttpClient.Builder? {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate?> {
                return arrayOfNulls(0)
            }
        })
        var sslContext: SSLContext? = null
        try {
            sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (sslContext != null) {
            builder.sslSocketFactory(sslContext.socketFactory, object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }
            })
        }
        return builder
    }
}

sealed class WEB(val url: String) {
    object TEST : WEB("https://cmr.ultracreditosmx.com/uniqargent/")//182111111  111111
    object ONLINETEST : WEB("https://test.购买的域名.com/")
    object ONLINE : WEB("https://www.购买的域名.com/")

}


