package com.alipay.security.mobile.module.http.v2;

import android.content.Context;
import com.alipay.security.mobile.module.http.a;
import com.alipay.security.mobile.module.http.model.c;
import com.alipay.security.mobile.module.http.model.d;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import java.util.HashMap;

public final class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private static a f786a;

    /* renamed from: b  reason: collision with root package name */
    private static a f787b;

    public final boolean a(String str) {
        return f787b.a(str);
    }

    public final c a(d dVar) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        dataReportRequest.os = com.alipay.security.mobile.module.a.a.d(dVar.f783a);
        dataReportRequest.rpcVersion = dVar.j;
        dataReportRequest.bizType = "1";
        dataReportRequest.bizData = new HashMap();
        dataReportRequest.bizData.put("apdid", com.alipay.security.mobile.module.a.a.d(dVar.f784b));
        dataReportRequest.bizData.put("apdidToken", com.alipay.security.mobile.module.a.a.d(dVar.f785c));
        dataReportRequest.bizData.put("umidToken", com.alipay.security.mobile.module.a.a.d(dVar.d));
        dataReportRequest.bizData.put("dynamicKey", dVar.e);
        dataReportRequest.deviceData = dVar.f == null ? new HashMap<>() : dVar.f;
        return com.alipay.security.mobile.module.http.model.b.a(f787b.a(dataReportRequest));
    }

    public static a a(Context context, String str) {
        com.alipay.security.mobile.module.http.b bVar = null;
        if (context == null) {
            return null;
        }
        if (f786a == null) {
            if (context != null) {
                bVar = com.alipay.security.mobile.module.http.b.a(context, str);
            }
            f787b = bVar;
            f786a = new b();
        }
        return f786a;
    }
}
