package anetwork.channel.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import anet.channel.bytes.ByteArray;
import anet.channel.bytes.a;
import anet.channel.request.BodyEntry;
import anetwork.channel.IBodyHandler;
import anetwork.channel.aidl.ParcelableBodyHandler;
import anetwork.channel.aidl.adapter.ParcelableBodyHandlerWrapper;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: Taobao */
public class BodyHandlerEntry implements BodyEntry {
    public static final Parcelable.Creator<BodyHandlerEntry> CREATOR = new Parcelable.Creator<BodyHandlerEntry>() {
        public BodyHandlerEntry createFromParcel(Parcel parcel) {
            BodyHandlerEntry bodyHandlerEntry = new BodyHandlerEntry();
            bodyHandlerEntry.bodyHandler = ParcelableBodyHandlerWrapper.asInterface(parcel.readStrongBinder());
            return bodyHandlerEntry;
        }

        public BodyHandlerEntry[] newArray(int i) {
            return new BodyHandlerEntry[i];
        }
    };
    ParcelableBodyHandler bodyHandler;

    public int describeContents() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public BodyHandlerEntry(IBodyHandler iBodyHandler) {
        this.bodyHandler = null;
        this.bodyHandler = new ParcelableBodyHandlerWrapper(iBodyHandler);
    }

    private BodyHandlerEntry() {
        this.bodyHandler = null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongInterface(this.bodyHandler);
    }

    public int writeTo(OutputStream outputStream) throws IOException {
        try {
            ByteArray a2 = a.C0005a.f154a.a(2048);
            int i = 0;
            while (!this.bodyHandler.isCompleted()) {
                int read = this.bodyHandler.read(a2.getBuffer());
                outputStream.write(a2.getBuffer(), 0, read);
                i += read;
            }
            a2.recycle();
            return i;
        } catch (RemoteException e) {
            throw new IOException("RemoteException", e);
        }
    }
}
