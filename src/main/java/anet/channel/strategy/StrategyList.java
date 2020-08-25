package anet.channel.strategy;

import anet.channel.strategy.k;
import anet.channel.strategy.utils.SerialLruCache;
import anet.channel.strategy.utils.c;
import anet.channel.util.ALog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* compiled from: Taobao */
class StrategyList implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private List<IPConnStrategy> f264a = new ArrayList();
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public Map<Integer, ConnHistoryItem> f265b = new SerialLruCache(40);

    /* renamed from: c  reason: collision with root package name */
    private boolean f266c = false;
    private transient Comparator<IPConnStrategy> d = null;

    /* compiled from: Taobao */
    private interface Predicate<T> {
        boolean apply(T t);
    }

    public StrategyList() {
    }

    StrategyList(List<IPConnStrategy> list) {
        this.f264a = list;
    }

    public void checkInit() {
        if (this.f264a == null) {
            this.f264a = new ArrayList();
        }
        if (this.f265b == null) {
            this.f265b = new SerialLruCache(40);
        }
        Iterator<Map.Entry<Integer, ConnHistoryItem>> it = this.f265b.entrySet().iterator();
        while (it.hasNext()) {
            if (((ConnHistoryItem) it.next().getValue()).d()) {
                it.remove();
            }
        }
        for (IPConnStrategy next : this.f264a) {
            if (!this.f265b.containsKey(Integer.valueOf(next.getUniqueId()))) {
                this.f265b.put(Integer.valueOf(next.getUniqueId()), new ConnHistoryItem());
            }
        }
        Collections.sort(this.f264a, a());
    }

    public String toString() {
        return this.f264a.toString();
    }

    public List<IConnStrategy> getStrategyList() {
        ArrayList arrayList = null;
        for (IPConnStrategy next : this.f264a) {
            if (this.f265b.get(Integer.valueOf(next.getUniqueId())).c()) {
                ALog.i("awcn.StrategyList", "strategy ban!", (String) null, "strategy", next);
            } else {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(next);
            }
        }
        return arrayList == null ? Collections.EMPTY_LIST : arrayList;
    }

    public void update(k.b bVar) {
        for (IPConnStrategy iPConnStrategy : this.f264a) {
            iPConnStrategy.f254c = true;
        }
        if (bVar.i != null) {
            for (k.e eVar : bVar.i) {
                a(eVar.f318a, c.b(eVar.f318a) ? -1 : 1, eVar.f319b);
            }
        }
        for (int i = 0; i < bVar.h.length; i++) {
            for (String a2 : bVar.f) {
                a(a2, 1, bVar.h[i]);
            }
            if (bVar.g != null) {
                this.f266c = true;
                for (String a3 : bVar.g) {
                    a(a3, 0, bVar.h[i]);
                }
            } else {
                this.f266c = false;
            }
        }
        ListIterator<IPConnStrategy> listIterator = this.f264a.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().f254c) {
                listIterator.remove();
            }
        }
        Collections.sort(this.f264a, a());
    }

    private void a(String str, int i, k.a aVar) {
        int a2 = a(this.f264a, new i(this, aVar, str, ConnProtocol.valueOf(aVar)));
        if (a2 != -1) {
            IPConnStrategy iPConnStrategy = this.f264a.get(a2);
            iPConnStrategy.cto = aVar.f309c;
            iPConnStrategy.rto = aVar.d;
            iPConnStrategy.heartbeat = aVar.f;
            iPConnStrategy.f252a = i;
            iPConnStrategy.f253b = 0;
            iPConnStrategy.f254c = false;
            return;
        }
        IPConnStrategy a3 = IPConnStrategy.a(str, aVar);
        if (a3 != null) {
            a3.f252a = i;
            a3.f253b = 0;
            if (!this.f265b.containsKey(Integer.valueOf(a3.getUniqueId()))) {
                this.f265b.put(Integer.valueOf(a3.getUniqueId()), new ConnHistoryItem());
            }
            this.f264a.add(a3);
        }
    }

    public boolean shouldRefresh() {
        boolean z = true;
        boolean z2 = true;
        for (IPConnStrategy next : this.f264a) {
            if (!this.f265b.get(Integer.valueOf(next.getUniqueId())).b()) {
                if (next.f252a == 0) {
                    z = false;
                }
                z2 = false;
            }
        }
        if ((!this.f266c || !z) && !z2) {
            return false;
        }
        return true;
    }

    public void notifyConnEvent(IConnStrategy iConnStrategy, ConnEvent connEvent) {
        if ((iConnStrategy instanceof IPConnStrategy) && this.f264a.indexOf(iConnStrategy) != -1) {
            this.f265b.get(Integer.valueOf(((IPConnStrategy) iConnStrategy).getUniqueId())).a(connEvent.isSuccess);
            Collections.sort(this.f264a, this.d);
        }
    }

    private Comparator a() {
        if (this.d == null) {
            this.d = new j(this);
        }
        return this.d;
    }

    private static <T> int a(Collection<T> collection, Predicate<T> predicate) {
        if (collection == null) {
            return -1;
        }
        int i = 0;
        Iterator<T> it = collection.iterator();
        while (it.hasNext() && !predicate.apply(it.next())) {
            i++;
        }
        if (i == collection.size()) {
            return -1;
        }
        return i;
    }
}
