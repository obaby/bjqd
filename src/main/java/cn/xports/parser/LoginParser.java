package cn.xports.parser;

import cn.xports.baselib.bean.Response;

public class LoginParser extends Response {
    private String accessToken;
    private Object avatar;
    private String coAccountId;
    private String coAppId;
    private String contactPhone;
    private String ecardNo;
    private String name;
    private String netUserId;
    private String sysdate;
    private String token;

    public String getSysdate() {
        return this.sysdate;
    }

    public void setSysdate(String str) {
        this.sysdate = str;
    }

    public String getNetUserId() {
        return this.netUserId;
    }

    public String getCoAccountId() {
        return this.coAccountId;
    }

    public LoginParser setCoAccountId(String str) {
        this.coAccountId = str;
        return this;
    }

    public String getCoAppId() {
        return this.coAppId;
    }

    public LoginParser setCoAppId(String str) {
        this.coAppId = str;
        return this;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public LoginParser setAccessToken(String str) {
        this.accessToken = str;
        return this;
    }

    public void setNetUserId(String str) {
        this.netUserId = str;
    }

    public String getEcardNo() {
        return this.ecardNo;
    }

    public void setEcardNo(String str) {
        this.ecardNo = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Object getAvatar() {
        return this.avatar;
    }

    public void setAvatar(Object obj) {
        this.avatar = obj;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String str) {
        this.contactPhone = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
