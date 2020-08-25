package anet.channel.util;

import anet.channel.request.Request;
import anet.channel.thread.ThreadPoolExecutorFactory;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Integer> f344a = new HashMap();

    static {
        f344a.put("tpatch", 3);
        f344a.put("so", 3);
        f344a.put("json", 3);
        f344a.put("html", 4);
        f344a.put("htm", 4);
        f344a.put("css", 5);
        f344a.put("js", 5);
        f344a.put("webp", 6);
        f344a.put("png", 6);
        f344a.put("jpg", 6);
        f344a.put("do", 6);
        f344a.put("zip", Integer.valueOf(ThreadPoolExecutorFactory.Priority.LOW));
        f344a.put("bin", Integer.valueOf(ThreadPoolExecutorFactory.Priority.LOW));
        f344a.put("apk", Integer.valueOf(ThreadPoolExecutorFactory.Priority.LOW));
    }

    public static int a(Request request) {
        Integer num;
        if (request == null) {
            throw new NullPointerException("url is null!");
        } else if (request.getHeaders().containsKey(HttpConstant.X_PV)) {
            return 1;
        } else {
            String trySolveFileExtFromUrlPath = HttpHelper.trySolveFileExtFromUrlPath(request.getHttpUrl().path());
            if (trySolveFileExtFromUrlPath == null || (num = f344a.get(trySolveFileExtFromUrlPath)) == null) {
                return 6;
            }
            return num.intValue();
        }
    }
}
