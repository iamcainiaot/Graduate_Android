package com.example.zt.graduate;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.example.administrator.graduate_android.R;
import com.example.zt.graduate.login.LoginActivity;
import com.example.zt.graduate.login.iview.ILoginView;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.login.presenter.LoginPresenter;
import com.example.zt.graduate.main.MainActivity;

import lib_utils.MyLogUtil;
import mvp.BaseMvpActivity;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 导航图
 */
public class SplashActivity extends BaseMvpActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        // do nothing
    }

    @Override
    public void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActivity.start(_this());
                finish();
            }
        }, 1000);
    }

    @Override
    public void setStatus() {
        setStatusMVP();
    }
}
