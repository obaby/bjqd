package com.amap.api.fence;

import android.app.PendingIntent;
import android.content.Context;
import com.amap.api.location.DPoint;
import com.loc.a;
import com.loc.au;
import com.loc.di;
import com.loc.v;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.List;

public class GeoFenceClient {
    public static final int GEOFENCE_IN = 1;
    public static final int GEOFENCE_OUT = 2;
    public static final int GEOFENCE_STAYED = 4;

    /* renamed from: a  reason: collision with root package name */
    Context f795a = null;

    /* renamed from: b  reason: collision with root package name */
    GeoFenceManagerBase f796b = null;

    public GeoFenceClient(Context context) {
        if (context != null) {
            try {
                this.f795a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                this.f796b = a(this.f795a);
            } catch (Throwable th) {
                di.a(th, "GeoFenceClient", "<init>");
            }
        } else {
            throw new IllegalArgumentException("Context参数不能为null");
        }
    }

    private static GeoFenceManagerBase a(Context context) {
        GeoFenceManagerBase geoFenceManagerBase;
        try {
            geoFenceManagerBase = (GeoFenceManagerBase) au.a(context, di.b(), v.c("EY29tLmFtYXAuYXBpLmZlbmNlLkdlb0ZlbmNlTWFuYWdlcldyYXBwZXI="), a.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable unused) {
            geoFenceManagerBase = new a(context);
        }
        return geoFenceManagerBase == null ? new a(context) : geoFenceManagerBase;
    }

    public void addGeoFence(DPoint dPoint, float f, String str) {
        try {
            this.f796b.addRoundGeoFence(dPoint, f, str);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "addGeoFence round");
        }
    }

    public void addGeoFence(String str, String str2) {
        try {
            this.f796b.addDistrictGeoFence(str, str2);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "addGeoFence district");
        }
    }

    public void addGeoFence(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        try {
            this.f796b.addNearbyGeoFence(str, str2, dPoint, f, i, str3);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "addGeoFence searche");
        }
    }

    public void addGeoFence(String str, String str2, String str3, int i, String str4) {
        try {
            this.f796b.addKeywordGeoFence(str, str2, str3, i, str4);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "addGeoFence searche");
        }
    }

    public void addGeoFence(List<DPoint> list, String str) {
        try {
            this.f796b.addPolygonGeoFence(list, str);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "addGeoFence polygon");
        }
    }

    public PendingIntent createPendingIntent(String str) {
        try {
            return this.f796b.createPendingIntent(str);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "creatPendingIntent");
            return null;
        }
    }

    public List<GeoFence> getAllGeoFence() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.f796b.getAllGeoFence();
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "getGeoFenceList");
            return arrayList;
        }
    }

    public boolean isPause() {
        try {
            return this.f796b.isPause();
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "isPause");
            return true;
        }
    }

    public void pauseGeoFence() {
        try {
            this.f796b.pauseGeoFence();
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "pauseGeoFence");
        }
    }

    public void removeGeoFence() {
        try {
            this.f796b.removeGeoFence();
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "removeGeoFence");
        }
    }

    public boolean removeGeoFence(GeoFence geoFence) {
        try {
            return this.f796b.removeGeoFence(geoFence);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "removeGeoFence1");
            return false;
        }
    }

    public void resumeGeoFence() {
        try {
            this.f796b.resumeGeoFence();
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "resumeGeoFence");
        }
    }

    public void setActivateAction(int i) {
        try {
            this.f796b.setActivateAction(i);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "setActivatesAction");
        }
    }

    public void setGeoFenceAble(String str, boolean z) {
        try {
            this.f796b.setGeoFenceAble(str, z);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "setGeoFenceAble");
        }
    }

    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        try {
            this.f796b.setGeoFenceListener(geoFenceListener);
        } catch (Throwable th) {
            di.a(th, "GeoFenceClient", "setGeoFenceListener");
        }
    }
}
