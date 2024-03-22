package com.store.pacific.stage.networks

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BusinessOp {
    @Multipart
    @POST("/login/getVerifCode")
    suspend fun login_getVerifCode(@Part phone: RequestBody): Call<String>
}


