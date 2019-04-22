package com.example.zt.graduate.main.activity.heart_wall.model.request;

import com.example.zt.graduate.app.UserApplication;

import mvp.BaseRequest;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 点赞请求参数
 */
public class SetLikeRequest extends BaseRequest {
    /**
     * 上传的图片地址
     */
    private String userId;
    /**
     * 发送内容
     */
    private String userIdLiked;
    /**
     * 被点赞的心声条目
     */
    private String heartId;
    /**
     * 是点赞还是取消点赞
     */
    private boolean isLike;

    public SetLikeRequest(String userId, String userIdLiked, String heartId, boolean isLike) {
        userId = UserApplication.getInstance().getCurrentUserId();
        this.userId = userId;
        this.userIdLiked = userIdLiked;
        this.heartId = heartId;
        this.isLike = isLike;
    }
}