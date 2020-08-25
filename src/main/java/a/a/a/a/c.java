package a.a.a.a;

import a.a.a.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: OpenIDHelper */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public a.a.a.a f24a = null;

    /* renamed from: b  reason: collision with root package name */
    public String f25b = null;

    /* renamed from: c  reason: collision with root package name */
    public String f26c = null;
    public final Object d = new Object();
    public ServiceConnection e = new b(this);

    /* compiled from: OpenIDHelper */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final c f27a = new c((b) null);
    }

    public /* synthetic */ c(b bVar) {
    }

    public boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            if (Build.VERSION.SDK_INT >= 28) {
                if (packageInfo == null || packageInfo.getLongVersionCode() < 1) {
                    return false;
                }
                return true;
            } else if (packageInfo == null || packageInfo.versionCode < 1) {
                return false;
            } else {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public final String b(Context context, String str) {
        Signature[] signatureArr;
        String str2;
        if (TextUtils.isEmpty(this.f25b)) {
            this.f25b = context.getPackageName();
        }
        if (TextUtils.isEmpty(this.f26c)) {
            try {
                signatureArr = context.getPackageManager().getPackageInfo(this.f25b, 64).signatures;
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                signatureArr = null;
            }
            if (signatureArr != null && signatureArr.length > 0) {
                byte[] byteArray = signatureArr[0].toByteArray();
                try {
                    MessageDigest instance = MessageDigest.getInstance("SHA1");
                    if (instance != null) {
                        byte[] digest = instance.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b2 : digest) {
                            sb.append(Integer.toHexString((b2 & 255) | 256).substring(1, 3));
                        }
                        str2 = sb.toString();
                        this.f26c = str2;
                    }
                } catch (NoSuchAlgorithmException e3) {
                    e3.printStackTrace();
                }
            }
            str2 = null;
            this.f26c = str2;
        }
        String a2 = ((a.C0000a.C0001a) this.f24a).a(this.f25b, this.f26c, str);
        return TextUtils.isEmpty(a2) ? "" : a2;
    }

    public synchronized String a(Context context, String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot run on MainThread");
        } else if (this.f24a == null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
            intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
            if (context.bindService(intent, this.e, 1)) {
                synchronized (this.d) {
                    try {
                        this.d.wait(3000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (this.f24a == null) {
                return "";
            }
            try {
                return b(context, str);
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return "";
            }
        } else {
            try {
                return b(context, str);
            } catch (RemoteException e4) {
                e4.printStackTrace();
                return "";
            }
        }
    }
}
