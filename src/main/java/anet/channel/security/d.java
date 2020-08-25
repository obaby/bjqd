package anet.channel.security;

/* compiled from: Taobao */
final class d implements ISecurityFactory {
    d() {
    }

    public ISecurity createSecurity(String str) {
        return new b(str);
    }

    public ISecurity createNonSecurity(String str) {
        return new a(str);
    }
}
