package com.store.pacific.stage.networks

import android.text.TextUtils
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

class LogInterceptor : Interceptor {
    var TAG = "----//----"
    private val UTF8 = Charset.forName("UTF-8")

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        val mediaType = response.body!!.contentType()
        val cb = StringBuilder()
        cb.append(response.body!!.string())
        Log.d(TAG, "\n")
        Log.d(TAG, "----------Start----------------")
        Log.d(TAG, "| $request")
        val headers = request.headers
        if (null != headers) {
            val hb = StringBuilder()
            var i = 0
            val count = headers.size
            while (i < count) {
                val name = headers.name(i)
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equals(
                        name,
                        ignoreCase = true
                    ) && !"Content-Length".equals(name, ignoreCase = true)
                ) {
                    hb.append("\"" + name + "\":\"" + headers.value(i) + "\"")
                }
                if (i != count - 1) {
                    hb.append(",")
                }
                i++
            }
            Log.d(TAG, "| Header:{$hb}")
        }
        val method = request.method
        if (TextUtils.equals("POST", method)) {
            val sb = StringBuilder()
            val requestBody = request.body
            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)
                var charset = UTF8
                val contentType = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                sb.append(buffer.readString(charset))
                Log.d(TAG, "| RequestParams:{$sb}")
            }
        }
        Log.d(TAG, "| Response:$cb")
        Log.d(TAG, "----------End:" + duration + "毫秒----------")

        // 打印body后原ResponseBody会被清空，需要重新设置body
        return response.newBuilder()
            .body(okhttp3.ResponseBody.create(mediaType, cb.toString()))
            .build()
    }
}