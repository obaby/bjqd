package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

final class m extends FutureTask<u> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ q f535a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ l f536b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    m(l lVar, Callable callable, q qVar) {
        super(callable);
        this.f536b = lVar;
        this.f535a = qVar;
    }

    /* access modifiers changed from: protected */
    public final void done() {
        o a2 = this.f535a.a();
        if (a2.f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || a2.h()) {
                a2.g();
                if (!isCancelled() || !isDone()) {
                    cancel(false);
                }
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                new StringBuilder().append(e2);
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (CancellationException unused) {
            a2.g();
        } catch (Throwable th) {
            throw new RuntimeException("An error occured while executing http request", th);
        }
    }
}
