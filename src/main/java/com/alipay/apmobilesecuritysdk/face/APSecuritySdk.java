package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.e.d;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.sdk.cons.b;
import java.util.HashMap;
import java.util.Map;

public class APSecuritySdk {

    /* renamed from: a  reason: collision with root package name */
    private static APSecuritySdk f581a;

    /* renamed from: c  reason: collision with root package name */
    private static Object f582c = new Object();
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public Context f583b;

    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    public class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;

        public TokenResult() {
        }
    }

    private APSecuritySdk(Context context) {
        this.f583b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (f581a == null) {
            synchronized (f582c) {
                if (f581a == null) {
                    f581a = new APSecuritySdk(context);
                }
            }
        }
        return f581a;
    }

    public static String getUtdid(Context context) {
        return UtdidWrapper.getUtdid(context);
    }

    public String getApdidToken() {
        String a2 = a.a(this.f583b, "");
        if (com.alipay.security.mobile.module.a.a.a(a2)) {
            initToken(0, new HashMap(), (InitResultListener) null);
        }
        return a2;
    }

    public String getSdkName() {
        return "APPSecuritySDK-ALIPAY";
    }

    public String getSdkVersion() {
        return "3.2.2-20180331";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        tokenResult = new TokenResult();
        try {
            tokenResult.apdidToken = a.a(this.f583b, "");
            tokenResult.clientKey = h.f(this.f583b);
            tokenResult.apdid = a.a(this.f583b);
            tokenResult.umidToken = UmidSdkWrapper.getSecurityToken(this.f583b);
            if (com.alipay.security.mobile.module.a.a.a(tokenResult.apdid) || com.alipay.security.mobile.module.a.a.a(tokenResult.apdidToken) || com.alipay.security.mobile.module.a.a.a(tokenResult.clientKey)) {
                initToken(0, new HashMap(), (InitResultListener) null);
            }
        } catch (Throwable unused) {
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        com.alipay.apmobilesecuritysdk.b.a.a().a(i);
        String b2 = h.b(this.f583b);
        String c2 = com.alipay.apmobilesecuritysdk.b.a.a().c();
        if (com.alipay.security.mobile.module.a.a.b(b2) && !com.alipay.security.mobile.module.a.a.a(b2, c2)) {
            com.alipay.apmobilesecuritysdk.e.a.a(this.f583b);
            d.a(this.f583b);
            g.a(this.f583b);
            i.h();
        }
        if (!com.alipay.security.mobile.module.a.a.a(b2, c2)) {
            h.c(this.f583b, c2);
        }
        String a2 = com.alipay.security.mobile.module.a.a.a(map, b.g, "");
        String a3 = com.alipay.security.mobile.module.a.a.a(map, b.f669c, "");
        String a4 = com.alipay.security.mobile.module.a.a.a(map, "userId", "");
        if (com.alipay.security.mobile.module.a.a.a(a2)) {
            a2 = UtdidWrapper.getUtdid(this.f583b);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put(b.g, a2);
        hashMap.put(b.f669c, a3);
        hashMap.put("userId", a4);
        hashMap.put(DispatchConstants.APP_NAME, "");
        hashMap.put("appKeyClient", "");
        hashMap.put("appchannel", "");
        hashMap.put("rpcVersion", "8");
        com.alipay.apmobilesecuritysdk.f.b.a().a((Runnable) new Runnable() {
            public void run() {
                new a(APSecuritySdk.this.f583b).a((Map<String, String>) hashMap);
                if (initResultListener != null) {
                    initResultListener.onResult(APSecuritySdk.this.getTokenResult());
                }
            }
        });
    }
}
