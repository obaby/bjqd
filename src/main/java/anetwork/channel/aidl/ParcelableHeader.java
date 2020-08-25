package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.util.ALog;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
public class ParcelableHeader implements Parcelable {
    public static Parcelable.Creator<ParcelableHeader> CREATOR = new d();
    public Map<String, List<String>> header;
    public int responseCode;

    public int describeContents() {
        return 0;
    }

    public ParcelableHeader(int i, Map<String, List<String>> map) {
        this.header = map;
        this.responseCode = i;
    }

    ParcelableHeader() {
    }

    public Map<String, List<String>> getHeader() {
        return this.header;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.header != null) {
            parcel.writeInt(1);
            parcel.writeMap(this.header);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.responseCode);
    }

    static ParcelableHeader a(Parcel parcel) {
        ParcelableHeader parcelableHeader = new ParcelableHeader();
        try {
            if (parcel.readInt() == 1) {
                parcelableHeader.header = parcel.readHashMap(ParcelableHeader.class.getClassLoader());
            }
            parcelableHeader.responseCode = parcel.readInt();
        } catch (Throwable th) {
            ALog.e("anet.ParcelableHeader", "[readFromParcel]", (String) null, th, new Object[0]);
        }
        return parcelableHeader;
    }

    public String toString() {
        return "ParcelableResponseHeader [responseCode=" + this.responseCode + ", header=" + this.header + "]";
    }
}
