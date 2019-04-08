package com.example.zt.graduate.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.graduate_android.R;
import com.example.zt.graduate.app.UserApplication;
import com.example.zt.graduate.choose_label.ChooseLabelActivity;
import com.example.zt.graduate.db.greendaogen.DaoSession;
import com.example.zt.graduate.login.iview.ILoginView;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.login.presenter.LoginPresenter;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.HashMap;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import lib_utils.CommonUtils;
import lib_utils.MyLogUtil;
import lib_utils.db.entity.UserInfoTable;
import mvp.BaseMvpActivity;

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

    /**
     * 发送验证码
     */
    private TextView tvPassword;

    /**
     * 登陆按钮
     */
    private TextView tvLogin;

    private LoginPresenter mLoginPresenter;

    /**
     * GreenDao相关操作
     */
    private UserApplication mUserApplication;
    private DaoSession mDaoSession;

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        mUserApplication = (UserApplication) getApplication();
        mDaoSession = mUserApplication.getDaoSession();
    }

    @Override
    public void initView() {

        View tvUsername = $(R.id.tv_username);
        tvUsername.setOnClickListener((View v) -> {
            // ChooseLabelActivity.start(this);
            //    finish();
        });
        tvLogin = $(R.id.tv_login);
        tvLogin.setOnClickListener(v -> {
            CommonUtils.isFastDoubleClick();
            mLoginPresenter = new LoginPresenter(this, this);
            mLoginPresenter.doLogin("18700000000", "18700000000");
        });
        tvPassword = $(R.id.tv_password);
        tvPassword.setOnClickListener(v -> {
            sendCode(this);
        });
    }

    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    // 国家代码，如“86”
                    String country = (String) phoneMap.get("86");
                    // 手机号码，如“13800138000”
                    String phone = (String) phoneMap.get("15155487396");

                    // TODO 利用国家代码和手机号码进行后续的操作
                } else {
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }

    @Override
    public void onLoginStart() {
        MyLogUtil.d("开始登陆");
    }

    @Override
    public void onLoginReturned(List<LoginResponse> loginResponseList) {
        MyLogUtil.d("登陆请求成功返回数据：" + loginResponseList);
        LoginResponse loginResponse1 = loginResponseList.get(0);
        // 状态码为200 且为请求成功
        if (loginResponse1.isOK() && loginResponse1.isSuccess()) {
            // id （自增）  userId userName sex label imageUrl
            UserInfoTable userInfoTable = new UserInfoTable(loginResponse1.getUserId(), loginResponse1.getUserName(), loginResponse1.getSex(), loginResponse1.getLabel(), loginResponse1.getImageUrl());
            // 不同则需做操作
            if (!userInfoTable.equals(mUserApplication.getUserInfoTable())) {
                mUserApplication.getDaoSession().clear();
                // 插入数据到表中
                mDaoSession.insertOrReplace(userInfoTable);
            }
            MyLogUtil.d(getLocalClassName() + mUserApplication.getUserInfoTable().toString());
            // 跳转到选择个性标签界面
            ChooseLabelActivity.start(this);
        }
    }
}