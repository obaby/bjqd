package anetwork.channel.interceptor;

import anet.channel.util.ALog;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: Taobao */
public class InterceptorManager {

    /* renamed from: a  reason: collision with root package name */
    private static final CopyOnWriteArrayList<Interceptor> f417a = new CopyOnWriteArrayList<>();

    private InterceptorManager() {
    }

    public static void addInterceptor(Interceptor interceptor) {
        if (!f417a.contains(interceptor)) {
            f417a.add(interceptor);
            ALog.i("anet.InterceptorManager", "[addInterceptor]", (String) null, "interceptors", f417a.toString());
        }
    }

    public static void removeInterceptor(Interceptor interceptor) {
        f417a.remove(interceptor);
        ALog.i("anet.InterceptorManager", "[remoteInterceptor]", (String) null, "interceptors", f417a.toString());
    }

    public static Interceptor getInterceptor(int i) {
        return f417a.get(i);
    }

    public static boolean contains(Interceptor interceptor) {
        return f417a.contains(interceptor);
    }

    public static int getSize() {
        return f417a.size();
    }
}
