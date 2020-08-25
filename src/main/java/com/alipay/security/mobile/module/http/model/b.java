package com.alipay.security.mobile.module.http.model;

import com.alipay.security.mobile.module.a.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import java.util.HashMap;
import java.util.Map;

public final class b {
    private static DataReportRequest a(d dVar) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        if (dVar == null) {
            return null;
        }
        dataReportRequest.os = a.d(dVar.f783a);
        dataReportRequest.rpcVersion = dVar.j;
        dataReportRequest.bizType = "1";
        dataReportRequest.bizData = new HashMap();
        dataReportRequest.bizData.put("apdid", a.d(dVar.f784b));
        dataReportRequest.bizData.put("apdidToken", a.d(dVar.f785c));
        dataReportRequest.bizData.put("umidToken", a.d(dVar.d));
        dataReportRequest.bizData.put("dynamicKey", dVar.e);
        dataReportRequest.deviceData = dVar.f == null ? new HashMap<>() : dVar.f;
        return dataReportRequest;
    }

    public static c a(DataReportResult dataReportResult) {
        c cVar = new c();
        if (dataReportResult == null) {
            return null;
        }
        cVar.f780a = dataReportResult.success;
        cVar.f781b = dataReportResult.resultCode;
        Map<String, String> map = dataReportResult.resultData;
        if (map != null) {
            cVar.h = map.get("apdid");
            cVar.i = map.get("apdidToken");
            cVar.l = map.get("dynamicKey");
            cVar.m = map.get("timeInterval");
            cVar.n = map.get("webrtcUrl");
            cVar.o = "";
            String str = map.get("drmSwitch");
            if (a.b(str)) {
                if (str.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str.charAt(0));
                    cVar.j = sb.toString();
                }
                if (str.length() >= 3) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str.charAt(2));
                    cVar.k = sb2.toString();
                }
            }
            if (map.containsKey("apse_degrade")) {
                cVar.p = map.get("apse_degrade");
            }
        }
        return cVar;
    }
}
