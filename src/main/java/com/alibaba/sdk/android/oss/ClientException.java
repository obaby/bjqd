package com.alibaba.sdk.android.oss;

public class ClientException extends Exception {
    private Boolean canceled;

    public ClientException() {
        this.canceled = false;
    }

    public ClientException(String str) {
        super("[ErrorMessage]: " + str);
        this.canceled = false;
    }

    public ClientException(Throwable th) {
        super(th);
        this.canceled = false;
    }

    public ClientException(String str, Throwable th) {
        this(str, th, false);
    }

    public ClientException(String str, Throwable th, Boolean bool) {
        super("[ErrorMessage]: " + str, th);
        this.canceled = false;
        this.canceled = bool;
    }

    public Boolean isCanceledException() {
        return this.canceled;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (getCause() == null) {
            return message;
        }
        return getCause().getMessage() + "\n" + message;
    }
}
