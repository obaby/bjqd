package com.amap.api.location;

import android.content.Context;
import android.os.Handler;
import com.loc.di;
import com.loc.p;

public class UmidtokenInfo {

    /* renamed from: a  reason: collision with root package name */
    static Handler f828a = new Handler();

    /* renamed from: b  reason: collision with root package name */
    static String f829b = null;

    /* renamed from: c  reason: collision with root package name */
    static boolean f830c = true;
    /* access modifiers changed from: private */
    public static AMapLocationClient d;
    private static long e = 30000;

    static class a implements AMapLocationListener {
        a() {
        }

        public final void onLocationChanged(AMapLocation aMapLocation) {
            try {
                if (UmidtokenInfo.d != null) {
                    UmidtokenInfo.f828a.removeCallbacksAndMessages((Object) null);
                    UmidtokenInfo.d.onDestroy();
                }
            } catch (Throwable th) {
                di.a(th, "UmidListener", "onLocationChanged");
            }
        }
    }

    public static String getUmidtoken() {
        return f829b;
    }

    public static void setLocAble(boolean z) {
        f830c = z;
    }

    public static synchronized void setUmidtoken(Context context, String str) {
        synchronized (UmidtokenInfo.class) {
            try {
                f829b = str;
                p.a(str);
                if (d == null && f830c) {
                    a aVar = new a();
                    d = new AMapLocationClient(context);
                    AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
                    aMapLocationClientOption.setOnceLocation(true);
                    aMapLocationClientOption.setNeedAddress(false);
                    d.setLocationOption(aMapLocationClientOption);
                    d.setLocationListener(aVar);
                    d.startLocation();
                    f828a.postDelayed(new Runnable() {
                        public final void run() {
                            try {
                                if (UmidtokenInfo.d != null) {
                                    UmidtokenInfo.d.onDestroy();
                                }
                            } catch (Throwable th) {
                                di.a(th, "UmidListener", "postDelayed");
                            }
                        }
                    }, 30000);
                }
            } catch (Throwable th) {
                di.a(th, "UmidListener", "setUmidtoken");
            }
        }
    }
}
