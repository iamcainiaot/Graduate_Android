package com.example.zt.graduate.login.service;


import com.example.zt.graduate.login.model.response.LoginResponse;

import java.util.Map;

import mvp.BaseResponse;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 用户网络Service
 */
public interface UserService {

    /**
     * 登录请求
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<BaseResponse<LoginResponse>> doLogin(
            //   @Field("AppId") String appId,
            //   @Field("AppSecret") String appSecret,
            // username, password, title, description, imageUrl, price, stock, sales
            //   @Field("username") String username,
            //  @Field("password") String password,
            @FieldMap Map<String, String> params
    );
}
