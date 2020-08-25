package anet.channel.security;

/* compiled from: Taobao */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ISecurityFactory f226a;

    public static ISecurityFactory a() {
        if (f226a == null) {
            f226a = new d();
        }
        return f226a;
    }
}
