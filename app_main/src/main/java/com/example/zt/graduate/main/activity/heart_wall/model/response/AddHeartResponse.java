package com.example.zt.graduate.main.activity.heart_wall.model.response;

import mvp.BaseResponse;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 增加心声
 */
public class AddHeartResponse extends BaseResponse {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 图片
     */
    private String imageUrl;

    /**
     * 内容
     */
    private String content;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HeartWallResult{" +
                "userId='" + userId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}