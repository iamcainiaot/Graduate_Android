package com.example.zt.graduate.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.administrator.graduate_android.R;
import com.example.zt.graduate.login.iview.ILoginView;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.login.presenter.LoginPresenter;

import lib_utils.MyLogUtil;
import mvp.BaseMvpActivity;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆
 */
public class LoginActivity extends BaseMvpActivity implements ILoginView {
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
        LoginPresenter mLoginPresenter;
        View tvLogin = $(R.id.tv_login);
        tvLogin.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        mLoginPresenter = new LoginPresenter(this, this);
        mLoginPresenter.doLogin("18700000000", "18700000000");

    }

    @Override
    public void onLoginStart() {
        MyLogUtil.d("开始登陆");
    }

    @Override
    public void onLoginReturned(LoginResponse loginResponse) {
        MyLogUtil.d("登陆成功返回数据：" + loginResponse.toString());
        MyLogUtil.d(loginResponse.toString());
    }
}
