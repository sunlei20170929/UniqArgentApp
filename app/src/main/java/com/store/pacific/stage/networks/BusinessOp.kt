package com.store.pacific.stage.networks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BusinessOp {

    //获取验证码  /login/getVerifCode
    @Multipart
    @POST("/forgetfulList/studyEastToothpaste")
    suspend fun getVcode(@Part("triangleSilenceAge")triangleSilenceAge : RequestBody): Flow<String>

    //注册/登录  /login/loginForSms
    @Multipart
    @POST("/forgetfulList/standChallengingChoice")
    suspend fun signMe(@Part("triangleSilenceAge")triangleSilenceAge : RequestBody): LiveData<String>
}


