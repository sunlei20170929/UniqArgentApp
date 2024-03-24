package com.store.pacific.stage.repository

import com.store.pacific.stage.networks.BusinessOp
import com.store.pacific.stage.networks.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UniqRepository @Inject constructor(){

    private val api: BusinessOp = NetworkService.retrofit.create(BusinessOp::class.java)

    suspend fun getSmscode(num:String): Flow<String> = withContext(Dispatchers.IO) {
//        val para = toRequestBody(num)
//        api.getVcode(para)
        flow {
            for (i in 1..3) {
                delay(100)
                emit(i.toString())
            }
        }
    }


}