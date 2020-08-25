package com.alipay.android.phone.mrpc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

public final class o extends t {

    /* renamed from: b  reason: collision with root package name */
    private String f538b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f539c;
    private String d = "application/x-www-form-urlencoded";
    private ArrayList<Header> e = new ArrayList<>();
    private Map<String, String> f = new HashMap();
    private boolean g;

    public o(String str) {
        this.f538b = str;
    }

    public final String a() {
        return this.f538b;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public final void a(Header header) {
        this.e.add(header);
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void a(byte[] bArr) {
        this.f539c = bArr;
    }

    public final String b(String str) {
        if (this.f == null) {
            return null;
        }
        return this.f.get(str);
    }

    public final byte[] b() {
        return this.f539c;
    }

    public final String c() {
        return this.d;
    }

    public final ArrayList<Header> d() {
        return this.e;
    }

    public final boolean e() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.f539c == null) {
            if (oVar.f539c != null) {
                return false;
            }
        } else if (!this.f539c.equals(oVar.f539c)) {
            return false;
        }
        if (this.f538b == null) {
            if (oVar.f538b != null) {
                return false;
            }
        } else if (!this.f538b.equals(oVar.f538b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((this.f == null || !this.f.containsKey("id")) ? 1 : this.f.get("id").hashCode() + 31) * 31) + (this.f538b == null ? 0 : this.f538b.hashCode());
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{this.f538b, this.e});
    }
}
