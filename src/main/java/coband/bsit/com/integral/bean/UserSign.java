package coband.bsit.com.integral.bean;

import java.io.Serializable;

public class UserSign implements Serializable {
    private int code;
    private String message;
    private String result;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }
}
