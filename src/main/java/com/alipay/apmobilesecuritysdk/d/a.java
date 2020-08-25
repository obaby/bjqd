package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public final class a {
    public static synchronized Map<String, String> a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (a.class) {
            String a2 = com.alipay.security.mobile.module.a.a.a(map, "appchannel", "");
            hashMap = new HashMap();
            hashMap.put("AA1", context.getPackageName());
            com.alipay.security.mobile.module.b.a.a();
            hashMap.put("AA2", com.alipay.security.mobile.module.b.a.a(context));
            hashMap.put("AA3", "APPSecuritySDK-ALIPAY");
            hashMap.put("AA4", "3.2.2-20180331");
            hashMap.put("AA6", a2);
        }
        return hashMap;
    }
}
