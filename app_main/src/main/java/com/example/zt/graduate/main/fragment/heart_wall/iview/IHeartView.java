package com.example.zt.graduate.main.fragment.heart_wall.iview;

import com.example.zt.graduate.main.fragment.heart_wall.model.response.HeartResponse;

import java.util.List;

import mvp.IAddPresenterView;

/**
 * @author taozhu5
 * @date 2019年4月9日 14:18:23
 * @description 心声墙IView接口层
 */
public interface IHeartView extends IAddPresenterView {
    /**
     * 登陆开始
     */
    void onHeartStart();

    /**
     * 响应完成
     *
     * @param loginResponse 返回数据
     */
    void onHeartReturned(List<HeartResponse> loginResponse);

    /**
     * 错误返回
     */
    void onHeartError();
}
