package com.alipay.security.mobile.module.http;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.aa;
import com.alipay.android.phone.mrpc.core.h;
import com.alipay.android.phone.mrpc.core.w;
import com.alipay.tscenter.biz.rpc.a.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import org.json.JSONObject;

public final class b implements a {
    private static b d;
    /* access modifiers changed from: private */
    public static DataReportResult e;

    /* renamed from: a  reason: collision with root package name */
    private w f773a = null;

    /* renamed from: b  reason: collision with root package name */
    private a f774b = null;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public com.alipay.tscenter.biz.rpc.report.general.a f775c = null;

    private b(Context context, String str) {
        aa aaVar = new aa();
        aaVar.a(str);
        this.f773a = new h(context);
        this.f774b = (a) this.f773a.a(a.class, aaVar);
        this.f775c = (com.alipay.tscenter.biz.rpc.report.general.a) this.f773a.a(com.alipay.tscenter.biz.rpc.report.general.a.class, aaVar);
    }

    public static synchronized b a(Context context, String str) {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                d = new b(context, str);
            }
            bVar = d;
        }
        return bVar;
    }

    public final DataReportResult a(DataReportRequest dataReportRequest) {
        if (this.f775c != null) {
            e = null;
            new Thread(new c(this, dataReportRequest)).start();
            int i = com.alipay.security.mobile.module.http.constant.a.f778a;
            while (e == null && i >= 0) {
                Thread.sleep(50);
                i -= 50;
            }
        }
        return e;
    }

    public final boolean a(String str) {
        String str2;
        if (com.alipay.security.mobile.module.a.a.a(str) || this.f774b == null) {
            return false;
        }
        try {
            a aVar = this.f774b;
            com.alipay.security.mobile.module.a.a.f(str);
            str2 = aVar.a();
        } catch (Throwable unused) {
            str2 = null;
        }
        if (!com.alipay.security.mobile.module.a.a.a(str2)) {
            return ((Boolean) new JSONObject(str2).get("success")).booleanValue();
        }
        return false;
    }
}
