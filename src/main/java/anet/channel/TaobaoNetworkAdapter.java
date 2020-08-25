package anet.channel;

import android.content.Context;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.e.a;
import anet.channel.flow.NetworkAnalysis;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anetwork.channel.config.NetworkConfigCenter;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Taobao */
public class TaobaoNetworkAdapter implements Serializable {
    public static AtomicBoolean isInited = new AtomicBoolean();

    public static void init(Context context) {
        if (isInited.compareAndSet(false, true)) {
            ALog.setLog(new a());
            NetworkConfigCenter.setRemoteConfig(new anet.channel.c.a());
            AppMonitor.setInstance(new anet.channel.appmonitor.a());
            NetworkAnalysis.setInstance(new anet.channel.a.a());
            ThreadPoolExecutorFactory.submitPriorityTask(new h(), ThreadPoolExecutorFactory.Priority.NORMAL);
        }
    }
}
