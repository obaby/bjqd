package a.a.a.a;

import a.a.a.a;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: OpenIDHelper */
class b implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ c f23a;

    public b(c cVar) {
        this.f23a = cVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f23a.f24a = a.C0000a.a(iBinder);
        synchronized (this.f23a.d) {
            this.f23a.d.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f23a.f24a = null;
    }
}
