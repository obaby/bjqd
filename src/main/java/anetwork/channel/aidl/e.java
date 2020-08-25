package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: Taobao */
final class e implements Parcelable.Creator<ParcelableRequest> {
    e() {
    }

    /* renamed from: a */
    public ParcelableRequest createFromParcel(Parcel parcel) {
        return ParcelableRequest.readFromParcel(parcel);
    }

    /* renamed from: a */
    public ParcelableRequest[] newArray(int i) {
        return new ParcelableRequest[i];
    }
}
