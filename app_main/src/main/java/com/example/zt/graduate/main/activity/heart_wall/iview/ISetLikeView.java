package com.example.zt.graduate.main.activity.heart_wall.iview;

import com.example.zt.graduate.main.activity.heart_wall.model.response.AddHeartResponse;
import com.example.zt.graduate.main.activity.heart_wall.model.response.SetLikeResponse;

import java.util.List;

import mvp.IAddPresenterView;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 点赞IView接口层
 */
public interface ISetLikeView extends IAddPresenterView {
    /**
     * 登陆开始
     */
    void onSetLikeStart();

    /**
     * 响应完成
     */
    void onSetLikeReturned(List<SetLikeResponse> setLikeResponses);

    /**
     * 响应错误
     */
    void onSetLikeError();
}
