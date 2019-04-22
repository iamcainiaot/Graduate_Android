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
     * 用户Id
     */
    private String userId;
    /**
     * 选择的标签
     */
    private String label;
    /**
     * 是否是男性
     */
    private boolean isMale;
    /**
     * 头像地址
     */
    private String imageUrl;

    public ChooseLabelRequest(String userId, String label, boolean isMale, String imageUrl) {
        this.userId = userId;
        this.label = label;
        this.isMale = isMale;
        this.imageUrl = imageUrl;
    }
}