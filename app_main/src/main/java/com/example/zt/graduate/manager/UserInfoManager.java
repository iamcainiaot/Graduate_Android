package com.example.zt.graduate.manager;


import com.example.zt.graduate.choose_label.model.request.ChooseLabelRequest;
import com.example.zt.graduate.choose_label.model.response.ChooseLabelResponse;
import com.example.zt.graduate.login.model.request.LoginRequest;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.main.fragment.searching.model.request.SearchingRequest;
import com.example.zt.graduate.main.fragment.searching.model.response.SearchingResponse;
import com.example.zt.graduate.service.UserInfoService;

import java.util.List;

import lib_utils.net.NetHttpApi;
import lib_utils.net.RxHelper;
import lib_utils.net.RxSubscribe;


/**
 * @author taozhu5
 * @date 2019/3/13 10:42
 * @description 用户信息Http管理类
 */
public class UserInfoManager {
    private UserInfoService mService;

    public UserInfoManager() {
        mService = NetHttpApi.getInstance().getService(UserInfoService.class);
    }

    /**
     * 登陆请求
     */
    public void doLogin(LoginRequest loginRequest, RxSubscribe<List<LoginResponse>> subscribe) {
        mService.doLogin(loginRequest.getParams())
                .compose(RxHelper.<List<LoginResponse>>handleResult())
                .subscribe(subscribe);
    }

    /**
     * 选择标签请求
     */
    public void doChooseLabel(ChooseLabelRequest chooseLabelRequest, RxSubscribe<List<ChooseLabelResponse>> subscribe) {
        mService.doChooseLabel(chooseLabelRequest.getParams())
                .compose(RxHelper.<List<ChooseLabelResponse>>handleResult())
                .subscribe(subscribe);
    }

    /**
     * 获取匹配的好友
     */
    public void doSearching(SearchingRequest searchingRequest, RxSubscribe<List<SearchingResponse>> subscribe) {
        mService.doSearching(searchingRequest.getParams())
                .compose(RxHelper.<List<SearchingResponse>>handleResult())
                .subscribe(subscribe);
    }
}