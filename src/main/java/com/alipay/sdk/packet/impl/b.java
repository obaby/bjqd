package com.alipay.sdk.packet.impl;

import com.alipay.sdk.packet.d;
import org.json.JSONException;
import org.json.JSONObject;

public final class b extends d {
    /* access modifiers changed from: protected */
    public final String b() {
        return "5.0.0";
    }

    /* access modifiers changed from: protected */
    public final JSONObject a() throws JSONException {
        return d.a("sdkConfig", "obtain");
    }
}
