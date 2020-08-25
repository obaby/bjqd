package com.alipay.sdk.packet.impl;

import android.content.Context;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alipay.sdk.packet.b;
import com.alipay.sdk.packet.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

public final class c extends d {
    public static final String t = "log_v";

    /* access modifiers changed from: protected */
    public final String a(String str, JSONObject jSONObject) {
        return str;
    }

    /* access modifiers changed from: protected */
    public final JSONObject a() throws JSONException {
        return null;
    }

    /* access modifiers changed from: protected */
    public final List<Header> a(boolean z, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicHeader(d.f699a, String.valueOf(z)));
        arrayList.add(new BasicHeader(d.d, OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE));
        arrayList.add(new BasicHeader(d.g, "CBC"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final String c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put(d.i, "/sdk/log");
        hashMap.put(d.j, "1.0.0");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(t, "1.0");
        return a((HashMap<String, String>) hashMap, (HashMap<String, String>) hashMap2);
    }

    public final b a(Context context, String str) throws Throwable {
        return a(context, str, "https://mcgw.alipay.com/sdklog.do", true);
    }
}
