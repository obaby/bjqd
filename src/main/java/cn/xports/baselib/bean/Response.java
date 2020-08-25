package cn.xports.baselib.bean;

public class Response {
    int error;
    String message;

    public int getError() {
        return this.error;
    }

    public Response setError(int i) {
        this.error = i;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Response setMessage(String str) {
        this.message = str;
        return this;
    }
}
