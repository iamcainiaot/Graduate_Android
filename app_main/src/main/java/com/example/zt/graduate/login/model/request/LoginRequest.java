package  com.example.zt.graduate.login.model.request;

import mvp.BaseRequest;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆请求参数
 */
public class LoginRequest extends BaseRequest {
    /**
     * 登陆手机号
     */
    String phoneNumber;
    /**
     * 登陆密码
     */
    String password;

    public LoginRequest(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}

