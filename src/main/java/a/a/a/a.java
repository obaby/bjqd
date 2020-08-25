package a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: IOpenID */
public interface a extends IInterface {

    /* renamed from: a.a.a.a$a  reason: collision with other inner class name */
    /* compiled from: IOpenID */
    public static abstract class C0000a extends Binder implements a {

        /* renamed from: a.a.a.a$a$a  reason: collision with other inner class name */
        /* compiled from: IOpenID */
        private static class C0001a implements a {

            /* renamed from: a  reason: collision with root package name */
            public IBinder f22a;

            public C0001a(IBinder iBinder) {
                this.f22a = iBinder;
            }

            public String a(String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.f22a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f22a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof a)) {
                return new C0001a(iBinder);
            }
            return (a) queryLocalInterface;
        }
    }
}
