package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.strategy.c;
import anet.channel.strategy.dispatch.DispatchEvent;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.strategy.k;
import anet.channel.strategy.utils.a;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import anet.channel.util.StringUtils;
import com.alipay.sdk.util.j;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* compiled from: Taobao */
class g implements IStrategyInstance, HttpDispatcher.IDispatchEventListener {

    /* renamed from: a  reason: collision with root package name */
    boolean f299a = false;

    /* renamed from: b  reason: collision with root package name */
    StrategyInfoHolder f300b = null;

    /* renamed from: c  reason: collision with root package name */
    long f301c = 0;
    CopyOnWriteArraySet<IStrategyListener> d = new CopyOnWriteArraySet<>();

    g() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0043, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initialize(android.content.Context r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.f299a     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0042
            if (r6 != 0) goto L_0x0008
            goto L_0x0042
        L_0x0008:
            r0 = 0
            r1 = 0
            java.lang.String r2 = "awcn.StrategyCenter"
            java.lang.String r3 = "StrategyCenter initialize started."
            java.lang.Object[] r4 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0036 }
            anet.channel.util.ALog.i(r2, r3, r1, r4)     // Catch:{ Exception -> 0x0036 }
            anet.channel.strategy.dispatch.a.a((android.content.Context) r6)     // Catch:{ Exception -> 0x0036 }
            anet.channel.strategy.l.a((android.content.Context) r6)     // Catch:{ Exception -> 0x0036 }
            anet.channel.status.NetworkStatusHelper.startListener(r6)     // Catch:{ Exception -> 0x0036 }
            anet.channel.strategy.dispatch.HttpDispatcher r6 = anet.channel.strategy.dispatch.HttpDispatcher.getInstance()     // Catch:{ Exception -> 0x0036 }
            r6.addListener(r5)     // Catch:{ Exception -> 0x0036 }
            anet.channel.strategy.StrategyInfoHolder r6 = anet.channel.strategy.StrategyInfoHolder.a()     // Catch:{ Exception -> 0x0036 }
            r5.f300b = r6     // Catch:{ Exception -> 0x0036 }
            r6 = 1
            r5.f299a = r6     // Catch:{ Exception -> 0x0036 }
            java.lang.String r6 = "awcn.StrategyCenter"
            java.lang.String r2 = "StrategyCenter initialize finished."
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0036 }
            anet.channel.util.ALog.i(r6, r2, r1, r3)     // Catch:{ Exception -> 0x0036 }
            goto L_0x0040
        L_0x0036:
            r6 = move-exception
            java.lang.String r2 = "awcn.StrategyCenter"
            java.lang.String r3 = "StrategyCenter initialize failed."
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0044 }
            anet.channel.util.ALog.e(r2, r3, r1, r6, r0)     // Catch:{ all -> 0x0044 }
        L_0x0040:
            monitor-exit(r5)
            return
        L_0x0042:
            monitor-exit(r5)
            return
        L_0x0044:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.strategy.g.initialize(android.content.Context):void");
    }

    public synchronized void switchEnv() {
        if (this.f300b != null) {
            this.f300b.b();
            this.f300b = StrategyInfoHolder.a();
        }
        l.a();
        HttpDispatcher.getInstance().switchENV();
    }

    @Deprecated
    public String getSchemeByHost(String str) {
        return getSchemeByHost(str, (String) null);
    }

    public String getSchemeByHost(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (a()) {
            return str2;
        }
        String a2 = this.f300b.f262b.a(str);
        if (a2 != null || TextUtils.isEmpty(str2)) {
            str2 = a2;
        }
        if (str2 == null && (str2 = c.a.f278a.a(str)) == null) {
            str2 = HttpConstant.HTTP;
        }
        ALog.d("awcn.StrategyCenter", "getSchemeByHost", (String) null, com.alipay.sdk.cons.c.f, str, "scheme", str2);
        return str2;
    }

    public String getCNameByHost(String str) {
        if (a() || TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f300b.d().getCnameByHost(str);
    }

    public String getFormalizeUrl(String str) {
        HttpUrl parse = HttpUrl.parse(str);
        if (parse == null) {
            ALog.e("awcn.StrategyCenter", "url is invalid.", (String) null, "URL", str);
            return null;
        }
        String urlString = parse.urlString();
        try {
            String schemeByHost = getSchemeByHost(parse.host(), parse.scheme());
            if (!schemeByHost.equalsIgnoreCase(parse.scheme())) {
                urlString = StringUtils.concatString(schemeByHost, ":", str.substring(str.indexOf("//")));
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.StrategyCenter", "", (String) null, "raw", StringUtils.simplifyString(str, 128), "ret", StringUtils.simplifyString(urlString, 128));
            }
        } catch (Exception e) {
            ALog.e("awcn.StrategyCenter", "getFormalizeUrl failed", (String) null, e, "raw", str);
        }
        return urlString;
    }

    public List<IConnStrategy> getConnStrategyListByHost(String str) {
        if (TextUtils.isEmpty(str) || a()) {
            return Collections.EMPTY_LIST;
        }
        String cnameByHost = this.f300b.d().getCnameByHost(str);
        if (!TextUtils.isEmpty(cnameByHost)) {
            str = cnameByHost;
        }
        List<IConnStrategy> queryByHost = this.f300b.d().queryByHost(str);
        if (queryByHost.isEmpty()) {
            queryByHost = this.f300b.f263c.a(str);
        }
        if (ALog.isPrintLog(1)) {
            ALog.d("getConnStrategyListByHost", (String) null, com.alipay.sdk.cons.c.f, str, j.f740c, queryByHost);
        }
        return queryByHost;
    }

    public void forceRefreshStrategy(String str) {
        if (!a() && !TextUtils.isEmpty(str)) {
            ALog.i("awcn.StrategyCenter", "force refresh strategy", (String) null, com.alipay.sdk.cons.c.f, str);
            this.f300b.d().a(str, true);
        }
    }

    public void registerListener(IStrategyListener iStrategyListener) {
        ALog.e("awcn.StrategyCenter", "registerListener", (String) null, "listener", this.d);
        if (iStrategyListener != null) {
            this.d.add(iStrategyListener);
        }
    }

    public void unregisterListener(IStrategyListener iStrategyListener) {
        ALog.e("awcn.StrategyCenter", "unregisterListener", (String) null, "listener", this.d);
        this.d.remove(iStrategyListener);
    }

    public String getUnitByHost(String str) {
        if (a()) {
            return null;
        }
        return this.f300b.f262b.b(str);
    }

    public String getClientIp() {
        if (a()) {
            return "";
        }
        return this.f300b.d().f268b;
    }

    public void notifyConnEvent(String str, IConnStrategy iConnStrategy, ConnEvent connEvent) {
        if (!a() && iConnStrategy != null && (iConnStrategy instanceof IPConnStrategy)) {
            IPConnStrategy iPConnStrategy = (IPConnStrategy) iConnStrategy;
            if (iPConnStrategy.f253b == 1) {
                this.f300b.f263c.a(str, iConnStrategy, connEvent);
            } else if (iPConnStrategy.f253b == 0) {
                this.f300b.d().a(str, iConnStrategy, connEvent);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean a() {
        if (this.f300b != null) {
            return false;
        }
        ALog.w("StrategyCenter not initialized", (String) null, "isInitialized", Boolean.valueOf(this.f299a));
        return true;
    }

    public void onEvent(DispatchEvent dispatchEvent) {
        if (dispatchEvent.eventType == 1 && this.f300b != null) {
            ALog.d("awcn.StrategyCenter", "receive amdc event", (String) null, new Object[0]);
            k.d a2 = k.a((JSONObject) dispatchEvent.extraObject);
            if (a2 != null) {
                this.f300b.a(a2);
                saveData();
                Iterator<IStrategyListener> it = this.d.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onStrategyUpdated(a2);
                    } catch (Exception e) {
                        ALog.e("awcn.StrategyCenter", "onStrategyUpdated failed", (String) null, e, new Object[0]);
                    }
                }
            }
        }
    }

    public synchronized void saveData() {
        ALog.i("awcn.StrategyCenter", "saveData", (String) null, new Object[0]);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f301c > 30000) {
            this.f301c = currentTimeMillis;
            a.a(new h(this), 500);
        }
    }
}
