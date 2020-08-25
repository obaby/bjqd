package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anetwork.channel.NetworkEvent;

/* compiled from: Taobao */
public class DefaultProgressEvent implements Parcelable, NetworkEvent.ProgressEvent {
    public static final Parcelable.Creator<DefaultProgressEvent> CREATOR = new b();

    /* renamed from: a  reason: collision with root package name */
    int f353a;

    /* renamed from: b  reason: collision with root package name */
    int f354b;

    /* renamed from: c  reason: collision with root package name */
    int f355c;
    Object d;
    byte[] e;

    public int describeContents() {
        return 0;
    }

    public String getDesc() {
        return "";
    }

    public DefaultProgressEvent() {
    }

    public DefaultProgressEvent(int i, int i2, int i3, byte[] bArr) {
        this.f353a = i;
        this.f354b = i2;
        this.f355c = i3;
        this.e = bArr;
    }

    public int getSize() {
        return this.f354b;
    }

    public int getTotal() {
        return this.f355c;
    }

    public Object getContext() {
        return this.d;
    }

    public void setContext(Object obj) {
        this.d = obj;
    }

    public byte[] getBytedata() {
        return this.e;
    }

    public int getIndex() {
        return this.f353a;
    }

    public String toString() {
        return "DefaultProgressEvent [index=" + this.f353a + ", size=" + this.f354b + ", total=" + this.f355c + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f353a);
        parcel.writeInt(this.f354b);
        parcel.writeInt(this.f355c);
        parcel.writeInt(this.e != null ? this.e.length : 0);
        parcel.writeByteArray(this.e);
    }

    public static DefaultProgressEvent readFromParcel(Parcel parcel) {
        DefaultProgressEvent defaultProgressEvent = new DefaultProgressEvent();
        try {
            defaultProgressEvent.f353a = parcel.readInt();
            defaultProgressEvent.f354b = parcel.readInt();
            defaultProgressEvent.f355c = parcel.readInt();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                byte[] bArr = new byte[readInt];
                parcel.readByteArray(bArr);
                defaultProgressEvent.e = bArr;
            }
        } catch (Exception unused) {
        }
        return defaultProgressEvent;
    }
}
