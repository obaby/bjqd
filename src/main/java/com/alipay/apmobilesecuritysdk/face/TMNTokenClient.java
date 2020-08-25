package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.sdk.cons.b;
import com.alipay.security.mobile.module.a.a;
import java.util.HashMap;
import java.util.Map;

public class TMNTokenClient {

    /* renamed from: a  reason: collision with root package name */
    private static TMNTokenClient f588a;
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public Context f589b = null;

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    private TMNTokenClient(Context context) {
        if (context != null) {
            this.f589b = context;
            return;
        }
        throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
    }

    public static TMNTokenClient getInstance(Context context) {
        if (f588a == null) {
            synchronized (TMNTokenClient.class) {
                if (f588a == null) {
                    f588a = new TMNTokenClient(context);
                }
            }
        }
        return f588a;
    }

    public void intiToken(final String str, String str2, String str3, final InitResultListener initResultListener) {
        if (a.a(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (a.a(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put(b.g, UtdidWrapper.getUtdid(this.f589b));
        hashMap.put(b.f669c, "");
        hashMap.put("userId", "");
        hashMap.put(DispatchConstants.APP_NAME, str);
        hashMap.put("appKeyClient", str2);
        hashMap.put("appchannel", "openapi");
        hashMap.put("sessionId", str3);
        hashMap.put("rpcVersion", "8");
        com.alipay.apmobilesecuritysdk.f.b.a().a((Runnable) new Runnable() {
            public void run() {
                int a2 = new com.alipay.apmobilesecuritysdk.a.a(TMNTokenClient.this.f589b).a((Map<String, String>) hashMap);
                if (initResultListener != null) {
                    if (a2 == 0) {
                        initResultListener.onResult(com.alipay.apmobilesecuritysdk.a.a.a(TMNTokenClient.this.f589b, str), 0);
                        return;
                    }
                    initResultListener.onResult("", a2);
                }
            }
        });
    }
}
