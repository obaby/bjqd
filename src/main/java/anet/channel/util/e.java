package anet.channel.util;

import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Taobao */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static AtomicInteger f345a = new AtomicInteger();

    public static String a(String str) {
        if (f345a.get() == Integer.MAX_VALUE) {
            f345a.set(0);
        }
        if (!TextUtils.isEmpty(str)) {
            return StringUtils.concatString(str, ".AWCN", String.valueOf(f345a.incrementAndGet()));
        }
        return StringUtils.concatString("AWCN", String.valueOf(f345a.incrementAndGet()));
    }
}
