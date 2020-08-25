package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import anetwork.channel.aidl.RemoteNetwork;

/* compiled from: Taobao */
public interface IRemoteNetworkGetter extends IInterface {
    RemoteNetwork get(int i) throws RemoteException;

    /* compiled from: Taobao */
    public static abstract class Stub extends Binder implements IRemoteNetworkGetter {
        private static final String DESCRIPTOR = "anetwork.channel.aidl.IRemoteNetworkGetter";
        static final int TRANSACTION_get = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteNetworkGetter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IRemoteNetworkGetter)) {
                return new Proxy(iBinder);
            }
            return (IRemoteNetworkGetter) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                RemoteNetwork remoteNetwork = get(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(remoteNetwork != null ? remoteNetwork.asBinder() : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* compiled from: Taobao */
        private static class Proxy implements IRemoteNetworkGetter {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f356a;

            Proxy(IBinder iBinder) {
                this.f356a = iBinder;
            }

            public IBinder asBinder() {
                return this.f356a;
            }

            public RemoteNetwork get(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.f356a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return RemoteNetwork.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
