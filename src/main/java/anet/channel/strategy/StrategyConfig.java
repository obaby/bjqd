package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.strategy.k;
import anet.channel.strategy.utils.SerialLruCache;
import anet.channel.strategy.utils.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
class StrategyConfig implements Serializable {
    public static final String NO_RESULT = "No_Result";

    /* renamed from: a  reason: collision with root package name */
    private SerialLruCache<String, String> f258a = null;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, String> f259b = null;

    /* renamed from: c  reason: collision with root package name */
    private transient StrategyInfoHolder f260c = null;

    StrategyConfig() {
    }

    /* access modifiers changed from: package-private */
    public void a(StrategyInfoHolder strategyInfoHolder) {
        this.f260c = strategyInfoHolder;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.f258a == null) {
            this.f258a = new SerialLruCache<>(256);
        }
        if (this.f259b == null) {
            this.f259b = new ConcurrentHashMap();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(k.d dVar) {
        if (dVar.f316b != null) {
            synchronized (this) {
                TreeMap treeMap = null;
                for (k.b bVar : dVar.f316b) {
                    if (bVar.j) {
                        this.f258a.remove(bVar.f310a);
                    } else if (bVar.d != null) {
                        if (treeMap == null) {
                            treeMap = new TreeMap();
                        }
                        treeMap.put(bVar.f310a, bVar.d);
                    } else {
                        if (HttpConstant.HTTP.equalsIgnoreCase(bVar.f312c) || "https".equalsIgnoreCase(bVar.f312c)) {
                            this.f258a.put(bVar.f310a, bVar.f312c);
                        } else {
                            this.f258a.put(bVar.f310a, NO_RESULT);
                        }
                        if (!TextUtils.isEmpty(bVar.e)) {
                            this.f259b.put(bVar.f310a, bVar.e);
                        } else {
                            this.f259b.remove(bVar.f310a);
                        }
                    }
                }
                if (treeMap != null) {
                    for (Map.Entry entry : treeMap.entrySet()) {
                        String str = (String) entry.getValue();
                        if (this.f258a.containsKey(str)) {
                            this.f258a.put(entry.getKey(), this.f258a.get(str));
                        } else {
                            this.f258a.put(entry.getKey(), NO_RESULT);
                        }
                    }
                }
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.StrategyConfig", "", (String) null, "SchemeMap", this.f258a.toString());
                ALog.d("awcn.StrategyConfig", "", (String) null, "UnitMap", this.f259b.toString());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str) || !c.b(str)) {
            return null;
        }
        synchronized (this.f258a) {
            str2 = (String) this.f258a.get(str);
            if (str2 == null) {
                this.f258a.put(str, NO_RESULT);
            }
        }
        if (str2 == null) {
            this.f260c.d().a(str, false);
        } else if (NO_RESULT.equals(str2)) {
            return null;
        }
        return str2;
    }

    /* access modifiers changed from: package-private */
    public String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f259b.get(str);
    }
}
