package anetwork.channel.unified;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import anet.channel.bytes.ByteArray;
import anet.channel.bytes.a;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.StringUtils;
import anetwork.channel.aidl.Connection;
import anetwork.channel.aidl.NetworkResponse;
import anetwork.channel.aidl.ParcelableFuture;
import anetwork.channel.aidl.ParcelableInputStream;
import anetwork.channel.aidl.ParcelableNetworkListener;
import anetwork.channel.aidl.ParcelableRequest;
import anetwork.channel.aidl.RemoteNetwork;
import anetwork.channel.aidl.adapter.ConnectionDelegate;
import anetwork.channel.aidl.adapter.ParcelableFutureResponse;
import anetwork.channel.aidl.adapter.ParcelableNetworkListenerWrapper;
import anetwork.channel.entity.c;
import anetwork.channel.entity.g;
import anetwork.channel.http.NetworkSdkSetting;
import java.io.ByteArrayOutputStream;

/* compiled from: Taobao */
public abstract class UnifiedNetworkDelegate extends RemoteNetwork.Stub {
    public static final int DEGRADABLE = 1;
    public static final int HTTP = 0;
    private static final String TAG = "anet.UnifiedNetworkDelegate";
    public int type = 1;

    public UnifiedNetworkDelegate(Context context) {
        NetworkSdkSetting.init(context);
    }

    public NetworkResponse syncSend(ParcelableRequest parcelableRequest) throws RemoteException {
        return convertToSync(parcelableRequest);
    }

    public ParcelableFuture asyncSend(ParcelableRequest parcelableRequest, ParcelableNetworkListener parcelableNetworkListener) throws RemoteException {
        try {
            return asyncSend(new g(parcelableRequest, this.type, false), parcelableNetworkListener);
        } catch (Exception e) {
            ALog.e(TAG, "asyncSend failed", parcelableRequest.seqNo, e, new Object[0]);
            throw new RemoteException(e.getMessage());
        }
    }

    private ParcelableFuture asyncSend(g gVar, ParcelableNetworkListener parcelableNetworkListener) throws RemoteException {
        return new ParcelableFutureResponse(new g(gVar, new c(parcelableNetworkListener, gVar)).a());
    }

    public Connection getConnection(ParcelableRequest parcelableRequest) throws RemoteException {
        try {
            g gVar = new g(parcelableRequest, this.type, true);
            ConnectionDelegate connectionDelegate = new ConnectionDelegate(gVar);
            connectionDelegate.setFuture(asyncSend(gVar, (ParcelableNetworkListener) new ParcelableNetworkListenerWrapper(connectionDelegate, (Handler) null, (Object) null)));
            return connectionDelegate;
        } catch (Exception e) {
            ALog.e(TAG, "asyncSend failed", parcelableRequest.seqNo, e, new Object[0]);
            throw new RemoteException(e.getMessage());
        }
    }

    private NetworkResponse convertToSync(ParcelableRequest parcelableRequest) {
        NetworkResponse networkResponse = new NetworkResponse();
        try {
            ConnectionDelegate connectionDelegate = (ConnectionDelegate) getConnection(parcelableRequest);
            ParcelableInputStream inputStream = connectionDelegate.getInputStream();
            if (inputStream != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(inputStream.length() > 0 ? inputStream.length() : 1024);
                ByteArray a2 = a.C0005a.f154a.a(2048);
                while (true) {
                    int read = inputStream.read(a2.getBuffer());
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(a2.getBuffer(), 0, read);
                }
                networkResponse.setBytedata(byteArrayOutputStream.toByteArray());
            }
            int statusCode = connectionDelegate.getStatusCode();
            if (statusCode < 0) {
                networkResponse.setBytedata((byte[]) null);
            } else {
                networkResponse.setConnHeadFields(connectionDelegate.getConnHeadFields());
            }
            networkResponse.setStatusCode(statusCode);
            networkResponse.setStatisticData(connectionDelegate.getStatisticData());
            return networkResponse;
        } catch (RemoteException e) {
            networkResponse.setStatusCode(-103);
            String message = e.getMessage();
            if (!TextUtils.isEmpty(message)) {
                networkResponse.setDesc(StringUtils.concatString(networkResponse.getDesc(), "|", message));
            }
            return networkResponse;
        } catch (Exception unused) {
            networkResponse.setStatusCode(ErrorConstant.ERROR_REQUEST_FAIL);
            return networkResponse;
        }
    }
}
