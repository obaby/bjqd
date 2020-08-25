package anetwork.channel.unified;

import anetwork.channel.entity.g;
import anetwork.channel.interceptor.Callback;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Taobao */
class f {

    /* renamed from: a  reason: collision with root package name */
    public final g f431a;

    /* renamed from: b  reason: collision with root package name */
    public Callback f432b;

    /* renamed from: c  reason: collision with root package name */
    public final String f433c;
    public volatile AtomicBoolean d = new AtomicBoolean();
    public volatile IUnifiedTask e = null;
    public volatile Future f = null;

    public f(g gVar, Callback callback) {
        this.f431a = gVar;
        this.f433c = gVar.e;
        this.f432b = callback;
    }

    public void a() {
        Future future = this.f;
        if (future != null) {
            future.cancel(true);
            this.f = null;
        }
    }

    public void b() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
    }
}
