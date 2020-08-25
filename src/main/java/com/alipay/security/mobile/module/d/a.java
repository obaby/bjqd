package com.alipay.security.mobile.module.d;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private String f764a;

    /* renamed from: b  reason: collision with root package name */
    private String f765b;

    /* renamed from: c  reason: collision with root package name */
    private String f766c;
    private String d;
    private String e;
    private String f;
    private String g;

    public a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f764a = str;
        this.f765b = str2;
        this.f766c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public final String toString() {
        String str;
        StringBuilder sb;
        String str2;
        StringBuilder sb2;
        String str3;
        StringBuilder sb3;
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append("," + this.f764a);
        stringBuffer.append("," + this.f765b);
        stringBuffer.append("," + this.f766c);
        stringBuffer.append("," + this.d);
        if (com.alipay.security.mobile.module.a.a.a(this.e) || this.e.length() < 20) {
            sb = new StringBuilder(",");
            str = this.e;
        } else {
            sb = new StringBuilder(",");
            str = this.e.substring(0, 20);
        }
        sb.append(str);
        stringBuffer.append(sb.toString());
        if (com.alipay.security.mobile.module.a.a.a(this.f) || this.f.length() < 20) {
            sb2 = new StringBuilder(",");
            str2 = this.f;
        } else {
            sb2 = new StringBuilder(",");
            str2 = this.f.substring(0, 20);
        }
        sb2.append(str2);
        stringBuffer.append(sb2.toString());
        if (com.alipay.security.mobile.module.a.a.a(this.g) || this.g.length() < 20) {
            sb3 = new StringBuilder(",");
            str3 = this.g;
        } else {
            sb3 = new StringBuilder(",");
            str3 = this.g.substring(0, 20);
        }
        sb3.append(str3);
        stringBuffer.append(sb3.toString());
        return stringBuffer.toString();
    }
}
