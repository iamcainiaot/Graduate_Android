package com.example.zt.graduate.login;

import android.content.Context;
import android.content.Intent;

import com.example.administrator.graduate_android.R;
import com.example.zt.graduate.login.iview.ILoginView;
import com.example.zt.graduate.login.model.response.LoginResponse;

import mvp.BaseMvpActivity;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆
 */
public class LoginActivity extends BaseMvpActivity implements ILoginView {
    /**
     * 启动此页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        // do nothing
    }

    @Override
    public void initView() {
        //View header = $(R.id.);
    }

    @Override
    public void onLoginStart() {
        // do nothing
    }

    @Override
    public void onLoginReturned(LoginResponse loginResponse) {
        // do nothing
    }
}
