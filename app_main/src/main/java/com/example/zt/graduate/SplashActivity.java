package com.example.zt.graduate;

import android.os.Handler;

import com.example.zt.graduate.app.UserApplication;
import com.example.zt.graduate.db.greendaogen.DaoSession;
import com.example.zt.graduate.login.LoginActivity;
import com.example.zt.graduate.main.MainActivity;

import lib_utils.db.entity.UserInfoTable;
import mvp.BaseMvpActivity;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 导航图
 */
public class SplashActivity extends BaseMvpActivity {
    /**
     * GreenDao相关操作
     */
    private UserApplication mUserApplication;
    private DaoSession mDaoSession;
    private UserInfoTable mUserInfoTable;

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
                mUserApplication = (UserApplication) getApplication();
                mDaoSession = mUserApplication.getDaoSession();
                mUserInfoTable = mUserApplication.getUserInfoTable();
                // LoginActivity.start(_this());
                if (mUserInfoTable != null) {
                    MainActivity.start(SplashActivity.this);
                    // ChooseLabelActivity.start(this);
                    finish();
                } else {
                    LoginActivity.start(_this());
                    finish();
                }
            }
        }, 1000);
    }

    @Override
    public void setStatus() {
        setStatusMVP();
    }
}
