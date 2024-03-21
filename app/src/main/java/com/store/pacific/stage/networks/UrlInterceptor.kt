package com.store.pacific.stage.networks

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class BaseUrlInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request() //获取原始的originalRequest
        val oldUrl: HttpUrl = request.url //获取老的url
        val baseURL = "https://www.test.top".toHttpUrlOrNull()
        val newHttpUrl = oldUrl.newBuilder() //重建新的HttpUrl，需要重新设置的url部分
            .scheme(baseURL!!.scheme) //http协议如：http或者https
            .host(baseURL.host) //主机地址
            .port(baseURL.port) //端口
            .build()
        val builder: Request.Builder = request.newBuilder() //获取originalRequest的创建者builder
        val newRequest: Request = builder.url(newHttpUrl).build() //获取处理后的新newRequest
        return chain.proceed(newRequest)
    }
}

