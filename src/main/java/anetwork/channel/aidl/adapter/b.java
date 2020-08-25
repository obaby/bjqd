package anetwork.channel.aidl.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.util.ALog;
import anetwork.channel.Network;
import anetwork.channel.NetworkListener;
import anetwork.channel.Request;
import anetwork.channel.Response;
import anetwork.channel.aidl.Connection;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.IRemoteNetworkGetter;
import anetwork.channel.aidl.NetworkResponse;
import anetwork.channel.aidl.ParcelableRequest;
import anetwork.channel.aidl.RemoteNetwork;
import anetwork.channel.config.NetworkConfigCenter;
import anetwork.channel.http.HttpNetworkDelegate;
import java.util.concurrent.Future;

/* compiled from: Taobao */
public class b implements Network {
    protected static final int DEGRADE = 1;
    protected static final int HTTP = 0;
    protected static String TAG = "anet.NetworkProxy";
    private Context mContext;
    private RemoteNetwork mDelegate = null;
    private int mType = 0;

    public b(Context context, int i) {
        this.mContext = context;
        this.mType = i;
    }

    public Connection getConnection(Request request, Object obj) {
        initDelegateInstance(true);
        ParcelableRequest parcelableRequest = new ParcelableRequest(request);
        if (parcelableRequest.url == null) {
            return new ConnectionDelegate(-102);
        }
        try {
            return this.mDelegate.getConnection(parcelableRequest);
        } catch (Throwable th) {
            reportRemoteError(th, "[getConnection]call getConnection method failed.");
            return new ConnectionDelegate(-103);
        }
    }

    public Response syncSend(Request request, Object obj) {
        initDelegateInstance(true);
        ParcelableRequest parcelableRequest = new ParcelableRequest(request);
        if (parcelableRequest.url == null) {
            return new NetworkResponse(-102);
        }
        try {
            return this.mDelegate.syncSend(parcelableRequest);
        } catch (Throwable th) {
            reportRemoteError(th, "[syncSend]call syncSend method failed.");
            return new NetworkResponse(-103);
        }
    }

    private void initDelegateInstance(boolean z) {
        if (this.mDelegate == null) {
            if (NetworkConfigCenter.isRemoteNetworkServiceEnable()) {
                d.a(this.mContext, z);
                this.mDelegate = tryGetRemoteNetworkInstance(this.mType);
            }
            if (this.mDelegate == null) {
                if (ALog.isPrintLog(2)) {
                    ALog.i(TAG, "[getLocalNetworkInstance]", (String) null, new Object[0]);
                }
                this.mDelegate = new HttpNetworkDelegate(this.mContext);
            }
        }
    }

    public Future<Response> asyncSend(Request request, Object obj, Handler handler, NetworkListener networkListener) {
        initDelegateInstance(Looper.myLooper() != Looper.getMainLooper());
        ParcelableRequest parcelableRequest = new ParcelableRequest(request);
        ParcelableNetworkListenerWrapper parcelableNetworkListenerWrapper = null;
        if (!(networkListener == null && handler == null)) {
            parcelableNetworkListenerWrapper = new ParcelableNetworkListenerWrapper(networkListener, handler, obj);
        }
        if (parcelableRequest.url == null) {
            if (parcelableNetworkListenerWrapper != null) {
                try {
                    parcelableNetworkListenerWrapper.onFinished(new DefaultFinishEvent(-102));
                } catch (RemoteException unused) {
                }
            }
            return new a((Response) new NetworkResponse(-102));
        }
        try {
            return new a(this.mDelegate.asyncSend(parcelableRequest, parcelableNetworkListenerWrapper));
        } catch (Throwable th) {
            if (parcelableNetworkListenerWrapper != null) {
                try {
                    parcelableNetworkListenerWrapper.onFinished(new DefaultFinishEvent(-102));
                } catch (RemoteException unused2) {
                }
            }
            reportRemoteError(th, "[asyncSend]call asyncSend exception");
            return new a((Response) new NetworkResponse(-103));
        }
    }

    private synchronized RemoteNetwork tryGetRemoteNetworkInstance(int i) {
        RemoteNetwork remoteNetwork;
        if (ALog.isPrintLog(2)) {
            ALog.i(TAG, "[tryGetRemoteNetworkInstance] type=" + i, (String) null, new Object[0]);
        }
        IRemoteNetworkGetter a2 = d.a();
        if (a2 != null) {
            try {
                remoteNetwork = a2.get(i);
            } catch (Throwable th) {
                reportRemoteError(th, "[tryGetRemoteNetworkInstance]get RemoteNetwork Delegate failed.");
            }
        }
        remoteNetwork = null;
        return remoteNetwork;
    }

    private void reportRemoteError(Throwable th, String str) {
        ALog.e(TAG, (String) null, str, th, new Object[0]);
        ExceptionStatistic exceptionStatistic = new ExceptionStatistic(-103, (String) null, "rt");
        exceptionStatistic.exceptionStack = th.toString();
        AppMonitor.getInstance().commitStat(exceptionStatistic);
    }
}
