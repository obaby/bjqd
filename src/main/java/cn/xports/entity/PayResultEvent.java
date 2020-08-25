package cn.xports.entity;

import java.io.Serializable;

public class PayResultEvent implements Serializable {
    private boolean isSuccess;
    private String message;
    private String payMode;
    private String payTime;
    private String tradeId;

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }

    public String getPayMode() {
        return this.payMode;
    }

    public void setPayMode(String str) {
        this.payMode = str;
    }

    public String getPayTime() {
        return this.payTime;
    }

    public void setPayTime(String str) {
        this.payTime = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getTradeId() {
        return this.tradeId;
    }

    public void setTradeId(String str) {
        this.tradeId = str;
    }
}
