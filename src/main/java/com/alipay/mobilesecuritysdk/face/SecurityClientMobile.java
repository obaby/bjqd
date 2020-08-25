package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.sdk.cons.b;
import com.alipay.security.mobile.module.a.a;
import java.util.HashMap;
import java.util.Map;

public class SecurityClientMobile {
    public static synchronized String GetApdid(Context context, Map<String, String> map) {
        String a2;
        synchronized (SecurityClientMobile.class) {
            HashMap hashMap = new HashMap();
            hashMap.put(b.g, a.a(map, b.g, ""));
            hashMap.put(b.f669c, a.a(map, b.f669c, ""));
            hashMap.put("userId", a.a(map, "userId", ""));
            APSecuritySdk.getInstance(context).initToken(0, hashMap, (APSecuritySdk.InitResultListener) null);
            a2 = com.alipay.apmobilesecuritysdk.a.a.a(context);
        }
        return a2;
    }
}
