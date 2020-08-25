package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: Taobao */
final class d implements Parcelable.Creator<ParcelableHeader> {
    d() {
    }

    /* renamed from: a */
    public ParcelableHeader createFromParcel(Parcel parcel) {
        return ParcelableHeader.a(parcel);
    }

    /* renamed from: a */
    public ParcelableHeader[] newArray(int i) {
        return new ParcelableHeader[i];
    }
}
