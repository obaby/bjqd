package com.alipay.sdk.packet;

import android.text.TextUtils;
import org.json.JSONObject;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    String f695a;

    /* renamed from: b  reason: collision with root package name */
    public String f696b;

    public b(String str, String str2) {
        this.f695a = str;
        this.f696b = str2;
    }

    private String b() {
        return this.f695a;
    }

    private void a(String str) {
        this.f695a = str;
    }

    private String c() {
        return this.f696b;
    }

    private void b(String str) {
        this.f696b = str;
    }

    public final JSONObject a() {
        if (TextUtils.isEmpty(this.f696b)) {
            return null;
        }
        try {
            return new JSONObject(this.f696b);
        } catch (Exception unused) {
            return null;
        }
    }

    public final String toString() {
        return "\nenvelop:" + this.f695a + "\nbody:" + this.f696b;
    }
}
