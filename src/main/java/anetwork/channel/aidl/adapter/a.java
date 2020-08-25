package anetwork.channel.aidl.adapter;

import android.os.RemoteException;
import anet.channel.util.ALog;
import anetwork.channel.Response;
import anetwork.channel.aidl.ParcelableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: Taobao */
public class a implements Future<Response> {

    /* renamed from: a  reason: collision with root package name */
    private ParcelableFuture f368a;

    /* renamed from: b  reason: collision with root package name */
    private Response f369b;

    public /* synthetic */ Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(j);
    }

    public a(ParcelableFuture parcelableFuture) {
        this.f368a = parcelableFuture;
    }

    public a(Response response) {
        this.f369b = response;
    }

    public boolean cancel(boolean z) {
        if (this.f368a == null) {
            return false;
        }
        try {
            return this.f368a.cancel(z);
        } catch (RemoteException e) {
            ALog.w("anet.FutureResponse", "[cancel]", (String) null, e, new Object[0]);
            return false;
        }
    }

    public boolean isCancelled() {
        try {
            return this.f368a.isCancelled();
        } catch (RemoteException e) {
            ALog.w("anet.FutureResponse", "[isCancelled]", (String) null, e, new Object[0]);
            return false;
        }
    }

    public boolean isDone() {
        try {
            return this.f368a.isDone();
        } catch (RemoteException e) {
            ALog.w("anet.FutureResponse", "[isDone]", (String) null, e, new Object[0]);
            return true;
        }
    }

    /* renamed from: a */
    public Response get() throws InterruptedException, ExecutionException {
        if (this.f369b != null) {
            return this.f369b;
        }
        if (this.f368a != null) {
            try {
                return this.f368a.get(20000);
            } catch (RemoteException e) {
                ALog.w("anet.FutureResponse", "[get]", (String) null, e, new Object[0]);
            }
        }
        return null;
    }

    public Response a(long j) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.f369b != null) {
            return this.f369b;
        }
        if (this.f368a != null) {
            try {
                return this.f368a.get(j);
            } catch (RemoteException e) {
                ALog.w("anet.FutureResponse", "[get(long timeout, TimeUnit unit)]", (String) null, e, new Object[0]);
            }
        }
        return null;
    }
}
