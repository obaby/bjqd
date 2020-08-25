package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.location.DPoint;
import java.util.List;

public class DistrictItem implements Parcelable {
    public static final Parcelable.Creator<DistrictItem> CREATOR = new Parcelable.Creator<DistrictItem>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new DistrictItem(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new DistrictItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f789a = "";

    /* renamed from: b  reason: collision with root package name */
    private String f790b = null;

    /* renamed from: c  reason: collision with root package name */
    private String f791c = null;
    private List<DPoint> d = null;

    public DistrictItem() {
    }

    protected DistrictItem(Parcel parcel) {
        this.f789a = parcel.readString();
        this.f790b = parcel.readString();
        this.f791c = parcel.readString();
        this.d = parcel.createTypedArrayList(DPoint.CREATOR);
    }

    public static Parcelable.Creator<DistrictItem> getCreator() {
        return CREATOR;
    }

    public int describeContents() {
        return 0;
    }

    public String getAdcode() {
        return this.f791c;
    }

    public String getCitycode() {
        return this.f790b;
    }

    public String getDistrictName() {
        return this.f789a;
    }

    public List<DPoint> getPolyline() {
        return this.d;
    }

    public void setAdcode(String str) {
        this.f791c = str;
    }

    public void setCitycode(String str) {
        this.f790b = str;
    }

    public void setDistrictName(String str) {
        this.f789a = str;
    }

    public void setPolyline(List<DPoint> list) {
        this.d = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f789a);
        parcel.writeString(this.f790b);
        parcel.writeString(this.f791c);
        parcel.writeTypedList(this.d);
    }
}
