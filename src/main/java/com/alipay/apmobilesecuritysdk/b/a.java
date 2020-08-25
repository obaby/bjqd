package com.alipay.apmobilesecuritysdk.b;

public final class a {

    /* renamed from: b  reason: collision with root package name */
    private static a f560b = new a();

    /* renamed from: a  reason: collision with root package name */
    private int f561a = 0;

    public static a a() {
        return f560b;
    }

    public final void a(int i) {
        this.f561a = i;
    }

    public final int b() {
        return this.f561a;
    }

    public final String c() {
        if (com.alipay.security.mobile.module.a.a.b((String) null)) {
            return null;
        }
        switch (this.f561a) {
            case 1:
                return "http://mobilegw.stable.alipay.net/mgw.htm";
            case 2:
                return "https://mobilegw.alipay.com/mgw.htm";
            case 3:
                return "http://mobilegw-1-64.test.alipay.net/mgw.htm";
            case 4:
                return "http://mobilegw.aaa.alipay.net/mgw.htm";
            default:
                return "https://mobilegw.alipay.com/mgw.htm";
        }
    }
}
