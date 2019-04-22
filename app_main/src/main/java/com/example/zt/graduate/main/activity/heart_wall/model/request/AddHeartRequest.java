package com.example.zt.graduate.main.activity.heart_wall.model.request;

import com.example.zt.graduate.app.UserApplication;

import mvp.BaseRequest;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆请求参数
 */
public class AddHeartRequest extends BaseRequest {
    /**
     * 上传的图片地址
     */
    private String imageUrl;
    /**
     * 发送内容
     */
    private String content;

    public AddHeartRequest(String imageUrl, String content) {
        userId = UserApplication.getInstance().getCurrentUserId();
        this.imageUrl = imageUrl;
        this.content = content;
    }
}