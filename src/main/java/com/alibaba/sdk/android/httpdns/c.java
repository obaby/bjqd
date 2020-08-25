package com.alibaba.sdk.android.httpdns;

import com.alibaba.sdk.android.httpdns.a.b;
import com.alibaba.sdk.android.httpdns.a.e;
import com.alibaba.sdk.android.httpdns.a.g;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

class c {

    /* renamed from: a  reason: collision with root package name */
    private String[] f465a;

    /* renamed from: b  reason: collision with root package name */
    private long f466b;

    /* renamed from: b  reason: collision with other field name */
    private String f5b;

    /* renamed from: c  reason: collision with root package name */
    private long f467c;

    c(e eVar) {
        int size;
        this.f5b = eVar.j;
        this.f467c = com.alibaba.sdk.android.httpdns.a.c.a(eVar.l);
        if (eVar.f460a != null && eVar.f460a.size() > 0 && (size = eVar.f460a.size()) > 0) {
            this.f466b = com.alibaba.sdk.android.httpdns.a.c.a(eVar.f460a.get(0).n);
            this.f465a = new String[size];
            for (int i = 0; i < size; i++) {
                this.f465a[i] = eVar.f460a.get(i).m;
            }
        }
    }

    c(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.f5b = jSONObject.getString(com.alipay.sdk.cons.c.f);
        JSONArray jSONArray = jSONObject.getJSONArray("ips");
        int length = jSONArray.length();
        this.f465a = new String[length];
        for (int i = 0; i < length; i++) {
            this.f465a[i] = jSONArray.getString(i);
        }
        this.f466b = jSONObject.getLong("ttl");
        this.f467c = System.currentTimeMillis() / 1000;
    }

    /* access modifiers changed from: package-private */
    public long a() {
        return this.f466b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public e m8a() {
        e eVar = new e();
        eVar.j = this.f5b;
        eVar.l = String.valueOf(this.f467c);
        eVar.k = b.g();
        if (this.f465a != null && this.f465a.length > 0) {
            eVar.f460a = new ArrayList<>();
            for (String str : this.f465a) {
                g gVar = new g();
                gVar.m = str;
                gVar.n = String.valueOf(this.f466b);
                eVar.f460a.add(gVar);
            }
        }
        return eVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public String[] m9a() {
        return this.f465a;
    }

    /* access modifiers changed from: package-private */
    public long b() {
        return this.f467c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b  reason: collision with other method in class */
    public boolean m10b() {
        return b() + a() < System.currentTimeMillis() / 1000;
    }

    public String toString() {
        String str = "host: " + this.f5b + " ip cnt: " + this.f465a.length + " ttl: " + this.f466b;
        for (int i = 0; i < this.f465a.length; i++) {
            str = str + "\n ip: " + this.f465a[i];
        }
        return str;
    }
}
