package com.alipay.sdk.authjs;

import android.widget.Toast;
import com.alipay.sdk.authjs.a;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

final class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f660a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ d f661b;

    e(d dVar, a aVar) {
        this.f661b = dVar;
        this.f660a = aVar;
    }

    public final void run() {
        d dVar = this.f661b;
        a aVar = this.f660a;
        if (aVar != null && "toast".equals(aVar.k)) {
            JSONObject jSONObject = aVar.m;
            String optString = jSONObject.optString("content");
            int i = 1;
            if (jSONObject.optInt("duration") < 2500) {
                i = 0;
            }
            Toast.makeText(dVar.f659b, optString, i).show();
            new Timer().schedule(new f(dVar, aVar), (long) i);
        }
        int i2 = a.C0011a.f654a;
        if (i2 != a.C0011a.f654a) {
            try {
                this.f661b.a(this.f660a.i, i2);
            } catch (JSONException unused) {
            }
        }
    }
}
