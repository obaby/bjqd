package com.alipay.sdk.widget;

final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f754a;

    c(a aVar) {
        this.f754a = aVar;
    }

    public final void run() {
        if (this.f754a.f != null && this.f754a.f.isShowing()) {
            try {
                this.f754a.l.removeMessages(1);
                this.f754a.f.dismiss();
            } catch (Exception unused) {
            }
        }
    }
}
