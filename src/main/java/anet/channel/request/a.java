package anet.channel.request;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: Taobao */
final class a implements Parcelable.Creator<ByteArrayEntry> {
    a() {
    }

    /* renamed from: a */
    public ByteArrayEntry createFromParcel(Parcel parcel) {
        ByteArrayEntry byteArrayEntry = new ByteArrayEntry((a) null);
        byte[] unused = byteArrayEntry.bytes = new byte[parcel.readInt()];
        parcel.readByteArray(byteArrayEntry.bytes);
        int unused2 = byteArrayEntry.offset = parcel.readInt();
        int unused3 = byteArrayEntry.count = parcel.readInt();
        return byteArrayEntry;
    }

    /* renamed from: a */
    public ByteArrayEntry[] newArray(int i) {
        return new ByteArrayEntry[i];
    }
}
