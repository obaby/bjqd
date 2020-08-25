package anet.channel.util;

import android.util.SparseArray;

/* compiled from: Taobao */
public class ErrorConstant {
    public static final int ERROR_ACCS_CUSTOM_FRAME_CB_NULL = -105;
    public static final int ERROR_AUTH_EXCEPTION = -302;
    public static final int ERROR_CONNECT_EXCEPTION = -406;
    public static final int ERROR_CONN_TIME_OUT = -400;
    public static final int ERROR_DATA_LENGTH_NOT_MATCH = -206;
    public static final int ERROR_DATA_TOO_LARGE = -303;
    public static final int ERROR_EXCEPTION = -101;
    public static final int ERROR_GET_PROCESS_NULL = -108;
    public static final int ERROR_HOST_NOT_VERIFY_ERROR = -403;
    public static final int ERROR_IO_EXCEPTION = -404;
    public static final int ERROR_NO_NETWORK = -200;
    public static final int ERROR_NO_STRATEGY = -203;
    @Deprecated
    public static final int ERROR_OPEN_CONNECTION_NULL = -407;
    public static final int ERROR_PARAM_ILLEGAL = -102;
    public static final int ERROR_REMOTE_CALL_FAIL = -103;
    public static final int ERROR_REQUEST_CANCEL = -204;
    public static final int ERROR_REQUEST_FAIL = -201;
    public static final int ERROR_REQUEST_FORBIDDEN_IN_BG = -205;
    public static final int ERROR_REQUEST_TIME_OUT = -202;
    public static final int ERROR_SESSION_INVALID = -301;
    public static final int ERROR_SOCKET_TIME_OUT = -401;
    public static final int ERROR_SSL_ERROR = -402;
    public static final int ERROR_TNET_EXCEPTION = -300;
    public static final int ERROR_TNET_REQUEST_FAIL = -304;
    public static final int ERROR_UNKNOWN = -100;
    public static final int ERROR_UNKNOWN_HOST_EXCEPTION = -405;
    public static final int SC_OK = 200;
    private static SparseArray<String> errorMsgMap = new SparseArray<>();

    static {
        errorMsgMap.put(200, "请求成功");
        errorMsgMap.put(-100, "未知错误");
        errorMsgMap.put(-101, "发生异常");
        errorMsgMap.put(-102, "非法参数");
        errorMsgMap.put(-103, "远程调用失败");
        errorMsgMap.put(-105, "ACCS自定义帧回调为空");
        errorMsgMap.put(ERROR_GET_PROCESS_NULL, "获取Process失败");
        errorMsgMap.put(ERROR_NO_NETWORK, "无网络");
        errorMsgMap.put(ERROR_NO_STRATEGY, "网络库无策略");
        errorMsgMap.put(ERROR_REQUEST_TIME_OUT, "请求超时");
        errorMsgMap.put(ERROR_REQUEST_CANCEL, "请求被取消");
        errorMsgMap.put(ERROR_REQUEST_FORBIDDEN_IN_BG, "请求后台被禁止");
        errorMsgMap.put(ERROR_DATA_LENGTH_NOT_MATCH, "请求收到的数据长度与Content-Length不匹配");
        errorMsgMap.put(ERROR_TNET_EXCEPTION, "Tnet层抛出异常");
        errorMsgMap.put(ERROR_SESSION_INVALID, "Session不可用");
        errorMsgMap.put(ERROR_AUTH_EXCEPTION, "鉴权异常");
        errorMsgMap.put(ERROR_DATA_TOO_LARGE, "自定义帧数据过大");
        errorMsgMap.put(ERROR_TNET_REQUEST_FAIL, "Tnet请求失败");
        errorMsgMap.put(ERROR_CONN_TIME_OUT, "连接超时");
        errorMsgMap.put(ERROR_SOCKET_TIME_OUT, "Socket超时");
        errorMsgMap.put(ERROR_SSL_ERROR, "SSL失败");
        errorMsgMap.put(ERROR_HOST_NOT_VERIFY_ERROR, "域名未认证");
        errorMsgMap.put(-404, "IO异常");
        errorMsgMap.put(ERROR_UNKNOWN_HOST_EXCEPTION, "域名不能解析");
        errorMsgMap.put(ERROR_CONNECT_EXCEPTION, "连接异常");
    }

    public static String getErrMsg(int i) {
        return StringUtils.stringNull2Empty(errorMsgMap.get(i));
    }

    public static String formatMsg(int i, String str) {
        return StringUtils.concatString(getErrMsg(i), ":", str);
    }
}
