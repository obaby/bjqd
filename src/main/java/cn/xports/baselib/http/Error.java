package cn.xports.baselib.http;

import android.util.SparseArray;

public class Error {
    static final int HTTP_ERROR = 1003;
    static final int INVALID_SIGN = 3006;
    static final int NETWORD_ERROR = 1002;
    static final int PARSE_ERROR = 1001;
    static final int UNKNOWN = 1000;
    private static SparseArray<String> errorActionMap = new SparseArray<>();

    static class StateCode {
        static final int BAD_GATEWAY = 502;
        static final int FORBIDDEN = 403;
        static final int GATEWAY_TIMEOUT = 504;
        static final int INTERNAL_SERVER_ERROR = 500;
        static final int METHOD_NOT_ALLOWED = 405;
        static final int NOT_FOUND = 404;
        static final int REQUEST_TIMEOUT = 408;
        static final int SERVICE_UNAVAILABLE = 503;
        static final int UNAUTHORIZED = 401;

        StateCode() {
        }
    }

    static class BusinessCode {
        BusinessCode() {
        }
    }

    static class Message {
        static final String PARSE_ERROR = "解析错误";
        static final String UNKNOWN = "unknown";

        Message() {
        }
    }

    static {
        errorActionMap.put(403, "重新登录");
    }

    public static String getErrorActionText(int i) {
        return errorActionMap.get(i) != null ? errorActionMap.get(i) : "重试";
    }
}
