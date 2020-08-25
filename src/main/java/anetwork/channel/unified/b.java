package anetwork.channel.unified;

import anetwork.channel.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: Taobao */
class b implements Future<Response> {

    /* renamed from: a  reason: collision with root package name */
    private g f421a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f422b;

    public /* synthetic */ Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return b();
    }

    public b(g gVar) {
        this.f421a = gVar;
    }

    public boolean cancel(boolean z) {
        if (!this.f422b) {
            this.f421a.b();
            this.f422b = true;
        }
        return true;
    }

    public boolean isCancelled() {
        return this.f422b;
    }

    public boolean isDone() {
        throw new RuntimeException("NOT SUPPORT!");
    }

    /* renamed from: a */
    public Response get() throws InterruptedException, ExecutionException {
        throw new RuntimeException("NOT SUPPORT!");
    }

    public Response b() throws InterruptedException, ExecutionException, TimeoutException {
        throw new RuntimeException("NOT SUPPORT!");
    }
}
