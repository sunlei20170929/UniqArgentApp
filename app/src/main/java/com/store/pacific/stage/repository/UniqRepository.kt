package com.store.pacific.stage.repository

import android.util.Log
import com.store.pacific.stage.networks.BusinessOp
import com.store.pacific.stage.networks.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.lang.reflect.Field
import java.util.Objects

import kotlin.reflect.KProperty1



class UniqRepository{ //@Inject constructor(private val api: BusinessOp){
    companion object{
        private val api: BusinessOp = NetworkService.retrofit.create(BusinessOp::class.java)
    }
    suspend fun getSmscode( headParam:Map<String,String>,commonMap: Map<String,String>, num:String): Flow<ResponseBody> = withContext(Dispatchers.IO) {
        val para = toRequestBody(num)
//        val headerMap = beanToMap(header) as Map<String, String>
//        Log.w("param","headerMap is ${headerMap.toString()}")
//        val commonMap = beanToMap(common)
        val commonParam:Map<String,RequestBody> = generateRequestBody(commonMap!!)
        Log.w("param","commonParam is ${commonParam.toString()}")
        api.getVcode(headParam,
            commonParam,
            para)
//        headerMap?.let { api.getVcode(it,commonParam,para) }
    }

    fun  generateRequestBody(requestDataMap:Map<String, String>):Map<String, RequestBody> {
        var requestBodyMap:MutableMap<String, RequestBody> = emptyMap<String, RequestBody>().toMutableMap()
        for(key in requestDataMap.keys){
            val requestBody = (requestDataMap[key]?:"").toRequestBody("multipart/form-data".toMediaTypeOrNull())
            requestBodyMap[key] = requestBody
        }
        return requestBodyMap;
    }


    @Throws(IllegalAccessException::class)
    fun beanToMap(`object`: Any): Map<String, String>? {
        val map: MutableMap<String, String> = HashMap()
        val fields: Array<Field> = `object`.javaClass.declaredFields
        for (field in fields) {
            field.isAccessible = true
            map[field.name] = field.get(`object`)?.toString()?:""
        }
        return map
    }

    fun toMap(obj: Objects): MutableMap<String, Any?> {

        val map = mutableMapOf<String, Any?>()
        val properties = Objects::class.members.filterIsInstance<KProperty1<Objects, String>>()
        for (property in properties) {
            map[property.name] = property.get(obj)?:""
        }
        return map
    }


    fun toRequestBody(key:String): RequestBody {
        return key.toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }

    fun strToRequestBody(p: String): RequestBody {
        return p.toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }


}