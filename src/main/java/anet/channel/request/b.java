package anet.channel.request;

import anet.channel.util.ALog;
import java.util.concurrent.Future;

/* compiled from: Taobao */
public class b implements Cancelable {
    public static final b NULL = new b((Future<?>) null, (String) null);

    /* renamed from: a  reason: collision with root package name */
    private final Future<?> f217a;

    /* renamed from: b  reason: collision with root package name */
    private final String f218b;

    public b(Future<?> future, String str) {
        this.f217a = future;
        this.f218b = str;
    }

    public void cancel() {
        if (this.f217a != null) {
            ALog.i("awcn.FutureCancelable", "cancel request", this.f218b, new Object[0]);
            this.f217a.cancel(true);
        }
    }
}
