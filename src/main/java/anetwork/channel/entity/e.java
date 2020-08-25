package anetwork.channel.entity;

import android.os.RemoteException;
import anet.channel.bytes.ByteArray;
import anetwork.channel.aidl.DefaultProgressEvent;
import anetwork.channel.aidl.ParcelableNetworkListener;
import anetwork.channel.aidl.adapter.ParcelableInputStreamImpl;

/* compiled from: Taobao */
class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f408a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ByteArray f409b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f410c;
    final /* synthetic */ ParcelableNetworkListener d;
    final /* synthetic */ c e;

    e(c cVar, int i, ByteArray byteArray, int i2, ParcelableNetworkListener parcelableNetworkListener) {
        this.e = cVar;
        this.f408a = i;
        this.f409b = byteArray;
        this.f410c = i2;
        this.d = parcelableNetworkListener;
    }

    public void run() {
        if (!this.e.d) {
            try {
                this.d.onDataReceived(new DefaultProgressEvent(this.f408a, this.f409b.getDataLength(), this.f410c, this.f409b.getBuffer()));
            } catch (RemoteException unused) {
            }
        } else {
            try {
                if (this.e.f404c == null) {
                    ParcelableInputStreamImpl unused2 = this.e.f404c = new ParcelableInputStreamImpl();
                    this.e.f404c.init(this.e.e, this.f410c);
                    this.e.f404c.write(this.f409b);
                    this.d.onInputStreamGet(this.e.f404c);
                    return;
                }
                this.e.f404c.write(this.f409b);
            } catch (Exception unused3) {
                if (this.e.f404c != null) {
                    this.e.f404c.close();
                }
            }
        }
    }
}
