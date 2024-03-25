package com.store.pacific.stage.networks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface BusinessOp {

    //获取验证码  /login/getVerifCode
    @Multipart
    @POST("/forgetfulList/studyEastToothpaste")
    suspend fun getVcode(@HeaderMap headers: Map<String, String>,@PartMap common:Map<String,RequestBody>,@Part("triangleSilenceAge")triangleSilenceAge : RequestBody): Flow<String>

    //注册/登录  /login/loginForSms
    @Multipart
    @POST("/forgetfulList/standChallengingChoice")
    suspend fun signMe(@HeaderMap headers: Map<String?, String?>?,@Part("triangleSilenceAge")triangleSilenceAge : RequestBody): LiveData<String>

}


