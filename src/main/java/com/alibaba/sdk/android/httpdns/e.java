package com.alibaba.sdk.android.httpdns;

import org.json.JSONObject;

class e {

    /* renamed from: b  reason: collision with root package name */
    private int f471b;
    private String e;

    e(int i, String str) {
        this.f471b = i;
        this.e = new JSONObject(str).getString("code");
    }

    public String a() {
        return this.e;
    }
}
