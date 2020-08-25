package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: Taobao */
final class b implements Parcelable.Creator<DefaultProgressEvent> {
    b() {
    }

    /* renamed from: a */
    public DefaultProgressEvent createFromParcel(Parcel parcel) {
        return DefaultProgressEvent.readFromParcel(parcel);
    }

    /* renamed from: a */
    public DefaultProgressEvent[] newArray(int i) {
        return new DefaultProgressEvent[i];
    }
}
