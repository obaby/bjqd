package anet.channel;

import android.text.TextUtils;
import anet.channel.entity.ConnType;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import com.alipay.sdk.cons.c;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/* compiled from: Taobao */
class AccsSessionManager {

    /* renamed from: a  reason: collision with root package name */
    SessionCenter f107a = null;

    /* renamed from: b  reason: collision with root package name */
    Set<String> f108b = Collections.EMPTY_SET;

    AccsSessionManager(SessionCenter sessionCenter) {
        this.f107a = sessionCenter;
    }

    public synchronized void checkAndStartSession() {
        Collection<SessionInfo> a2 = this.f107a.g.a();
        Set<String> set = Collections.EMPTY_SET;
        if (!a2.isEmpty()) {
            set = new TreeSet<>();
        }
        for (SessionInfo next : a2) {
            if (next.isKeepAlive) {
                set.add(StringUtils.concatString(StrategyCenter.getInstance().getSchemeByHost(next.host, next.isAccs ? "https" : HttpConstant.HTTP), HttpConstant.SCHEME_SPLIT, next.host));
            }
        }
        for (String next2 : this.f108b) {
            if (!set.contains(next2)) {
                a(next2);
            }
        }
        if (a()) {
            for (String next3 : set) {
                try {
                    this.f107a.get(next3, ConnType.TypeLevel.SPDY, 0);
                } catch (Exception unused) {
                    ALog.e("start session failed", (String) null, c.f, next3);
                }
            }
            this.f108b = set;
        }
    }

    public synchronized void forceCloseSession(boolean z) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.AccsSessionManager", "forceCloseSession", this.f107a.f127c, "reCreate", Boolean.valueOf(z));
        }
        for (String a2 : this.f108b) {
            a(a2);
        }
        if (z) {
            checkAndStartSession();
        }
    }

    private boolean a() {
        if (!GlobalAppRuntimeInfo.isAppBackground() && NetworkStatusHelper.isConnected()) {
            return true;
        }
        return false;
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            ALog.d("awcn.AccsSessionManager", "closeSessions", this.f107a.f127c, c.f, str);
            this.f107a.a(str).b(false);
        }
    }
}
