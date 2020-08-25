package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.loc.di;

public class AMapLocationClientOption implements Parcelable, Cloneable {
    public static final Parcelable.Creator<AMapLocationClientOption> CREATOR = new Parcelable.Creator<AMapLocationClientOption>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new AMapLocationClientOption(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AMapLocationClientOption[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    static String f805a = "";
    private static AMapLocationProtocol j = AMapLocationProtocol.HTTP;
    private static boolean t = true;

    /* renamed from: b  reason: collision with root package name */
    private long f806b;

    /* renamed from: c  reason: collision with root package name */
    private long f807c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private AMapLocationMode i;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private long r;
    private GeoLanguage s;
    private float u;
    private AMapLocationPurpose v;

    public enum AMapLocationMode {
        Battery_Saving,
        Device_Sensors,
        Hight_Accuracy
    }

    public enum AMapLocationProtocol {
        HTTP(0),
        HTTPS(1);
        

        /* renamed from: a  reason: collision with root package name */
        private int f811a;

        private AMapLocationProtocol(int i) {
            this.f811a = i;
        }

        public final int getValue() {
            return this.f811a;
        }
    }

    public enum AMapLocationPurpose {
        SignIn,
        Transport,
        Sport
    }

    public enum GeoLanguage {
        DEFAULT,
        ZH,
        EN
    }

    public AMapLocationClientOption() {
        this.f806b = 2000;
        this.f807c = (long) di.f;
        this.d = false;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = AMapLocationMode.Hight_Accuracy;
        this.k = false;
        this.l = false;
        this.m = true;
        this.n = true;
        this.o = false;
        this.p = false;
        this.q = true;
        this.r = 30000;
        this.s = GeoLanguage.DEFAULT;
        this.u = 0.0f;
        this.v = null;
    }

    protected AMapLocationClientOption(Parcel parcel) {
        this.f806b = 2000;
        this.f807c = (long) di.f;
        boolean z = false;
        this.d = false;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = AMapLocationMode.Hight_Accuracy;
        this.k = false;
        this.l = false;
        this.m = true;
        this.n = true;
        this.o = false;
        this.p = false;
        this.q = true;
        this.r = 30000;
        this.s = GeoLanguage.DEFAULT;
        this.u = 0.0f;
        AMapLocationPurpose aMapLocationPurpose = null;
        this.v = null;
        this.f806b = parcel.readLong();
        this.f807c = parcel.readLong();
        this.d = parcel.readByte() != 0;
        this.e = parcel.readByte() != 0;
        this.f = parcel.readByte() != 0;
        this.g = parcel.readByte() != 0;
        this.h = parcel.readByte() != 0;
        int readInt = parcel.readInt();
        this.i = readInt == -1 ? AMapLocationMode.Hight_Accuracy : AMapLocationMode.values()[readInt];
        this.k = parcel.readByte() != 0;
        this.l = parcel.readByte() != 0;
        this.m = parcel.readByte() != 0;
        this.n = parcel.readByte() != 0;
        this.o = parcel.readByte() != 0;
        this.p = parcel.readByte() != 0;
        this.q = parcel.readByte() != 0;
        this.r = parcel.readLong();
        int readInt2 = parcel.readInt();
        j = readInt2 == -1 ? AMapLocationProtocol.HTTP : AMapLocationProtocol.values()[readInt2];
        int readInt3 = parcel.readInt();
        this.s = readInt3 == -1 ? GeoLanguage.DEFAULT : GeoLanguage.values()[readInt3];
        t = parcel.readByte() != 0 ? true : z;
        this.u = parcel.readFloat();
        int readInt4 = parcel.readInt();
        this.v = readInt4 != -1 ? AMapLocationPurpose.values()[readInt4] : aMapLocationPurpose;
    }

    public static String getAPIKEY() {
        return f805a;
    }

    public static boolean isDownloadCoordinateConvertLibrary() {
        return t;
    }

    public static void setDownloadCoordinateConvertLibrary(boolean z) {
        t = z;
    }

    public static void setLocationProtocol(AMapLocationProtocol aMapLocationProtocol) {
        j = aMapLocationProtocol;
    }

    public AMapLocationClientOption clone() {
        try {
            super.clone();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.f806b = this.f806b;
        aMapLocationClientOption.d = this.d;
        aMapLocationClientOption.i = this.i;
        aMapLocationClientOption.e = this.e;
        aMapLocationClientOption.k = this.k;
        aMapLocationClientOption.l = this.l;
        aMapLocationClientOption.f = this.f;
        aMapLocationClientOption.g = this.g;
        aMapLocationClientOption.f807c = this.f807c;
        aMapLocationClientOption.m = this.m;
        aMapLocationClientOption.n = this.n;
        aMapLocationClientOption.o = this.o;
        aMapLocationClientOption.p = isSensorEnable();
        aMapLocationClientOption.q = isWifiScan();
        aMapLocationClientOption.r = this.r;
        aMapLocationClientOption.s = this.s;
        aMapLocationClientOption.u = this.u;
        aMapLocationClientOption.v = this.v;
        return aMapLocationClientOption;
    }

    public int describeContents() {
        return 0;
    }

    public float getDeviceModeDistanceFilter() {
        return this.u;
    }

    public GeoLanguage getGeoLanguage() {
        return this.s;
    }

    public long getHttpTimeOut() {
        return this.f807c;
    }

    public long getInterval() {
        return this.f806b;
    }

    public long getLastLocationLifeCycle() {
        return this.r;
    }

    public AMapLocationMode getLocationMode() {
        return this.i;
    }

    public AMapLocationProtocol getLocationProtocol() {
        return j;
    }

    public AMapLocationPurpose getLocationPurpose() {
        return this.v;
    }

    public boolean isGpsFirst() {
        return this.l;
    }

    public boolean isKillProcess() {
        return this.k;
    }

    public boolean isLocationCacheEnable() {
        return this.n;
    }

    public boolean isMockEnable() {
        return this.e;
    }

    public boolean isNeedAddress() {
        return this.f;
    }

    public boolean isOffset() {
        return this.m;
    }

    public boolean isOnceLocation() {
        return this.d;
    }

    public boolean isOnceLocationLatest() {
        return this.o;
    }

    public boolean isSensorEnable() {
        return this.p;
    }

    public boolean isWifiActiveScan() {
        return this.g;
    }

    public boolean isWifiScan() {
        return this.q;
    }

    public AMapLocationClientOption setDeviceModeDistanceFilter(float f2) {
        this.u = f2;
        return this;
    }

    public AMapLocationClientOption setGeoLanguage(GeoLanguage geoLanguage) {
        this.s = geoLanguage;
        return this;
    }

    public AMapLocationClientOption setGpsFirst(boolean z) {
        this.l = z;
        return this;
    }

    public AMapLocationClientOption setHttpTimeOut(long j2) {
        this.f807c = j2;
        return this;
    }

    public AMapLocationClientOption setInterval(long j2) {
        if (j2 <= 800) {
            j2 = 800;
        }
        this.f806b = j2;
        return this;
    }

    public AMapLocationClientOption setKillProcess(boolean z) {
        this.k = z;
        return this;
    }

    public AMapLocationClientOption setLastLocationLifeCycle(long j2) {
        this.r = j2;
        return this;
    }

    public AMapLocationClientOption setLocationCacheEnable(boolean z) {
        this.n = z;
        return this;
    }

    public AMapLocationClientOption setLocationMode(AMapLocationMode aMapLocationMode) {
        this.i = aMapLocationMode;
        return this;
    }

    public AMapLocationClientOption setLocationPurpose(AMapLocationPurpose aMapLocationPurpose) {
        this.v = aMapLocationPurpose;
        if (aMapLocationPurpose != null) {
            switch (aMapLocationPurpose) {
                case SignIn:
                    this.i = AMapLocationMode.Hight_Accuracy;
                    this.d = true;
                    this.o = true;
                    this.l = false;
                    break;
                case Transport:
                case Sport:
                    this.i = AMapLocationMode.Hight_Accuracy;
                    this.d = false;
                    this.o = false;
                    this.l = true;
                    break;
            }
            this.e = false;
            this.q = true;
        }
        return this;
    }

    public AMapLocationClientOption setMockEnable(boolean z) {
        this.e = z;
        return this;
    }

    public AMapLocationClientOption setNeedAddress(boolean z) {
        this.f = z;
        return this;
    }

    public AMapLocationClientOption setOffset(boolean z) {
        this.m = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocation(boolean z) {
        this.d = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocationLatest(boolean z) {
        this.o = z;
        return this;
    }

    public AMapLocationClientOption setSensorEnable(boolean z) {
        this.p = z;
        return this;
    }

    public AMapLocationClientOption setWifiActiveScan(boolean z) {
        this.g = z;
        this.h = z;
        return this;
    }

    public AMapLocationClientOption setWifiScan(boolean z) {
        this.q = z;
        this.g = this.q ? this.h : false;
        return this;
    }

    public String toString() {
        return "interval:" + String.valueOf(this.f806b) + "#" + "isOnceLocation:" + String.valueOf(this.d) + "#" + "locationMode:" + String.valueOf(this.i) + "#" + "locationProtocol:" + String.valueOf(j) + "#" + "isMockEnable:" + String.valueOf(this.e) + "#" + "isKillProcess:" + String.valueOf(this.k) + "#" + "isGpsFirst:" + String.valueOf(this.l) + "#" + "isNeedAddress:" + String.valueOf(this.f) + "#" + "isWifiActiveScan:" + String.valueOf(this.g) + "#" + "wifiScan:" + String.valueOf(this.q) + "#" + "httpTimeOut:" + String.valueOf(this.f807c) + "#" + "isLocationCacheEnable:" + String.valueOf(this.n) + "#" + "isOnceLocationLatest:" + String.valueOf(this.o) + "#" + "sensorEnable:" + String.valueOf(this.p) + "#" + "geoLanguage:" + String.valueOf(this.s) + "#" + "locationPurpose:" + String.valueOf(this.v) + "#";
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f806b);
        parcel.writeLong(this.f807c);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeByte(this.f ? (byte) 1 : 0);
        parcel.writeByte(this.g ? (byte) 1 : 0);
        parcel.writeByte(this.h ? (byte) 1 : 0);
        int i3 = -1;
        parcel.writeInt(this.i == null ? -1 : this.i.ordinal());
        parcel.writeByte(this.k ? (byte) 1 : 0);
        parcel.writeByte(this.l ? (byte) 1 : 0);
        parcel.writeByte(this.m ? (byte) 1 : 0);
        parcel.writeByte(this.n ? (byte) 1 : 0);
        parcel.writeByte(this.o ? (byte) 1 : 0);
        parcel.writeByte(this.p ? (byte) 1 : 0);
        parcel.writeByte(this.q ? (byte) 1 : 0);
        parcel.writeLong(this.r);
        parcel.writeInt(j == null ? -1 : getLocationProtocol().ordinal());
        parcel.writeInt(this.s == null ? -1 : this.s.ordinal());
        parcel.writeByte(t ? (byte) 1 : 0);
        parcel.writeFloat(this.u);
        if (this.v != null) {
            i3 = this.v.ordinal();
        }
        parcel.writeInt(i3);
    }
}
