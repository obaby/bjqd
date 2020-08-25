package com.alipay.sdk.app.statistic;

import android.content.Context;

final class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f623a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f624b;

    b(Context context, String str) {
        this.f623a = context;
        this.f624b = str;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0023 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002b A[Catch:{ IOException -> 0x0034, Throwable -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
            com.alipay.sdk.packet.impl.c r0 = new com.alipay.sdk.packet.impl.c
            r0.<init>()
            android.content.Context r1 = r4.f623a     // Catch:{ Throwable -> 0x0023 }
            java.lang.String r2 = "alipay_cashier_statistic_record"
            r3 = 0
            java.lang.String r1 = com.alipay.sdk.util.i.b(r1, r2, r3)     // Catch:{ Throwable -> 0x0023 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0023 }
            if (r2 != 0) goto L_0x0023
            android.content.Context r2 = r4.f623a     // Catch:{ Throwable -> 0x0023 }
            com.alipay.sdk.packet.b r1 = r0.a((android.content.Context) r2, (java.lang.String) r1)     // Catch:{ Throwable -> 0x0023 }
            if (r1 == 0) goto L_0x0023
            android.content.Context r1 = r4.f623a     // Catch:{ Throwable -> 0x0023 }
            java.lang.String r2 = "alipay_cashier_statistic_record"
            com.alipay.sdk.util.i.a(r1, r2)     // Catch:{ Throwable -> 0x0023 }
        L_0x0023:
            java.lang.String r1 = r4.f624b     // Catch:{ IOException -> 0x0034, Throwable -> 0x0033 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ IOException -> 0x0034, Throwable -> 0x0033 }
            if (r1 != 0) goto L_0x0032
            android.content.Context r1 = r4.f623a     // Catch:{ IOException -> 0x0034, Throwable -> 0x0033 }
            java.lang.String r2 = r4.f624b     // Catch:{ IOException -> 0x0034, Throwable -> 0x0033 }
            r0.a((android.content.Context) r1, (java.lang.String) r2)     // Catch:{ IOException -> 0x0034, Throwable -> 0x0033 }
        L_0x0032:
            return
        L_0x0033:
            return
        L_0x0034:
            android.content.Context r0 = r4.f623a
            java.lang.String r1 = "alipay_cashier_statistic_record"
            java.lang.String r2 = r4.f624b
            com.alipay.sdk.util.i.a(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.statistic.b.run():void");
    }
}
