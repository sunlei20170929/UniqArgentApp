package com.store.pacific.stage.repository

import com.store.pacific.stage.networks.BusinessOp
import com.store.pacific.stage.networks.NetworkService
import com.store.pacific.stage.networks.toRequestBody
import retrofit2.Call

class UniqRepository {

    private val api: BusinessOp = NetworkService.retrofit.create(BusinessOp::class.java)

    // 创建请求参数
//    User user = new User();
//    user.setUsername("Alice");
//    user.setAvatar(new File("/path/to/avatar.jpg"));
//
//// 创建 RequestBody
//    RequestBody username = RequestBody.create(MediaType.parse("text/plain"), user.getUsername());
//
//// 创建 MultipartBody.Part
//    RequestBody avatarFile = RequestBody.create(MediaType.parse("image/jpeg"), user.getAvatar());
//    MultipartBody.Part avatarPart = MultipartBody.Part.createFormData("avatar", user.getAvatar().getName(), avatarFile);
//
//// 执行请求
//    ApiService apiService = retrofit.create(ApiService.class);
//    Call<ResponseBody> call = apiService.uploadUser(username, avatarPart);
//    call.enqueue(new Callback<ResponseBody>() {
//        // 处理响应结果
//    });
//    public interface ApiService {
//        @Multipart
//        @POST("upload")
//        Call<ResponseBody> uploadUser(@Part("username") RequestBody username,
//        @Part MultipartBody.Part avatar);
//    }

    suspend fun getSmscode(num:String): Call<String> {
        val para = toRequestBody(num)
        return api.login_getVerifCode(para)
    }
}