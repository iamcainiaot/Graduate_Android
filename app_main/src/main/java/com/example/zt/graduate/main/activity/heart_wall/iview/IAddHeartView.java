package com.example.zt.graduate.main.activity.heart_wall.iview;

import com.example.zt.graduate.main.activity.heart_wall.model.response.AddHeartResponse;

import java.util.List;

import mvp.IAddPresenterView;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆IView接口层
 */
public interface IAddHeartView extends IAddPresenterView {
    /**
     * 登陆开始
     */
    void onAddAllHeartStart();

    /**
     * 响应完成
     */
    void onAddAllHeartReturned(List<AddHeartResponse> addAllHeartResponses);

    /**
     * 响应错误
     */
    void onAddAllHeartError();
}
