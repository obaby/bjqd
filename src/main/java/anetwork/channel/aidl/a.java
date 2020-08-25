package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: Taobao */
final class a implements Parcelable.Creator<DefaultFinishEvent> {
    a() {
    }

    /* renamed from: a */
    public DefaultFinishEvent createFromParcel(Parcel parcel) {
        return DefaultFinishEvent.a(parcel);
    }

    /* renamed from: a */
    public DefaultFinishEvent[] newArray(int i) {
        return new DefaultFinishEvent[i];
    }
}
