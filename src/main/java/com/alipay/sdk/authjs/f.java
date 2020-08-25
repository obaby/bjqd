package com.alipay.sdk.authjs;

import anetwork.channel.util.RequestConstant;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

final class f extends TimerTask {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f662a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ d f663b;

    f(d dVar, a aVar) {
        this.f663b = dVar;
        this.f662a = aVar;
    }

    public final void run() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("toastCallBack", RequestConstant.TRUE);
        } catch (JSONException unused) {
        }
        a aVar = new a(a.f653c);
        aVar.i = this.f662a.i;
        aVar.m = jSONObject;
        this.f663b.f658a.a(aVar);
    }
}
