package com.example.zt.graduate.login.service;


import com.example.zt.graduate.login.model.response.LoginResponse;

import java.util.List;

import mvp.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 用户网络Service
 */
public interface UserService {

//  /**
//   * 登录请求
//   *
//   * @param params
//   * @returns
//   */
//  @FormUrlEncoded
//  @POST("reg")
//  Observable<BaseResponse<LoginResponse>> doLogin(
//          //   @Field("AppId") String appId,
//          //   @Field("AppSecret") String appSecret,
//          // username, password, title, description, imageUrl, price, stock, sales
//          //   @Field("username") String username,
//          //  @Field("password") String password,
//          @FieldMap Map<String, String> params
//  );

    /**
     * 登录请求
     *
     * @returns
     */
    @Streaming
    @GET("reg")
    Observable<BaseResponse<List<LoginResponse>>> doLogin();
}
