package com.amap.api.location;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import com.loc.au;
import com.loc.d;
import com.loc.di;
import com.loc.do;
import com.loc.u;
import com.loc.v;
import com.stub.StubApp;

public class AMapLocationClient {

    /* renamed from: a  reason: collision with root package name */
    Context f803a;

    /* renamed from: b  reason: collision with root package name */
    LocationManagerBase f804b;

    public AMapLocationClient(Context context) {
        if (context != null) {
            try {
                this.f803a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                this.f804b = a(this.f803a, (Intent) null);
            } catch (Throwable th) {
                di.a(th, "AMapLocationClient", "AMapLocationClient 1");
            }
        } else {
            throw new IllegalArgumentException("Context参数不能为null");
        }
    }

    public AMapLocationClient(Context context, Intent intent) {
        if (context != null) {
            try {
                this.f803a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                this.f804b = a(this.f803a, intent);
            } catch (Throwable th) {
                di.a(th, "AMapLocationClient", "AMapLocationClient 2");
            }
        } else {
            throw new IllegalArgumentException("Context参数不能为null");
        }
    }

    private static LocationManagerBase a(Context context, Intent intent) {
        LocationManagerBase locationManagerBase;
        try {
            u b2 = di.b();
            do.a(context, b2);
            boolean c2 = do.c(context);
            do.a(context);
            if (c2) {
                locationManagerBase = (LocationManagerBase) au.a(context, b2, v.c("IY29tLmFtYXAuYXBpLmxvY2F0aW9uLkxvY2F0aW9uTWFuYWdlcldyYXBwZXI="), d.class, new Class[]{Context.class, Intent.class}, new Object[]{context, intent});
            } else {
                locationManagerBase = new d(context, intent);
            }
        } catch (Throwable unused) {
            locationManagerBase = new d(context, intent);
        }
        return locationManagerBase == null ? new d(context, intent) : locationManagerBase;
    }

    public static void setApiKey(String str) {
        try {
            AMapLocationClientOption.f805a = str;
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "setApiKey");
        }
    }

    public void disableBackgroundLocation(boolean z) {
        try {
            if (this.f804b != null) {
                this.f804b.disableBackgroundLocation(z);
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "disableBackgroundLocation");
        }
    }

    public void enableBackgroundLocation(int i, Notification notification) {
        try {
            if (this.f804b != null) {
                this.f804b.enableBackgroundLocation(i, notification);
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "enableBackgroundLocation");
        }
    }

    public AMapLocation getLastKnownLocation() {
        try {
            if (this.f804b != null) {
                return this.f804b.getLastKnownLocation();
            }
            return null;
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "getLastKnownLocation");
            return null;
        }
    }

    public String getVersion() {
        return "4.1.0";
    }

    public boolean isStarted() {
        try {
            if (this.f804b != null) {
                return this.f804b.isStarted();
            }
            return false;
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "isStarted");
            return false;
        }
    }

    public void onDestroy() {
        try {
            if (this.f804b != null) {
                this.f804b.onDestroy();
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "onDestroy");
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener != null) {
            try {
                if (this.f804b != null) {
                    this.f804b.setLocationListener(aMapLocationListener);
                }
            } catch (Throwable th) {
                di.a(th, "AMapLocationClient", "setLocationListener");
            }
        } else {
            throw new IllegalArgumentException("listener参数不能为null");
        }
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        if (aMapLocationClientOption != null) {
            try {
                if (this.f804b != null) {
                    this.f804b.setLocationOption(aMapLocationClientOption);
                }
            } catch (Throwable th) {
                di.a(th, "AMapLocationClient", "setLocationOption");
            }
        } else {
            throw new IllegalArgumentException("LocationManagerOption参数不能为null");
        }
    }

    public void startAssistantLocation() {
        try {
            if (this.f804b != null) {
                this.f804b.startAssistantLocation();
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "startAssistantLocation");
        }
    }

    public void startAssistantLocation(WebView webView) {
        try {
            if (this.f804b != null) {
                this.f804b.startAssistantLocation(webView);
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "startAssistantLocation1");
        }
    }

    public void startLocation() {
        try {
            if (this.f804b != null) {
                this.f804b.startLocation();
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "startLocation");
        }
    }

    public void stopAssistantLocation() {
        try {
            if (this.f804b != null) {
                this.f804b.stopAssistantLocation();
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "stopAssistantLocation");
        }
    }

    public void stopLocation() {
        try {
            if (this.f804b != null) {
                this.f804b.stopLocation();
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "stopLocation");
        }
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            if (this.f804b != null) {
                this.f804b.unRegisterLocationListener(aMapLocationListener);
            }
        } catch (Throwable th) {
            di.a(th, "AMapLocationClient", "unRegisterLocationListener");
        }
    }
}
