package anet.channel.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
public class StrategyTemplate {
    Map<String, ConnProtocol> templateMap = new ConcurrentHashMap();

    public static StrategyTemplate getInstance() {
        return a.f270a;
    }

    /* compiled from: Taobao */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        static StrategyTemplate f270a = new StrategyTemplate();

        a() {
        }
    }

    public void registerConnProtocol(String str, ConnProtocol connProtocol) {
        if (connProtocol != null) {
            this.templateMap.put(str, connProtocol);
            try {
                IStrategyInstance instance = StrategyCenter.getInstance();
                if (instance instanceof g) {
                    ((g) instance).f300b.f263c.a(str, connProtocol);
                }
            } catch (Exception unused) {
            }
        }
    }

    public ConnProtocol getConnProtocol(String str) {
        return this.templateMap.get(str);
    }
}
