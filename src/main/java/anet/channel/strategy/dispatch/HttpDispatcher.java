package anet.channel.strategy.dispatch;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.util.ALog;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Taobao */
public class HttpDispatcher {

    /* renamed from: a  reason: collision with root package name */
    private CopyOnWriteArraySet<IDispatchEventListener> f281a;

    /* renamed from: b  reason: collision with root package name */
    private b f282b;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f283c;
    private Set<String> d;
    private Set<String> e;
    private AtomicBoolean f;

    /* compiled from: Taobao */
    public interface IDispatchEventListener {
        void onEvent(DispatchEvent dispatchEvent);
    }

    /* compiled from: Taobao */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        static HttpDispatcher f284a = new HttpDispatcher();

        private a() {
        }
    }

    public static HttpDispatcher getInstance() {
        return a.f284a;
    }

    private HttpDispatcher() {
        this.f281a = new CopyOnWriteArraySet<>();
        this.f282b = new b();
        this.f283c = true;
        this.d = Collections.newSetFromMap(new ConcurrentHashMap());
        this.e = new TreeSet();
        this.f = new AtomicBoolean();
        a();
    }

    public void sendAmdcRequest(Set<String> set, int i) {
        if (!this.f283c || set == null || set.isEmpty()) {
            ALog.e("awcn.HttpDispatcher", "invalid parameter", (String) null, new Object[0]);
            return;
        }
        if (ALog.isPrintLog(2)) {
            ALog.i("awcn.HttpDispatcher", "sendAmdcRequest", (String) null, DispatchConstants.HOSTS, set.toString());
        }
        HashMap hashMap = new HashMap();
        hashMap.put(DispatchConstants.HOSTS, set);
        hashMap.put(DispatchConstants.CONFIG_VERSION, String.valueOf(i));
        this.f282b.a((Map<String, Object>) hashMap);
    }

    public void addListener(IDispatchEventListener iDispatchEventListener) {
        this.f281a.add(iDispatchEventListener);
    }

    public void removeListener(IDispatchEventListener iDispatchEventListener) {
        this.f281a.remove(iDispatchEventListener);
    }

    /* access modifiers changed from: package-private */
    public void a(DispatchEvent dispatchEvent) {
        Iterator<IDispatchEventListener> it = this.f281a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onEvent(dispatchEvent);
            } catch (Exception unused) {
            }
        }
    }

    public void setEnable(boolean z) {
        this.f283c = z;
    }

    public synchronized void addHosts(List<String> list) {
        if (list != null) {
            this.e.addAll(list);
            this.d.clear();
        }
    }

    public static void setInitHosts(List<String> list) {
        if (list != null) {
            DispatchConstants.initHostArray = (String[]) list.toArray(new String[0]);
        }
    }

    public synchronized Set<String> getInitHosts() {
        a();
        return new HashSet(this.e);
    }

    private void a() {
        if (!this.f.get() && GlobalAppRuntimeInfo.getContext() != null && this.f.compareAndSet(false, true)) {
            this.e.add(DispatchConstants.getAmdcServerDomain());
            if (GlobalAppRuntimeInfo.isTargetProcess()) {
                this.e.addAll(Arrays.asList(DispatchConstants.initHostArray));
            }
        }
    }

    public boolean isInitHostsChanged(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean contains = this.d.contains(str);
        if (!contains) {
            this.d.add(str);
        }
        return !contains;
    }

    public void switchENV() {
        this.d.clear();
        this.e.clear();
        this.f.set(false);
    }
}
