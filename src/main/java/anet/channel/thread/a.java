package anet.channel.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: Taobao */
class a extends ThreadPoolExecutor {
    public a(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory);
    }

    /* access modifiers changed from: protected */
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new C0006a(runnable, t);
    }

    /* access modifiers changed from: protected */
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new C0006a(callable);
    }

    /* renamed from: anet.channel.thread.a$a  reason: collision with other inner class name */
    /* compiled from: Taobao */
    class C0006a<V> extends FutureTask<V> implements Comparable<C0006a<V>> {

        /* renamed from: b  reason: collision with root package name */
        private Object f336b;

        public C0006a(Callable<V> callable) {
            super(callable);
            this.f336b = callable;
        }

        public C0006a(Runnable runnable, V v) {
            super(runnable, v);
            this.f336b = runnable;
        }

        /* renamed from: a */
        public int compareTo(C0006a<V> aVar) {
            if (this == aVar) {
                return 0;
            }
            if (aVar == null) {
                return -1;
            }
            if (this.f336b == null || aVar.f336b == null || !this.f336b.getClass().equals(aVar.f336b.getClass()) || !(this.f336b instanceof Comparable)) {
                return 0;
            }
            return ((Comparable) this.f336b).compareTo(aVar.f336b);
        }
    }
}
