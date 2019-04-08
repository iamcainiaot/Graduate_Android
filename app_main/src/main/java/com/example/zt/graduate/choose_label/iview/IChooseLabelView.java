package com.example.zt.graduate.choose_label.iview;

import com.example.zt.graduate.choose_label.model.response.ChooseLabelResponse;

import java.util.List;

import mvp.IAddPresenterView;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 选择标签IView接口层
 */
public interface IChooseLabelView extends IAddPresenterView {
    /**
     * 登陆开始
     */
    void onChooseLabelStart();

    /**
     * 响应完成
     *
     * @param loginResponse 返回数据
     */
    void onChooseLabelReturned(List<ChooseLabelResponse> loginResponse);
}
