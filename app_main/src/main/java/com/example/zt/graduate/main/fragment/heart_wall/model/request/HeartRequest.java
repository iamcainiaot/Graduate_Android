package com.example.zt.graduate.main.fragment.heart_wall.model.request;

import com.example.zt.graduate.app.UserApplication;

import mvp.BaseRequest;

/**
 * @author taozhu5
 * @date 2019年4月9日 14:18:17
 * @description 心声墙请求参数
 */
public class HeartRequest extends BaseRequest {

    public HeartRequest() {
        userId = UserApplication.getInstance().getCurrentUserId();
    }
}