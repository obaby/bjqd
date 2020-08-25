package anetwork.channel.aidl.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import anet.channel.util.ALog;
import anetwork.channel.aidl.IRemoteNetworkGetter;
import anetwork.channel.aidl.NetworkService;
import java.util.concurrent.CountDownLatch;

/* compiled from: Taobao */
public class d {

    /* renamed from: a  reason: collision with root package name */
    static volatile IRemoteNetworkGetter f373a;

    /* renamed from: b  reason: collision with root package name */
    static volatile boolean f374b;

    /* renamed from: c  reason: collision with root package name */
    static volatile boolean f375c;
    static volatile CountDownLatch d;
    static Handler e = new Handler(Looper.getMainLooper());
    private static ServiceConnection f = new e();

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        anet.channel.util.ALog.i("anet.RemoteGetter", "[initRemoteGetterAndWait]begin to wait", (java.lang.String) null, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (d.await((long) anetwork.channel.config.NetworkConfigCenter.getServiceBindWaitTime(), java.util.concurrent.TimeUnit.SECONDS) == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        anet.channel.util.ALog.i("anet.RemoteGetter", "mServiceBindLock count down to 0", (java.lang.String) null, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
        anet.channel.util.ALog.i("anet.RemoteGetter", "mServiceBindLock wait timeout", (java.lang.String) null, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r4, boolean r5) {
        /*
            anetwork.channel.aidl.IRemoteNetworkGetter r0 = f373a
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = f374b
            if (r0 != 0) goto L_0x0062
            a(r4)
            boolean r4 = f374b
            if (r4 != 0) goto L_0x0062
            if (r5 == 0) goto L_0x0062
            r4 = 0
            r5 = 0
            java.lang.Class<anetwork.channel.aidl.adapter.d> r0 = anetwork.channel.aidl.adapter.d.class
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0059 }
            anetwork.channel.aidl.IRemoteNetworkGetter r1 = f373a     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return
        L_0x001d:
            java.util.concurrent.CountDownLatch r1 = d     // Catch:{ all -> 0x0056 }
            if (r1 != 0) goto L_0x0029
            java.util.concurrent.CountDownLatch r1 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x0056 }
            r2 = 1
            r1.<init>(r2)     // Catch:{ all -> 0x0056 }
            d = r1     // Catch:{ all -> 0x0056 }
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            java.lang.String r0 = "anet.RemoteGetter"
            java.lang.String r1 = "[initRemoteGetterAndWait]begin to wait"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ InterruptedException -> 0x0059 }
            anet.channel.util.ALog.i(r0, r1, r5, r2)     // Catch:{ InterruptedException -> 0x0059 }
            java.util.concurrent.CountDownLatch r0 = d     // Catch:{ InterruptedException -> 0x0059 }
            int r1 = anetwork.channel.config.NetworkConfigCenter.getServiceBindWaitTime()     // Catch:{ InterruptedException -> 0x0059 }
            long r1 = (long) r1     // Catch:{ InterruptedException -> 0x0059 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0059 }
            boolean r0 = r0.await(r1, r3)     // Catch:{ InterruptedException -> 0x0059 }
            if (r0 == 0) goto L_0x004c
            java.lang.String r0 = "anet.RemoteGetter"
            java.lang.String r1 = "mServiceBindLock count down to 0"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ InterruptedException -> 0x0059 }
            anet.channel.util.ALog.i(r0, r1, r5, r2)     // Catch:{ InterruptedException -> 0x0059 }
            goto L_0x0062
        L_0x004c:
            java.lang.String r0 = "anet.RemoteGetter"
            java.lang.String r1 = "mServiceBindLock wait timeout"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ InterruptedException -> 0x0059 }
            anet.channel.util.ALog.i(r0, r1, r5, r2)     // Catch:{ InterruptedException -> 0x0059 }
            goto L_0x0062
        L_0x0056:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            throw r1     // Catch:{ InterruptedException -> 0x0059 }
        L_0x0059:
            java.lang.String r0 = "anet.RemoteGetter"
            java.lang.String r1 = "mServiceBindLock wait interrupt"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            anet.channel.util.ALog.e(r0, r1, r5, r4)
        L_0x0062:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.aidl.adapter.d.a(android.content.Context, boolean):void");
    }

    public static IRemoteNetworkGetter a() {
        return f373a;
    }

    private static void a(Context context) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.RemoteGetter", "[asyncBindService] mContext:" + context + " bBindFailed:" + f374b + " bBinding:" + f375c, (String) null, new Object[0]);
        }
        if (context != null && !f374b && !f375c) {
            f375c = true;
            Intent intent = new Intent(context, NetworkService.class);
            intent.setAction(IRemoteNetworkGetter.class.getName());
            intent.addCategory("android.intent.category.DEFAULT");
            f374b = !context.bindService(intent, f, 1);
            if (f374b) {
                f375c = false;
                ALog.w("anet.RemoteGetter", "[asyncBindService]ANet_Service start not success. ANet run with local mode!", (String) null, new Object[0]);
            }
            e.postDelayed(new f(), 10000);
        }
    }
}
