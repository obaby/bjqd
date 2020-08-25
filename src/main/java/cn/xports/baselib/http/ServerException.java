package cn.xports.baselib.http;

public class ServerException extends RuntimeException {
    public String message;
    public int statusCode;

    public ServerException(int i) {
        this.message = getErrorMessage(i);
        this.statusCode = i;
    }

    private String getErrorMessage(int i) {
        String str;
        if (i == 401) {
            str = "unauthorized";
        } else if (i == 408) {
            str = "request timeout";
        } else if (i != 500) {
            switch (i) {
                case 403:
                    str = "forbidden";
                    break;
                case 404:
                    str = "not found";
                    break;
                case 405:
                    str = "method not allowed";
                    break;
                default:
                    switch (i) {
                        case 502:
                            str = "bad gateway";
                            break;
                        case 503:
                            str = "service unavailable";
                            break;
                        case 504:
                            str = "gateway timeout";
                            break;
                        default:
                            str = "default";
                            break;
                    }
            }
        } else {
            str = "internal server error";
        }
        this.message = str;
        return str;
    }
}
