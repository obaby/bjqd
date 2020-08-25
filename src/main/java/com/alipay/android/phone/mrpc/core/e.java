package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

final class e extends DefaultRedirectHandler {

    /* renamed from: a  reason: collision with root package name */
    int f521a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ d f522b;

    e(d dVar) {
        this.f522b = dVar;
    }

    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        int statusCode;
        this.f521a++;
        boolean isRedirectRequested = e.super.isRedirectRequested(httpResponse, httpContext);
        if (isRedirectRequested || this.f521a >= 5 || ((statusCode = httpResponse.getStatusLine().getStatusCode()) != 301 && statusCode != 302)) {
            return isRedirectRequested;
        }
        return true;
    }
}
