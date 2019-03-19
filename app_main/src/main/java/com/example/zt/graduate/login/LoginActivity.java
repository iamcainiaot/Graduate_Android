package com.example.zt.graduate.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.administrator.graduate_android.R;
import com.example.zt.graduate.app.UserApplication;
import com.example.zt.graduate.choose_label.ChooseLabelActivity;
import com.example.zt.graduate.login.iview.ILoginView;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.login.presenter.LoginPresenter;
import com.example.zt.graduate.main.MainActivity;

import java.util.List;

import lib_utils.MyLogUtil;
import lib_utils.db.greendaogen.DaoSession;
import mvp.BaseMvpActivity;
import mvp.BaseResponse;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆
 */
public class LoginActivity extends BaseMvpActivity implements ILoginView {

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setStatus() {
        setStatusMVP();
    }

    private UserApplication mApplication;
    private DaoSession mDaoSession;

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        mApplication = (UserApplication) getApplication();
        mDaoSession = mApplication.getDaoSession();
        // DaoSession对象已经进行过初始化了
        // 增
        //   mDaoSession.insertOrReplace();
        //   mDaoSession.insert();
        // 改
        // mDaoSession.update();
        // ...
    }

    @Override
    public void initView() {
        LoginPresenter mLoginPresenter;
        View tvLogin = $(R.id.tv_login);
        tvLogin.setOnClickListener((View v) -> {
            ChooseLabelActivity.start(this);
            finish();
        });

        mLoginPresenter = new LoginPresenter(this, this);
        mLoginPresenter.doLogin("18700000000", "18700000000");

    }

    @Override
    public void onLoginStart() {
        MyLogUtil.d("开始登陆");
    }

    @Override
    public void onLoginReturned(List<LoginResponse> loginResponse) {
        MyLogUtil.d("登陆成功返回数据：" + loginResponse);
    }

    @Override
    public void setStatusMVP() {
        super.setStatusMVP();
    }
}
