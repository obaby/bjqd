package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable;

public class PoiItem implements Parcelable {
    public static final Parcelable.Creator<PoiItem> CREATOR = new Parcelable.Creator<PoiItem>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PoiItem(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PoiItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f797a = "";

    /* renamed from: b  reason: collision with root package name */
    private String f798b = "";

    /* renamed from: c  reason: collision with root package name */
    private String f799c = "";
    private String d = "";
    private String e = "";
    private double f = 0.0d;
    private double g = 0.0d;
    private String h = "";
    private String i = "";
    private String j = "";
    private String k = "";

    public PoiItem() {
    }

    protected PoiItem(Parcel parcel) {
        this.f797a = parcel.readString();
        this.f798b = parcel.readString();
        this.f799c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readDouble();
        this.g = parcel.readDouble();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
    }

    public static Parcelable.Creator<PoiItem> getCreator() {
        return CREATOR;
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.e;
    }

    public String getAdname() {
        return this.k;
    }

    public String getCity() {
        return this.j;
    }

    public double getLatitude() {
        return this.f;
    }

    public double getLongitude() {
        return this.g;
    }

    public String getPoiId() {
        return this.f798b;
    }

    public String getPoiName() {
        return this.f797a;
    }

    public String getPoiType() {
        return this.f799c;
    }

    public String getProvince() {
        return this.i;
    }

    public String getTel() {
        return this.h;
    }

    public String getTypeCode() {
        return this.d;
    }

    public void setAddress(String str) {
        this.e = str;
    }

    public void setAdname(String str) {
        this.k = str;
    }

    public void setCity(String str) {
        this.j = str;
    }

    public void setLatitude(double d2) {
        this.f = d2;
    }

    public void setLongitude(double d2) {
        this.g = d2;
    }

    public void setPoiId(String str) {
        this.f798b = str;
    }

    public void setPoiName(String str) {
        this.f797a = str;
    }

    public void setPoiType(String str) {
        this.f799c = str;
    }

    public void setProvince(String str) {
        this.i = str;
    }

    public void setTel(String str) {
        this.h = str;
    }

    public void setTypeCode(String str) {
        this.d = str;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f797a);
        parcel.writeString(this.f798b);
        parcel.writeString(this.f799c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeDouble(this.f);
        parcel.writeDouble(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
    }
}
