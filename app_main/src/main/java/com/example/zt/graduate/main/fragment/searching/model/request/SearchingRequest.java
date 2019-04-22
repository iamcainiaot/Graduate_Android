package com.example.zt.graduate.main.fragment.searching.model.request;

import com.example.zt.graduate.app.UserApplication;

import mvp.BaseRequest;

/**
 * @author taozhu5
 * @date 2019年4月9日 14:18:17
 * @description 匹配好友请求参数
 */
public class SearchingRequest extends BaseRequest {

    public SearchingRequest() {
        userId = UserApplication.getInstance().getCurrentUserId();
    }
}