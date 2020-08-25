package cn.gov.pbc.tsm.client.mobile.android.bank.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

public interface a extends IInterface {

    /* renamed from: cn.gov.pbc.tsm.client.mobile.android.bank.service.a$a  reason: collision with other inner class name */
    public static abstract class C0008a extends Binder implements a {

        /* renamed from: cn.gov.pbc.tsm.client.mobile.android.bank.service.a$a$a  reason: collision with other inner class name */
        private static class C0009a implements a {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f452a;

            C0009a(IBinder iBinder) {
                this.f452a = iBinder;
            }

            public final String a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    this.f452a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Map a(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f452a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    boolean z = false;
                    this.f452a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final byte[] a(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    this.f452a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final byte[] a(byte[] bArr, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    this.f452a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f452a;
            }

            public final boolean b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    boolean z = false;
                    this.f452a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean b(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    boolean z = false;
                    this.f452a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final byte[] b(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    this.f452a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.f452a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String d() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.f452a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0009a(iBinder) : (a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        byte[] a2 = a(parcel.createByteArray());
                        parcel2.writeNoException();
                        parcel2.writeByteArray(a2);
                        return true;
                    case 2:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        byte[] b2 = b(parcel.createByteArray());
                        parcel2.writeNoException();
                        parcel2.writeByteArray(b2);
                        return true;
                    case 3:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        String a3 = a(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(a3);
                        return true;
                    case 4:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        boolean a4 = a();
                        parcel2.writeNoException();
                        parcel2.writeInt(a4 ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        boolean b3 = b();
                        parcel2.writeNoException();
                        parcel2.writeInt(b3 ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        Map a5 = a(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeMap(a5);
                        return true;
                    case 7:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        byte[] a6 = a(parcel.createByteArray(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeByteArray(a6);
                        return true;
                    case 8:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        boolean b4 = b(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(b4 ? 1 : 0);
                        return true;
                    case 9:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        String c2 = c();
                        parcel2.writeNoException();
                        parcel2.writeString(c2);
                        return true;
                    case 10:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        String d = d();
                        parcel2.writeNoException();
                        parcel2.writeString(d);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                return true;
            }
        }
    }

    String a(String str) throws RemoteException;

    Map a(String str, String str2) throws RemoteException;

    boolean a() throws RemoteException;

    byte[] a(byte[] bArr) throws RemoteException;

    byte[] a(byte[] bArr, String str) throws RemoteException;

    boolean b() throws RemoteException;

    boolean b(String str) throws RemoteException;

    byte[] b(byte[] bArr) throws RemoteException;

    String c() throws RemoteException;

    String d() throws RemoteException;
}
