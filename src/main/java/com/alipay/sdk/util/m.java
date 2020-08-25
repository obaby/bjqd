package com.alipay.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

final class m implements DownloadListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f747a;

    m(Context context) {
        this.f747a = context;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            this.f747a.startActivity(intent);
        } catch (Throwable unused) {
        }
    }
}
