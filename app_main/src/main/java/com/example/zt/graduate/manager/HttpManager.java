package com.example.zt.graduate.manager;


import com.example.zt.graduate.login.model.request.LoginRequest;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.login.service.UserService;

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

    //  /**
    //   * 登陆请求
    //   */
    //  public void doLogin(LoginRequest loginRequest, RxSubscribe<LoginResponse> subscribe) {
    //      mService.doLogin(loginRequest.getParams())
    //              .compose(RxHelper.<LoginResponse>handleResult())
    //              .subscribe(subscribe);
    //  }

    /**
     * 登陆请求
     */
    public void doLogin(LoginRequest loginRequest, RxSubscribe<List<LoginResponse>> subscribe) {
        mService.doLogin()
                .compose(RxHelper.<List<LoginResponse>>handleResult())
                .subscribe(subscribe);
    }
}
