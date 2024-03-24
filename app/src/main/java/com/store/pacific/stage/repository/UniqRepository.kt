package com.store.pacific.stage.repository

import com.store.pacific.stage.CommonParam
import com.store.pacific.stage.HeaderParam
import com.store.pacific.stage.networks.BusinessOp
import com.store.pacific.stage.networks.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UniqRepository @Inject constructor(){

    private val api: BusinessOp = NetworkService.retrofit.create(BusinessOp::class.java)

    suspend fun getSmscode(header: HeaderParam, common: CommonParam, num:String): Flow<String> = withContext(Dispatchers.IO) {
        val para = toRequestBody(num)
        api.getVcode(para)
    }

    fun  generateRequestBody(requestDataMap:Map<String, String>):Map<String, RequestBody> {
        var requestBodyMap:MutableMap<String, RequestBody> = emptyMap<String, RequestBody>().toMutableMap()
        for(key in requestDataMap.keys){
            val requestBody =
                (requestDataMap[key]?:"").toRequestBody("multipart/form-data".toMediaTypeOrNull())

            requestBodyMap[key] = requestBody
        }
        return requestBodyMap;
    }

    fun toRequestBody(key:String): RequestBody {
        return key.toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }

    fun strToRequestBody(p: String): RequestBody {
        return p.toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }


}