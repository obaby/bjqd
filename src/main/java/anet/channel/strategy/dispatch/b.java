package anet.channel.strategy.dispatch;

import android.support.graphics.drawable.PathInterpolatorCompat;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/* compiled from: Taobao */
class b {
    public static final String TAG = "awcn.AmdcThreadPoolExecutor";

    /* renamed from: b  reason: collision with root package name */
    private static Random f288b = new Random();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Map<String, Object> f289a;

    b() {
    }

    public void a(Map<String, Object> map) {
        try {
            map.put("Env", GlobalAppRuntimeInfo.getEnv());
            synchronized (this) {
                if (this.f289a == null) {
                    this.f289a = map;
                    int nextInt = f288b.nextInt(PathInterpolatorCompat.MAX_NUM_POINTS) + 2000;
                    ALog.i(TAG, "merge amdc request", (String) null, "delay", Integer.valueOf(nextInt));
                    anet.channel.strategy.utils.a.a(new a(), (long) nextInt);
                } else {
                    Set set = (Set) this.f289a.get(DispatchConstants.HOSTS);
                    Set set2 = (Set) map.get(DispatchConstants.HOSTS);
                    if (map.get("Env") != this.f289a.get("Env")) {
                        this.f289a = map;
                    } else if (set.size() + set2.size() <= 40) {
                        set2.addAll(set);
                        this.f289a = map;
                    } else {
                        anet.channel.strategy.utils.a.a(new a(map));
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    /* compiled from: Taobao */
    private class a implements Runnable {

        /* renamed from: b  reason: collision with root package name */
        private Map<String, Object> f291b;

        a(Map<String, Object> map) {
            this.f291b = map;
        }

        a() {
        }

        public void run() {
            Map<String, Object> a2;
            try {
                Map<String, Object> map = this.f291b;
                if (map == null) {
                    synchronized (b.class) {
                        a2 = b.this.f289a;
                        Map unused = b.this.f289a = null;
                    }
                    map = a2;
                }
                if (NetworkStatusHelper.isConnected()) {
                    if (GlobalAppRuntimeInfo.getEnv() != map.get("Env")) {
                        ALog.w(b.TAG, "task's env changed", (String) null, new Object[0]);
                    } else {
                        c.a(e.a(map));
                    }
                }
            } catch (Exception e) {
                ALog.e(b.TAG, "exec amdc task failed.", (String) null, e, new Object[0]);
            }
        }
    }
}
