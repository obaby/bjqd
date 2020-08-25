package anet.channel.entity;

import anet.channel.strategy.IConnStrategy;

/* compiled from: Taobao */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final IConnStrategy f175a;

    /* renamed from: b  reason: collision with root package name */
    public int f176b = 0;

    /* renamed from: c  reason: collision with root package name */
    public int f177c = 0;
    private String d;
    private String e;

    public a(String str, String str2, IConnStrategy iConnStrategy) {
        this.f175a = iConnStrategy;
        this.d = str;
        this.e = str2;
    }

    public String a() {
        if (this.f175a != null) {
            return this.f175a.getIp();
        }
        return null;
    }

    public int b() {
        if (this.f175a != null) {
            return this.f175a.getPort();
        }
        return 0;
    }

    public ConnType c() {
        if (this.f175a != null) {
            return ConnType.valueOf(this.f175a.getProtocol());
        }
        return ConnType.HTTP;
    }

    public int d() {
        if (this.f175a == null || this.f175a.getConnectionTimeout() == 0) {
            return com.alipay.sdk.data.a.d;
        }
        return this.f175a.getConnectionTimeout();
    }

    public int e() {
        if (this.f175a == null || this.f175a.getReadTimeout() == 0) {
            return com.alipay.sdk.data.a.d;
        }
        return this.f175a.getReadTimeout();
    }

    public String f() {
        return this.d;
    }

    public int g() {
        if (this.f175a != null) {
            return this.f175a.getHeartbeat();
        }
        return 45000;
    }

    public String h() {
        return this.e;
    }

    public String toString() {
        return "ConnInfo [ip=" + a() + ",port=" + b() + ",type=" + c() + ",hb" + g() + "]";
    }
}
