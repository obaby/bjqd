package anetwork.channel.aidl.adapter;

import android.os.RemoteException;
import anetwork.channel.IBodyHandler;
import anetwork.channel.aidl.ParcelableBodyHandler;

/* compiled from: Taobao */
public class ParcelableBodyHandlerWrapper extends ParcelableBodyHandler.Stub {
    private static final String TAG = "anet.ParcelableBodyHandlerWrapper";
    private IBodyHandler handler;

    public ParcelableBodyHandlerWrapper(IBodyHandler iBodyHandler) {
        this.handler = iBodyHandler;
    }

    public int read(byte[] bArr) throws RemoteException {
        if (this.handler != null) {
            return this.handler.read(bArr);
        }
        return 0;
    }

    public boolean isCompleted() throws RemoteException {
        if (this.handler != null) {
            return this.handler.isCompleted();
        }
        return true;
    }

    public String toString() {
        return super.toString() + " handle:" + this.handler;
    }
}
