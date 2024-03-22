package com.store.pacific.stage.repository

import com.store.pacific.stage.networks.BusinessOp
import com.store.pacific.stage.networks.NetworkService
import com.store.pacific.stage.networks.toRequestBody
import retrofit2.Call

class UniqRepository {

    private val api: BusinessOp = NetworkService.retrofit.create(BusinessOp::class.java)

    suspend fun getSmscode(num:String): Call<String> {
        val para = toRequestBody(num)
        return api.login_getVerifCode(para)
    }
}