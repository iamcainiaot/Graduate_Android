package com.example.zt.graduate.manager;


import com.example.zt.graduate.choose_label.model.request.ChooseLabelRequest;
import com.example.zt.graduate.choose_label.model.response.ChooseLabelResponse;
import com.example.zt.graduate.login.model.request.LoginRequest;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.service.UserService;

import java.util.List;

import lib_utils.net.NetHttpApi;
import lib_utils.net.RxHelper;
import lib_utils.net.RxSubscribe;


/**
 * @author taozhu5
 * @date 2019/3/13 10:42
 * @description Http管理类
 */
public class HttpManager {
    private UserService mService;

    public HttpManager() {
        mService = NetHttpApi.getInstance().getService(UserService.class);
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
}