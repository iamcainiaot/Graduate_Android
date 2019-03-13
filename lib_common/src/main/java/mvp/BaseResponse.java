package mvp;

/**
 * @author taozhu5
 * @date 2019/3/13 10:16
 * @description 基础响应类
 */
public class BaseResponse<T> {
    /**
     * 状态码
     */
    private String status;
    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
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
    private static final String CODE_OK = "success";

}
