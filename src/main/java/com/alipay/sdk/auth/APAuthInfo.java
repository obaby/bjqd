package com.alipay.sdk.auth;

public class APAuthInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f628a;

    /* renamed from: b  reason: collision with root package name */
    private String f629b;

    /* renamed from: c  reason: collision with root package name */
    private String f630c;
    private String d;

    public APAuthInfo(String str, String str2, String str3) {
        this(str, str2, str3, (String) null);
    }

    public APAuthInfo(String str, String str2, String str3, String str4) {
        this.f628a = str;
        this.f629b = str2;
        this.d = str3;
        this.f630c = str4;
    }

    public String getAppId() {
        return this.f628a;
    }

    public String getProductId() {
        return this.f629b;
    }

    public String getPid() {
        return this.f630c;
    }

    public String getRedirectUri() {
        return this.d;
    }
}
