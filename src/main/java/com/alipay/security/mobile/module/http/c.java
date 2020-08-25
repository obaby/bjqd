package com.alipay.security.mobile.module.http;

import com.alipay.security.mobile.module.a.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DataReportRequest f776a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ b f777b;

    c(b bVar, DataReportRequest dataReportRequest) {
        this.f777b = bVar;
        this.f776a = dataReportRequest;
    }

    public final void run() {
        try {
            DataReportResult unused = b.e = this.f777b.f775c.a();
        } catch (Throwable th) {
            DataReportResult unused2 = b.e = new DataReportResult();
            b.e.success = false;
            DataReportResult a2 = b.e;
            a2.resultCode = "static data rpc upload error, " + a.a(th);
            a.a(th);
        }
    }
}
