package com.amap.api.fence;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.loc.dq;
import java.util.ArrayList;
import java.util.List;

public class GeoFence implements Parcelable {
    public static final int ADDGEOFENCE_SUCCESS = 0;
    public static final String BUNDLE_KEY_CUSTOMID = "customId";
    public static final String BUNDLE_KEY_FENCE = "fence";
    public static final String BUNDLE_KEY_FENCEID = "fenceid";
    public static final String BUNDLE_KEY_FENCESTATUS = "event";
    public static final String BUNDLE_KEY_LOCERRORCODE = "location_errorcode";
    public static final Parcelable.Creator<GeoFence> CREATOR = new Parcelable.Creator<GeoFence>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new GeoFence(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new GeoFence[i];
        }
    };
    public static final int ERROR_CODE_EXISTS = 17;
    public static final int ERROR_CODE_FAILURE_AUTH = 7;
    public static final int ERROR_CODE_FAILURE_CONNECTION = 4;
    public static final int ERROR_CODE_FAILURE_PARSER = 5;
    public static final int ERROR_CODE_INVALID_PARAMETER = 1;
    public static final int ERROR_CODE_UNKNOWN = 8;
    public static final int ERROR_NO_VALIDFENCE = 16;
    public static final int STATUS_IN = 1;
    public static final int STATUS_LOCFAIL = 4;
    public static final int STATUS_OUT = 2;
    public static final int STATUS_STAYED = 3;
    public static final int STATUS_UNKNOWN = 0;
    public static final int TYPE_AMAPPOI = 2;
    public static final int TYPE_DISTRICT = 3;
    public static final int TYPE_POLYGON = 1;
    public static final int TYPE_ROUND = 0;

    /* renamed from: a  reason: collision with root package name */
    private String f792a;

    /* renamed from: b  reason: collision with root package name */
    private String f793b;

    /* renamed from: c  reason: collision with root package name */
    private String f794c;
    private PendingIntent d;
    private int e;
    private PoiItem f;
    private List<DistrictItem> g;
    private List<List<DPoint>> h;
    private float i;
    private long j;
    private int k;
    private float l;
    private float m;
    private DPoint n;
    private int o;
    private long p;
    private boolean q;
    private AMapLocation r;

    public GeoFence() {
        this.d = null;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.i = 0.0f;
        this.j = -1;
        this.k = 1;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = null;
        this.o = 0;
        this.p = -1;
        this.q = true;
        this.r = null;
    }

    protected GeoFence(Parcel parcel) {
        this.d = null;
        boolean z = false;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.i = 0.0f;
        this.j = -1;
        this.k = 1;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = null;
        this.o = 0;
        this.p = -1;
        this.q = true;
        this.r = null;
        this.f792a = parcel.readString();
        this.f793b = parcel.readString();
        this.f794c = parcel.readString();
        this.d = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.e = parcel.readInt();
        this.f = (PoiItem) parcel.readParcelable(PoiItem.class.getClassLoader());
        this.g = parcel.createTypedArrayList(DistrictItem.CREATOR);
        this.i = parcel.readFloat();
        this.j = parcel.readLong();
        this.k = parcel.readInt();
        this.l = parcel.readFloat();
        this.m = parcel.readFloat();
        this.n = (DPoint) parcel.readParcelable(DPoint.class.getClassLoader());
        this.o = parcel.readInt();
        this.p = parcel.readLong();
        int readInt = parcel.readInt();
        if (readInt != 0) {
            this.h = new ArrayList();
            for (int i2 = 0; i2 < readInt; i2++) {
                this.h.add(parcel.createTypedArrayList(DPoint.CREATOR));
            }
        }
        this.q = parcel.readByte() != 0 ? true : z;
        this.r = (AMapLocation) parcel.readParcelable(AMapLocation.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeoFence)) {
            return false;
        }
        GeoFence geoFence = (GeoFence) obj;
        if (TextUtils.isEmpty(this.f793b)) {
            if (!TextUtils.isEmpty(geoFence.f793b)) {
                return false;
            }
        } else if (!this.f793b.equals(geoFence.f793b)) {
            return false;
        }
        if (this.n == null) {
            if (geoFence.n != null) {
                return false;
            }
        } else if (!this.n.equals(geoFence.n)) {
            return false;
        }
        if (this.i != geoFence.i) {
            return false;
        }
        return this.h == null ? geoFence.h == null : this.h.equals(geoFence.h);
    }

    public int getActivatesAction() {
        return this.k;
    }

    public DPoint getCenter() {
        return this.n;
    }

    public AMapLocation getCurrentLocation() {
        return this.r;
    }

    public String getCustomId() {
        return this.f793b;
    }

    public List<DistrictItem> getDistrictItemList() {
        return this.g;
    }

    public long getEnterTime() {
        return this.p;
    }

    public long getExpiration() {
        return this.j;
    }

    public String getFenceId() {
        return this.f792a;
    }

    public float getMaxDis2Center() {
        return this.m;
    }

    public float getMinDis2Center() {
        return this.l;
    }

    public PendingIntent getPendingIntent() {
        return this.d;
    }

    public String getPendingIntentAction() {
        return this.f794c;
    }

    public PoiItem getPoiItem() {
        return this.f;
    }

    public List<List<DPoint>> getPointList() {
        return this.h;
    }

    public float getRadius() {
        return this.i;
    }

    public int getStatus() {
        return this.o;
    }

    public int getType() {
        return this.e;
    }

    public int hashCode() {
        return this.f793b.hashCode() + this.h.hashCode() + this.n.hashCode() + ((int) (this.i * 100.0f));
    }

    public boolean isAble() {
        return this.q;
    }

    public void setAble(boolean z) {
        this.q = z;
    }

    public void setActivatesAction(int i2) {
        this.k = i2;
    }

    public void setCenter(DPoint dPoint) {
        this.n = dPoint;
    }

    public void setCurrentLocation(AMapLocation aMapLocation) {
        this.r = aMapLocation.clone();
    }

    public void setCustomId(String str) {
        this.f793b = str;
    }

    public void setDistrictItemList(List<DistrictItem> list) {
        this.g = list;
    }

    public void setEnterTime(long j2) {
        this.p = j2;
    }

    public void setExpiration(long j2) {
        this.j = j2 < 0 ? -1 : j2 + dq.b();
    }

    public void setFenceId(String str) {
        this.f792a = str;
    }

    public void setMaxDis2Center(float f2) {
        this.m = f2;
    }

    public void setMinDis2Center(float f2) {
        this.l = f2;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.d = pendingIntent;
    }

    public void setPendingIntentAction(String str) {
        this.f794c = str;
    }

    public void setPoiItem(PoiItem poiItem) {
        this.f = poiItem;
    }

    public void setPointList(List<List<DPoint>> list) {
        this.h = list;
    }

    public void setRadius(float f2) {
        this.i = f2;
    }

    public void setStatus(int i2) {
        this.o = i2;
    }

    public void setType(int i2) {
        this.e = i2;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f792a);
        parcel.writeString(this.f793b);
        parcel.writeString(this.f794c);
        parcel.writeParcelable(this.d, i2);
        parcel.writeInt(this.e);
        parcel.writeParcelable(this.f, i2);
        parcel.writeTypedList(this.g);
        parcel.writeFloat(this.i);
        parcel.writeLong(this.j);
        parcel.writeInt(this.k);
        parcel.writeFloat(this.l);
        parcel.writeFloat(this.m);
        parcel.writeParcelable(this.n, i2);
        parcel.writeInt(this.o);
        parcel.writeLong(this.p);
        if (this.h != null && !this.h.isEmpty()) {
            parcel.writeInt(this.h.size());
            for (List<DPoint> writeTypedList : this.h) {
                parcel.writeTypedList(writeTypedList);
            }
        }
        parcel.writeByte(this.q ? (byte) 1 : 0);
        parcel.writeParcelable(this.r, i2);
    }
}
