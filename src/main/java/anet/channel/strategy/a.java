package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.strategy.utils.c;
import anet.channel.util.ALog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
class a {

    /* renamed from: a  reason: collision with root package name */
    final ConcurrentHashMap<String, List<IPConnStrategy>> f271a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    final HashMap<String, Object> f272b = new HashMap<>();

    a() {
    }

    /* access modifiers changed from: package-private */
    public List a(String str) {
        Object obj;
        if (TextUtils.isEmpty(str) || !c.b(str) || DispatchConstants.getAmdcServerDomain().equalsIgnoreCase(str)) {
            return Collections.EMPTY_LIST;
        }
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.LocalDnsStrategyTable", "try resolve ip with local dns", (String) null, com.alipay.sdk.cons.c.f, str);
        }
        List list = Collections.EMPTY_LIST;
        if (!this.f271a.containsKey(str)) {
            synchronized (this.f272b) {
                if (!this.f272b.containsKey(str)) {
                    obj = new Object();
                    this.f272b.put(str, obj);
                    a(str, obj);
                } else {
                    obj = this.f272b.get(str);
                }
            }
            if (obj != null) {
                try {
                    synchronized (obj) {
                        obj.wait(500);
                    }
                } catch (InterruptedException unused) {
                }
            }
        }
        List list2 = this.f271a.get(str);
        if (!(list2 == null || list2 == Collections.EMPTY_LIST)) {
            list = new ArrayList(list2);
        }
        ALog.i("awcn.LocalDnsStrategyTable", "get local strategy", (String) null, "strategyList", list2);
        return list;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, ConnProtocol connProtocol) {
        List<IPConnStrategy> list = this.f271a.get(str);
        if (list != null && !list.isEmpty()) {
            for (IPConnStrategy protocol : list) {
                if (protocol.getProtocol().equals(connProtocol)) {
                    return;
                }
            }
            list.add(IPConnStrategy.a(((IPConnStrategy) list.get(0)).getIp(), !(connProtocol.protocol.equalsIgnoreCase("https") || !TextUtils.isEmpty(connProtocol.publicKey)) ? 80 : 443, connProtocol, 0, 0, 1, 45000));
            ALog.i("awcn.LocalDnsStrategyTable", "setProtocolForHost", (String) null, "strategyList", list);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, IConnStrategy iConnStrategy, ConnEvent connEvent) {
        List list;
        if (!connEvent.isSuccess && !TextUtils.isEmpty(str) && (list = this.f271a.get(str)) != null && list != Collections.EMPTY_LIST) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (it.next() == iConnStrategy) {
                    it.remove();
                }
            }
            if (list.isEmpty()) {
                this.f271a.put(str, Collections.EMPTY_LIST);
            }
        }
    }

    private void a(String str, Object obj) {
        anet.channel.strategy.utils.a.a(new b(this, str, obj));
    }
}
