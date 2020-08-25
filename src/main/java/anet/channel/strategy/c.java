package anet.channel.strategy;

import anet.channel.util.HttpConstant;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<String, String> f276a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private boolean f277b = true;

    /* compiled from: Taobao */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        public static c f278a = new c();

        private a() {
        }
    }

    public void a(boolean z) {
        this.f277b = z;
    }

    public String a(String str) {
        if (!this.f277b) {
            return null;
        }
        String str2 = this.f276a.get(str);
        if (str2 != null) {
            return str2;
        }
        this.f276a.put(str, "https");
        return "https";
    }

    public void b(String str) {
        this.f276a.put(str, HttpConstant.HTTP);
    }
}
