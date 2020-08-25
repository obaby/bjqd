package com.alipay.apmobilesecuritysdk.f;

import android.os.Process;

final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f580a;

    c(b bVar) {
        this.f580a = bVar;
    }

    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!this.f580a.f579c.isEmpty()) {
                Runnable runnable = (Runnable) this.f580a.f579c.get(0);
                this.f580a.f579c.remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            Thread unused2 = this.f580a.f578b = null;
            throw th;
        }
        Thread unused3 = this.f580a.f578b = null;
    }
}
