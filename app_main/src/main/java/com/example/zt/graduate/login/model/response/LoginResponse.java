package com.example.zt.graduate.login.model.response;

import java.util.List;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆响应数据
 */
public class LoginResponse {
    /**
     * id  自增主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
