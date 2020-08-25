package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.strategy.dispatch.a;
import anet.channel.strategy.k;
import anet.channel.strategy.utils.SerialLruCache;
import anet.channel.strategy.utils.c;
import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* compiled from: Taobao */
class StrategyTable implements Serializable {
    protected static Comparator<StrategyCollection> d = new n();

    /* renamed from: a  reason: collision with root package name */
    protected String f267a;

    /* renamed from: b  reason: collision with root package name */
    protected volatile String f268b;

    /* renamed from: c  reason: collision with root package name */
    protected transient boolean f269c = false;
    private HostLruCache e;
    private volatile transient int f;

    /* compiled from: Taobao */
    private static class HostLruCache extends SerialLruCache<String, StrategyCollection> {
        public HostLruCache(int i) {
            super(i);
        }

        /* access modifiers changed from: protected */
        public boolean entryRemoved(Map.Entry<String, StrategyCollection> entry) {
            if (!entry.getValue().e) {
                return true;
            }
            Iterator it = entrySet().iterator();
            while (it.hasNext()) {
                if (!((StrategyCollection) ((Map.Entry) it.next()).getValue()).e) {
                    it.remove();
                    return false;
                }
            }
            return false;
        }
    }

    protected StrategyTable(String str) {
        this.f267a = str;
        a();
    }

    private void b() {
        if (HttpDispatcher.getInstance().isInitHostsChanged(this.f267a)) {
            for (String next : HttpDispatcher.getInstance().getInitHosts()) {
                this.e.put(next, new StrategyCollection(next));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.e == null) {
            this.e = new HostLruCache(256);
            b();
        }
        for (StrategyCollection checkInit : this.e.values()) {
            checkInit.checkInit();
        }
        int i = 0;
        ALog.i("awcn.StrategyTable", "strategy map", (String) null, "size", Integer.valueOf(this.e.size()));
        if (!GlobalAppRuntimeInfo.isTargetProcess()) {
            i = -1;
        }
        this.f = i;
    }

    public List<IConnStrategy> queryByHost(String str) {
        StrategyCollection strategyCollection;
        if (TextUtils.isEmpty(str) || !c.b(str)) {
            return Collections.EMPTY_LIST;
        }
        c();
        synchronized (this.e) {
            strategyCollection = (StrategyCollection) this.e.get(str);
            if (strategyCollection == null) {
                strategyCollection = new StrategyCollection(str);
                this.e.put(str, strategyCollection);
            }
        }
        if (strategyCollection.f257c == 0 || (strategyCollection.isExpired() && a.a() == 0)) {
            a(str);
        }
        return strategyCollection.queryStrategyList();
    }

    public String getCnameByHost(String str) {
        StrategyCollection strategyCollection;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.e) {
            strategyCollection = (StrategyCollection) this.e.get(str);
        }
        if (strategyCollection != null && strategyCollection.isExpired() && a.a() == 0) {
            a(str);
        }
        if (strategyCollection != null) {
            return strategyCollection.d;
        }
        return null;
    }

    public void update(k.d dVar) {
        ALog.i("awcn.StrategyTable", "update strategyTable with httpDns response", this.f267a, new Object[0]);
        try {
            this.f268b = dVar.f315a;
            this.f = dVar.f;
            k.b[] bVarArr = dVar.f316b;
            if (bVarArr != null) {
                synchronized (this.e) {
                    for (k.b bVar : bVarArr) {
                        if (bVar != null) {
                            if (bVar.f310a != null) {
                                if (bVar.j) {
                                    this.e.remove(bVar.f310a);
                                } else {
                                    StrategyCollection strategyCollection = (StrategyCollection) this.e.get(bVar.f310a);
                                    if (strategyCollection == null) {
                                        strategyCollection = new StrategyCollection(bVar.f310a);
                                        this.e.put(bVar.f310a, strategyCollection);
                                    }
                                    strategyCollection.update(bVar);
                                }
                            }
                        }
                    }
                }
                if (ALog.isPrintLog(1)) {
                    StringBuilder sb = new StringBuilder("uniqueId : ");
                    sb.append(this.f267a);
                    sb.append("\n-------------------------domains:------------------------------------");
                    ALog.d("awcn.StrategyTable", sb.toString(), (String) null, new Object[0]);
                    synchronized (this.e) {
                        for (Map.Entry entry : this.e.entrySet()) {
                            sb.setLength(0);
                            sb.append((String) entry.getKey());
                            sb.append(" = ");
                            sb.append(((StrategyCollection) entry.getValue()).toString());
                            ALog.d("awcn.StrategyTable", sb.toString(), (String) null, new Object[0]);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            ALog.e("awcn.StrategyTable", "fail to update strategyTable", this.f267a, th, new Object[0]);
        }
    }

    private void a(String str) {
        TreeSet treeSet = new TreeSet();
        treeSet.add(str);
        a((Set<String>) treeSet);
    }

    /* access modifiers changed from: protected */
    public void a(String str, boolean z) {
        StrategyCollection strategyCollection;
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.e) {
                strategyCollection = (StrategyCollection) this.e.get(str);
                if (strategyCollection == null) {
                    strategyCollection = new StrategyCollection(str);
                    this.e.put(str, strategyCollection);
                }
            }
            if (z || strategyCollection.f257c == 0 || (strategyCollection.isExpired() && a.a() == 0)) {
                a(str);
            }
        }
    }

    private void a(Set<String> set) {
        if (set != null && !set.isEmpty()) {
            if ((!GlobalAppRuntimeInfo.isAppBackground() || AppLifecycle.lastEnterBackgroundTime <= 0) && NetworkStatusHelper.isConnected()) {
                int a2 = a.a();
                if (a2 != 3) {
                    long currentTimeMillis = System.currentTimeMillis();
                    synchronized (this.e) {
                        for (String str : set) {
                            StrategyCollection strategyCollection = (StrategyCollection) this.e.get(str);
                            if (strategyCollection != null) {
                                strategyCollection.f257c = 30000 + currentTimeMillis;
                            }
                        }
                    }
                    if (a2 == 0) {
                        b(set);
                    }
                    HttpDispatcher.getInstance().sendAmdcRequest(set, this.f);
                    return;
                }
                return;
            }
            ALog.i("awcn.StrategyTable", "app in background or no network", this.f267a, new Object[0]);
        }
    }

    private void b(Set<String> set) {
        TreeSet treeSet = new TreeSet(d);
        synchronized (this.e) {
            treeSet.addAll(this.e.values());
        }
        long currentTimeMillis = System.currentTimeMillis();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            StrategyCollection strategyCollection = (StrategyCollection) it.next();
            if (strategyCollection.isExpired() && set.size() < 40) {
                strategyCollection.f257c = 30000 + currentTimeMillis;
                set.add(strategyCollection.f255a);
            } else {
                return;
            }
        }
    }

    private void c() {
        try {
            if (HttpDispatcher.getInstance().isInitHostsChanged(this.f267a)) {
                TreeSet treeSet = null;
                synchronized (this.e) {
                    for (String next : HttpDispatcher.getInstance().getInitHosts()) {
                        if (!this.e.containsKey(next)) {
                            this.e.put(next, new StrategyCollection(next));
                            if (treeSet == null) {
                                treeSet = new TreeSet();
                            }
                            treeSet.add(next);
                        }
                    }
                }
                if (treeSet != null) {
                    a((Set<String>) treeSet);
                }
            }
        } catch (Exception e2) {
            ALog.e("awcn.StrategyTable", "checkInitHost failed", this.f267a, e2, new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, IConnStrategy iConnStrategy, ConnEvent connEvent) {
        StrategyCollection strategyCollection;
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.StrategyTable", "[notifyConnEvent]", (String) null, "Host", str, "IConnStrategy", iConnStrategy, "ConnEvent", connEvent);
        }
        synchronized (this.e) {
            strategyCollection = (StrategyCollection) this.e.get(str);
        }
        if (strategyCollection != null) {
            strategyCollection.notifyConnEvent(iConnStrategy, connEvent);
        }
    }
}
