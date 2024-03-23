package com.store.pacific.stage.repository

import androidx.lifecycle.LiveData
import com.store.pacific.stage.networks.BusinessOp
import com.store.pacific.stage.networks.NetworkService
import com.store.pacific.stage.networks.toRequestBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class UniqRepository {

    private val api: BusinessOp = NetworkService.retrofit.create(BusinessOp::class.java)

    suspend fun getSmscode(num:String): String = withContext(Dispatchers.IO) {
        val para = toRequestBody(num)
        api.getVcode(para)
    }
}