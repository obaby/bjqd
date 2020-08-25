package anet.channel.monitor;

/* compiled from: Taobao */
public enum NetworkSpeed {
    Slow("弱网络", 1),
    Fast("强网络", 5);
    

    /* renamed from: a  reason: collision with root package name */
    private final String f192a;

    /* renamed from: b  reason: collision with root package name */
    private final int f193b;

    private NetworkSpeed(String str, int i) {
        this.f192a = str;
        this.f193b = i;
    }

    public String getDesc() {
        return this.f192a;
    }

    public int getCode() {
        return this.f193b;
    }

    public static NetworkSpeed valueOfCode(int i) {
        return i == 1 ? Slow : Fast;
    }
}
