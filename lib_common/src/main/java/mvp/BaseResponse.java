package mvp;

import lib_utils.CommonUtils;

/**
 * @author taozhu5
 * @date 2019/3/13 10:16
 * @description 基础响应类
 */
public class BaseResponse<T> {
    /**
     * 状态码
     */
    private int status;

    /**
     * 信息
     */
    private String message;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", msg='" + message + '\'' +
                '}';
    }

    /**
     * 请求是否成功
     *
     * @return boolean类型
     */
    public boolean isOK() {
        return status == CODE_OK;
    }

    /**
     * code为success表示请求成功
     */
    public static final int CODE_OK = 200;

    /**
     * 判断是否请求正常
     */
    public boolean isSuccess() {
        return "请求成功".equals(message);
    }
}