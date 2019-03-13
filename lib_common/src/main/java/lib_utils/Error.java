package lib_utils;

public class Error {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;
    /**
     * 速算比赛，无可用拍照识别资源
     */
    public static final int NO_AI_ASSESS_RESOURCE = -2904;
    /**
     * token不正确，需跳到登录页
     */
    public static final int WRONG_TOKEN = -103;
}
