package com.example.zt.graduate.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.zt.graduate.R;
import com.example.zt.graduate.app.UserApplication;
import com.example.zt.graduate.choose_label.ChooseLabelActivity;
import com.example.zt.graduate.db.greendaogen.DaoSession;
import com.example.zt.graduate.login.iview.ILoginView;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.login.presenter.LoginPresenter;
import com.google.gson.Gson;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import lib_utils.CommonUtils;
import lib_utils.MyLogUtil;
import lib_utils.StringUtils;
import lib_utils.ToastUtil;
import lib_utils.db.entity.UserInfoTable;
import lib_utils.net.tencent.TencentMainModel;
import lib_utils.net.tencent.TencentUrl;
import lib_utils.net.tencent.TencentUserInfoModel;
import lib_utils.net.tencent.TentcentId;
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

    private static String GRAPH_SIMPLE_USER_INFO;
    /**
     * 验证码输入框
     */
    private EditText mEtCode;
    private EditText mEtUserName;
    private LoginPresenter mLoginPresenter;

    /**
     * 腾讯
     */
    private Tencent mTencent;
    private TencentUserInfoModel mTencentUserInfoModel;
    private BaseUiListener baseUiListener;


    private UserApplication mUserApplication;
    private DaoSession mDaoSession;
    /**
     * 是否能请求
     */
    private static boolean isCanRequest = true;

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
        // 读取手机sdCard权限
        CommonUtils.verifyStoragePermissions(this);
        View tvUsername = $(R.id.tv_username);
        tvUsername.setOnClickListener((View v) -> {
            //    finish();
        });
        // qq登录
        $(R.id.iv_qq).setOnClickListener(v -> {
            showProgress();
            if (baseUiListener == null) {
                baseUiListener = new BaseUiListener();
            }
            tencentLogin();
        });

        // 点击登录按钮 默认是手机号码登录
        $(R.id.tv_login).setOnClickListener(v -> {
            // 手机号码登陆
            phoneLogin();
        });

        mEtUserName = $(R.id.et_username);
        // 点击发送验证码
        $(R.id.tv_send_code).setOnClickListener(v -> {
            String userName = mEtUserName.getText().toString().trim();
            if (!StringUtils.isEmpty(userName) && userName.length() == 11) {
                sendCode(userName);
            }
        });
        mEtCode = $(R.id.et_code);
    }

    /**
     * 手机号码登录
     */
    private void phoneLogin() {
        EditText etUserName = $(R.id.et_username);
        String code = mEtCode.getText().toString().trim();
        if (code.length() == 4) {
            SMSSDK.submitVerificationCode("86", etUserName.getText().toString().trim(), code);
        }
    }

    /**
     * 使用qq登陆
     */
    private void tencentLogin() {
        mTencent = Tencent.createInstance(TentcentId.APP_ID, this.getApplicationContext());
        mTencent.login(LoginActivity.this, "all", new BaseUiListener());
    }

    public void sendCode(String phone) {
        showProgress();
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        // 提交验证码成功，处理成功的结果
                        HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                        // 手机号码，如“13800138000”
                        String phone = (String) phoneMap.get("phone");
                        if (mLoginPresenter == null || mLoginPresenter.isDetached()) {
                            mLoginPresenter = new LoginPresenter(LoginActivity.this, LoginActivity.this);
                        }
                        mLoginPresenter.doLogin(phone);
                        SMSSDK.unregisterAllEventHandler();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        ToastUtil.showShort(LoginActivity.this, "验证码发送成功，请注意查收！", true);
                        hideProgress();
                        // 获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        // do nothing
                    }
                } else {
                    // 出现异常情况
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        // 发送验证码
        SMSSDK.getVerificationCode("86", phone);
        SMSSDK.registerEventHandler(eh);
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
       /*     // userId userName sex label imageUrl
            UserInfoTable userInfoTable = new UserInfoTable(loginResponse1.getUserId(),
                    loginResponse1.getUserName(), loginResponse1.getSex(),
                    loginResponse1.getLabel(), loginResponse1.getImageUrl());
            // 不同则需做操作
            if (!userInfoTable.equals(mUserApplication.getUserInfoTable())) {
                mUserApplication.getDaoSession().clear();
                MyLogUtil.d("userInfoTable:" + userInfoTable.toString());
                // 插入数据到表中
                mDaoSession.insertOrReplace(userInfoTable);
            }*/
            ChooseLabelActivity.start(LoginActivity.this, loginResponse1);
            hideProgress();
            finish();
        }
    }

    /**
     * qq登陆回调接口    !!!!!!!!!!!!!!必须要!!!!!!!!!!!!!!!!
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, baseUiListener);
        Tencent.handleResultData(data, baseUiListener);
    }

    /**
     * @author taozhu5
     * @date 2019/4/10 20:12
     * @description 腾讯接口监听
     */
    private class BaseUiListener implements IUiListener {
        TencentMainModel tencentMainModel;
        String token;
        String expires_in;
        String uniqueCode;

        @Override
        public void onComplete(Object o) {
            MyLogUtil.d("Tencent  Complete" + o.toString());
            uniqueCode = ((JSONObject) o).optString("openid");
            try {
                token = ((JSONObject) o).optString("access_token");
                expires_in = ((JSONObject) o).optString("expires_in");
            } catch (Exception e) {
                e.printStackTrace();
            }
            tencentMainModel = new Gson().fromJson(String.valueOf(o), TencentMainModel.class);
            mTencent.setOpenId(tencentMainModel.getOpenid());
            mTencent.setAccessToken(tencentMainModel.getAccess_token(), tencentMainModel.getExpires_in());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(TencentUrl.GET_USER_INFO_URL);
            stringBuilder.append("access_token=");
            stringBuilder.append(tencentMainModel.getAccess_token());
            stringBuilder.append("&oauth_consumer_key=");
            stringBuilder.append(TentcentId.APP_ID);
            stringBuilder.append("&openid=");
            stringBuilder.append(tencentMainModel.getOpenid());
            GRAPH_SIMPLE_USER_INFO = stringBuilder.toString();
            getUserInfoInThread();
        }


        @Override
        public void onError(UiError uiError) {
            MyLogUtil.d("Tencent  Error了");
        }

        @Override
        public void onCancel() {
            MyLogUtil.d("Tencent  Cancel");
        }

        private void getUserInfoInThread() {
            new Thread() {
                @Override
                public void run() {
                    /**
                     * "ret":0, 返回码
                     * "msg":"", 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
                     * "nickname":"Peter", 用户在QQ空间的昵称。
                     * "figureurl_2":"http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/100",   用户头像可以用这个
                     * "gender":"男", 性别。 如果获取不到则默认返回"男"
                     * "is_yellow_vip":"1",
                     * "vip":"1",
                     * "yellow_vip_level":"7",
                     * "level":"7",
                     * "is_yellow_year_vip":"1"
                     * "province":"安徽",
                     * "city":"合肥",
                     * "year":"1997"
                     */
                    try {
                        JSONObject json = mTencent.request(GRAPH_SIMPLE_USER_INFO, null,
                                Constants.HTTP_GET);
                        int ret = json.optInt("ret");
                        mTencentUserInfoModel = new TencentUserInfoModel();
                        mTencentUserInfoModel.setRet(ret);
                        mTencentUserInfoModel.setMsg(json.optString("msg"));
                        mTencentUserInfoModel.setFigureurl_2(json.optString("figureurl"));
                        mTencentUserInfoModel.setNickname(json.optString("nickname"));
                        mTencentUserInfoModel.setGender(json.optString("gender"));
                        mTencentUserInfoModel.setProvince(json.optString("province"));
                        mTencentUserInfoModel.setCity(json.optString("city"));
                        mTencentUserInfoModel.setYear(json.optString("year"));
                        MyLogUtil.d("Tencent UserInfo:" + mTencentUserInfoModel);
                        if (mTencentUserInfoModel != null) {
                            // 说明正常登陆
                            if (ret == 0) {
                                mLoginPresenter = new LoginPresenter(LoginActivity.this, LoginActivity.this);
                                // 用户名和头像
                                mLoginPresenter.doLogin(mTencentUserInfoModel.getNickname());
                            } else {
                                // qq登录返回错误码：
                                // http://wiki.connect.qq.com/%E5%85%AC%E5%85%B1%E8%BF%94%E5%9B%9E%E7%A0%81%E8%AF%B4%E6%98%8E
                                ToastUtil.showShort(LoginActivity.this,
                                        "登录出错了，请重试", false);
                                hideProgress();
                                isCanRequest = true;
                            }
                        }
                    } catch (Exception e) {
                        MyLogUtil.e(e.toString());
                    }
                }
            }.start();
        }
    }
}