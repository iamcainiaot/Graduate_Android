package com.example.zt.graduate.service;


import com.example.zt.graduate.choose_label.model.response.ChooseLabelResponse;
import com.example.zt.graduate.login.model.response.LoginResponse;

import java.util.List;
import java.util.Map;

import mvp.BaseResponse;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 用户网络Service
 */
public interface UserService {
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
     * 注册
     *
     * @return List<LoginResponse>
     */
    @FormUrlEncoded
    @POST("user/reg")
    Observable<BaseResponse<List<LoginResponse>>> doReg(@FieldMap Map<String, String> params);

    /**
     * 注册
     *
     * @return List<LoginResponse>
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<List<LoginResponse>>> doLogin(@FieldMap Map<String, String> params);

    /**
     * 选择标签
     *
     * @param params 参数
     * @return List<ChooseLabelResponse>
     */
    @FormUrlEncoded
    @POST("user/chooseLabel")
    Observable<BaseResponse<List<ChooseLabelResponse>>> doChooseLabel(@FieldMap Map<String, String> params);
}