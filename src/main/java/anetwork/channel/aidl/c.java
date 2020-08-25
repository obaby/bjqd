package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: Taobao */
final class c implements Parcelable.Creator<NetworkResponse> {
    c() {
    }

    /* renamed from: a */
    public NetworkResponse createFromParcel(Parcel parcel) {
        return NetworkResponse.readFromParcel(parcel);
    }

    /* renamed from: a */
    public NetworkResponse[] newArray(int i) {
        return new NetworkResponse[i];
    }
}
