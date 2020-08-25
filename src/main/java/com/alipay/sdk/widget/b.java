package com.alipay.sdk.widget;

import com.alipay.sdk.widget.a;

final class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f753a;

    b(a aVar) {
        this.f753a = aVar;
    }

    public final void run() {
        if (this.f753a.f == null) {
            a.C0012a unused = this.f753a.f = new a.C0012a(this.f753a.g);
            this.f753a.f.setCancelable(this.f753a.e);
        }
        try {
            if (!this.f753a.f.isShowing()) {
                this.f753a.f.show();
                this.f753a.l.sendEmptyMessageDelayed(1, 15000);
            }
        } catch (Exception unused2) {
        }
    }
}
