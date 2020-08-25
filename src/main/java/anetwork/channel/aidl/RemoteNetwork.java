package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import anetwork.channel.aidl.Connection;
import anetwork.channel.aidl.ParcelableFuture;

/* compiled from: Taobao */
public interface RemoteNetwork extends IInterface {
    ParcelableFuture asyncSend(ParcelableRequest parcelableRequest, ParcelableNetworkListener parcelableNetworkListener) throws RemoteException;

    Connection getConnection(ParcelableRequest parcelableRequest) throws RemoteException;

    NetworkResponse syncSend(ParcelableRequest parcelableRequest) throws RemoteException;

    /* compiled from: Taobao */
    public static abstract class Stub extends Binder implements RemoteNetwork {
        private static final String DESCRIPTOR = "anetwork.channel.aidl.RemoteNetwork";
        static final int TRANSACTION_asyncSend = 2;
        static final int TRANSACTION_getConnection = 3;
        static final int TRANSACTION_syncSend = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static RemoteNetwork asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof RemoteNetwork)) {
                return new Proxy(iBinder);
            }
            return (RemoteNetwork) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: anetwork.channel.aidl.ParcelableRequest} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v5, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v7, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v9 */
        /* JADX WARNING: type inference failed for: r0v10 */
        /* JADX WARNING: type inference failed for: r0v11 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
            /*
                r2 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r3 == r0) goto L_0x008c
                r0 = 0
                switch(r3) {
                    case 1: goto L_0x0063;
                    case 2: goto L_0x0035;
                    case 3: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r3 = super.onTransact(r3, r4, r5, r6)
                return r3
            L_0x000f:
                java.lang.String r3 = "anetwork.channel.aidl.RemoteNetwork"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0023
                android.os.Parcelable$Creator<anetwork.channel.aidl.ParcelableRequest> r3 = anetwork.channel.aidl.ParcelableRequest.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                anetwork.channel.aidl.ParcelableRequest r3 = (anetwork.channel.aidl.ParcelableRequest) r3
                goto L_0x0024
            L_0x0023:
                r3 = r0
            L_0x0024:
                anetwork.channel.aidl.Connection r3 = r2.getConnection(r3)
                r5.writeNoException()
                if (r3 == 0) goto L_0x0031
                android.os.IBinder r0 = r3.asBinder()
            L_0x0031:
                r5.writeStrongBinder(r0)
                return r1
            L_0x0035:
                java.lang.String r3 = "anetwork.channel.aidl.RemoteNetwork"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0049
                android.os.Parcelable$Creator<anetwork.channel.aidl.ParcelableRequest> r3 = anetwork.channel.aidl.ParcelableRequest.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                anetwork.channel.aidl.ParcelableRequest r3 = (anetwork.channel.aidl.ParcelableRequest) r3
                goto L_0x004a
            L_0x0049:
                r3 = r0
            L_0x004a:
                android.os.IBinder r4 = r4.readStrongBinder()
                anetwork.channel.aidl.ParcelableNetworkListener r4 = anetwork.channel.aidl.ParcelableNetworkListener.Stub.asInterface(r4)
                anetwork.channel.aidl.ParcelableFuture r3 = r2.asyncSend(r3, r4)
                r5.writeNoException()
                if (r3 == 0) goto L_0x005f
                android.os.IBinder r0 = r3.asBinder()
            L_0x005f:
                r5.writeStrongBinder(r0)
                return r1
            L_0x0063:
                java.lang.String r3 = "anetwork.channel.aidl.RemoteNetwork"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0077
                android.os.Parcelable$Creator<anetwork.channel.aidl.ParcelableRequest> r3 = anetwork.channel.aidl.ParcelableRequest.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                anetwork.channel.aidl.ParcelableRequest r0 = (anetwork.channel.aidl.ParcelableRequest) r0
            L_0x0077:
                anetwork.channel.aidl.NetworkResponse r3 = r2.syncSend(r0)
                r5.writeNoException()
                if (r3 == 0) goto L_0x0087
                r5.writeInt(r1)
                r3.writeToParcel(r5, r1)
                goto L_0x008b
            L_0x0087:
                r3 = 0
                r5.writeInt(r3)
            L_0x008b:
                return r1
            L_0x008c:
                java.lang.String r3 = "anetwork.channel.aidl.RemoteNetwork"
                r5.writeString(r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.aidl.RemoteNetwork.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        /* compiled from: Taobao */
        private static class Proxy implements RemoteNetwork {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f367a;

            Proxy(IBinder iBinder) {
                this.f367a = iBinder;
            }

            public IBinder asBinder() {
                return this.f367a;
            }

            public NetworkResponse syncSend(ParcelableRequest parcelableRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelableRequest != null) {
                        obtain.writeInt(1);
                        parcelableRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f367a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? NetworkResponse.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelableFuture asyncSend(ParcelableRequest parcelableRequest, ParcelableNetworkListener parcelableNetworkListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelableRequest != null) {
                        obtain.writeInt(1);
                        parcelableRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(parcelableNetworkListener != null ? parcelableNetworkListener.asBinder() : null);
                    this.f367a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return ParcelableFuture.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Connection getConnection(ParcelableRequest parcelableRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelableRequest != null) {
                        obtain.writeInt(1);
                        parcelableRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f367a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return Connection.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
