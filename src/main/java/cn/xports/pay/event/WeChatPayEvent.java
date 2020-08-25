package cn.xports.pay.event;

public class WeChatPayEvent {
    private boolean isSuccess;
    private String message;
    private String payTime;

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
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
}
