package anetwork.channel.aidl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import anet.channel.util.ALog;
import anetwork.channel.aidl.IRemoteNetworkGetter;
import anetwork.channel.aidl.RemoteNetwork;
import anetwork.channel.degrade.DegradableNetworkDelegate;
import anetwork.channel.http.HttpNetworkDelegate;
import com.stub.StubApp;

/* compiled from: Taobao */
public class NetworkService extends Service {

    /* renamed from: a  reason: collision with root package name */
    IRemoteNetworkGetter.Stub f360a = new IRemoteNetworkGetter.Stub() {
        public RemoteNetwork get(int i) throws RemoteException {
            return i == 1 ? NetworkService.this.f362c : NetworkService.this.d;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private Context f361b;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public RemoteNetwork.Stub f362c = null;
    /* access modifiers changed from: private */
    public RemoteNetwork.Stub d = null;

    public void onDestroy() {
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public IBinder onBind(Intent intent) {
        this.f361b = StubApp.getOrigApplicationContext(getApplicationContext());
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.NetworkService", "onBind:" + intent.getAction(), (String) null, new Object[0]);
        }
        this.f362c = new DegradableNetworkDelegate(this.f361b);
        this.d = new HttpNetworkDelegate(this.f361b);
        if (IRemoteNetworkGetter.class.getName().equals(intent.getAction())) {
            return this.f360a;
        }
        return null;
    }
}
