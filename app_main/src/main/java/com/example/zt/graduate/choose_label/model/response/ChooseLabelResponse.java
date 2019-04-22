package com.example.zt.graduate.choose_label.model.response;

import mvp.BaseResponse;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 选择标签响应数据
 */
public class ChooseLabelResponse extends BaseResponse {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名 （手机号）
     */
    private String userName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 个性标签
     */
    private String label;
    /**
     * 用户头像
     */
    private String imageUrl;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }


    @Override
    public String toString() {
        return "UserResult{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", label='" + label + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}