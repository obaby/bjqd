package anet.channel.monitor;

import anet.channel.util.ALog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f194a;

    /* renamed from: b  reason: collision with root package name */
    private Map<INetworkQualityChangeListener, f> f195b = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    private f f196c = new f();

    private a() {
    }

    public static a a() {
        if (f194a == null) {
            synchronized (a.class) {
                if (f194a == null) {
                    f194a = new a();
                }
            }
        }
        return f194a;
    }

    public void a(INetworkQualityChangeListener iNetworkQualityChangeListener, f fVar) {
        if (iNetworkQualityChangeListener == null) {
            ALog.e("BandWidthListenerHelp", "listener is null", (String) null, new Object[0]);
        } else if (fVar == null) {
            this.f196c.f209b = System.currentTimeMillis();
            this.f195b.put(iNetworkQualityChangeListener, this.f196c);
        } else {
            fVar.f209b = System.currentTimeMillis();
            this.f195b.put(iNetworkQualityChangeListener, fVar);
        }
    }

    public void a(INetworkQualityChangeListener iNetworkQualityChangeListener) {
        this.f195b.remove(iNetworkQualityChangeListener);
    }

    public void a(double d) {
        boolean a2;
        for (Map.Entry next : this.f195b.entrySet()) {
            INetworkQualityChangeListener iNetworkQualityChangeListener = (INetworkQualityChangeListener) next.getKey();
            f fVar = (f) next.getValue();
            if (!(iNetworkQualityChangeListener == null || fVar == null || fVar.b() || fVar.f208a == (a2 = fVar.a(d)))) {
                fVar.f208a = a2;
                iNetworkQualityChangeListener.onNetworkQualityChanged(a2 ? NetworkSpeed.Slow : NetworkSpeed.Fast);
            }
        }
    }
}
