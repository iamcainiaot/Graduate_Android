package com.example.zt.graduate.login.model.request;

import com.example.zt.graduate.app.UserApplication;

import mvp.BaseRequest;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆请求参数
 */
public class LoginRequest extends BaseRequest {
    /**
     * 登陆手机号
     */
    private String userName;

    public LoginRequest(String userName) {
        userId = UserApplication.getInstance().getCurrentUserId();
        this.userName = userName;
    }
}