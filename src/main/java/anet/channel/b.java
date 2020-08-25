package anet.channel;

import android.text.TextUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
class b {

    /* renamed from: a  reason: collision with root package name */
    Map<String, Integer> f146a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    Map<String, SessionInfo> f147b = new ConcurrentHashMap();

    b() {
    }

    /* access modifiers changed from: package-private */
    public void a(SessionInfo sessionInfo) {
        if (sessionInfo == null) {
            throw new NullPointerException("info is null");
        } else if (!TextUtils.isEmpty(sessionInfo.host)) {
            this.f147b.put(sessionInfo.host, sessionInfo);
        } else {
            throw new IllegalArgumentException("host cannot be null or empty");
        }
    }

    /* access modifiers changed from: package-private */
    public SessionInfo a(String str) {
        return this.f147b.remove(str);
    }

    /* access modifiers changed from: package-private */
    public SessionInfo b(String str) {
        return this.f147b.get(str);
    }

    /* access modifiers changed from: package-private */
    public Collection<SessionInfo> a() {
        return this.f147b.values();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.f146a) {
                this.f146a.put(str, Integer.valueOf(i));
            }
            return;
        }
        throw new IllegalArgumentException("host cannot be null or empty");
    }

    public int c(String str) {
        Integer num;
        synchronized (this.f146a) {
            num = this.f146a.get(str);
        }
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }
}
