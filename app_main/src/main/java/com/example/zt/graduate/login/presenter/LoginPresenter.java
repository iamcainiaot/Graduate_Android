package com.example.zt.graduate.login.presenter;

import android.content.Context;

import com.example.zt.graduate.login.iview.ILoginView;
import com.example.zt.graduate.login.model.request.LoginRequest;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.manager.HttpManager;

import java.util.List;

import lib_utils.MyLogUtil;
import lib_utils.net.RxSubscribe;
import mvp.BasePresenter;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆请求
 */
public class LoginPresenter extends BasePresenter<ILoginView> {
    private HttpManager mHttpManager = new HttpManager();
    private Context mContext;

    public LoginPresenter(ILoginView view, Context context) {
        super(view);
        mContext = context;
    }

    /**
     * 登陆请求
     *
     * @param userName 用户名（登陆用户名）
     * @param pwd 密码
     */
    public void doLogin(String userName, String pwd) {
        LoginRequest loginRequest = new LoginRequest(userName, pwd);
        mView.get().onLoginStart();
        mHttpManager.doLogin(loginRequest,
                new RxSubscribe<List<LoginResponse>>(mContext, true) {
                    @Override
                    protected void _onNext(List<LoginResponse> loginResponse) {
                        MyLogUtil.d("成功了");
                        mView.get().onLoginReturned(loginResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        MyLogUtil.d(message);
                    }
                });
    }
}
