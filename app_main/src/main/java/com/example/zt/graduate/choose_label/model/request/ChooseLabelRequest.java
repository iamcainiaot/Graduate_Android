package com.example.zt.graduate.choose_label.model.request;

import com.example.zt.graduate.app.UserApplication;

import mvp.BaseRequest;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 选择标签请求参数
 */
public class ChooseLabelRequest extends BaseRequest {
    /**
     * 选择的标签
     */
    private String label;

    public ChooseLabelRequest(String label) {
        userId = UserApplication.getInstance().getCurrentUserId();
        this.label = label;
    }
}