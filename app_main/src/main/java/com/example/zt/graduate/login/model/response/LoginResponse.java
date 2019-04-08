package com.example.zt.graduate.login.model.response;

import mvp.BaseResponse;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆响应数据
 */
public class LoginResponse extends BaseResponse {
    /**
     * id  自增主键
     */
    private Long id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.userName = userName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", label='" + label + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}