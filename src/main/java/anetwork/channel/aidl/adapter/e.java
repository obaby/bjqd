package anetwork.channel.aidl.adapter;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import anet.channel.util.ALog;
import anetwork.channel.aidl.IRemoteNetworkGetter;

/* compiled from: Taobao */
final class e implements ServiceConnection {
    e() {
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.RemoteGetter", "ANet_Service Disconnected", (String) null, new Object[0]);
        }
        d.f373a = null;
        d.f375c = false;
        if (d.d != null) {
            d.d.countDown();
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.RemoteGetter", "[onServiceConnected]ANet_Service start success. ANet run with service mode", (String) null, new Object[0]);
        }
        synchronized (d.class) {
            d.f373a = IRemoteNetworkGetter.Stub.asInterface(iBinder);
            if (d.d != null) {
                d.d.countDown();
            }
        }
        d.f374b = false;
        d.f375c = false;
    }
}
