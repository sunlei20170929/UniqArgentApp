package com.store.pacific.stage.networks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface BusinessOp {

    //获取验证码
    // /login/getVerifCode@FormUrlEncoded

    @Multipart
    @POST("uniqargent/forgetfulList/studyEastToothpaste")
    fun getVcode(@HeaderMap headers:Map<String,String>,
                         @PartMap common:Map<String,@JvmSuppressWildcards RequestBody>,
                         @Part("triangleSilenceAge")triangleSilenceAge : RequestBody): Flow<ResponseBody>

    //注册/登录  /login/loginForSms
    @Multipart
    @POST("uniqargent/forgetfulList/standChallengingChoice")
    fun signMe(@HeaderMap headers: Map<String?, String?>?,@Part("triangleSilenceAge")triangleSilenceAge : RequestBody): LiveData<ResponseBody>

}


