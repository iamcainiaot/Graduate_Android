package com.example.zt.graduate.login.iview;

import com.example.zt.graduate.login.model.response.LoginResponse;

import java.util.List;

import mvp.IAddPresenterView;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆IView接口层
 */
public interface ILoginView extends IAddPresenterView {
    /**
     * 登陆开始
     */
    void onLoginStart();

    /**
     * 响应完成
     *
     * @param loginResponse 返回数据
     */
    void onLoginReturned(List<LoginResponse> loginResponse);
}
