package anet.channel.util;

import android.util.Base64;
import java.net.InetSocketAddress;
import java.net.Proxy;

/* compiled from: Taobao */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static c f341a;

    /* renamed from: b  reason: collision with root package name */
    private final Proxy f342b;

    /* renamed from: c  reason: collision with root package name */
    private final String f343c;
    private final String d;

    public static c a() {
        return f341a;
    }

    public Proxy b() {
        return this.f342b;
    }

    public c(String str, int i, String str2, String str3) {
        this.f342b = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        this.f343c = str2;
        this.d = str3;
    }

    public String c() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f343c);
        sb.append(":");
        sb.append(this.d);
        String encodeToString = Base64.encodeToString(sb.toString().getBytes(), 0);
        StringBuilder sb2 = new StringBuilder(64);
        sb2.append("Basic ");
        sb2.append(encodeToString);
        return sb2.toString();
    }
}
