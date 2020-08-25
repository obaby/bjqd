package anetwork.channel.util;

import android.support.v7.widget.ActivityChooserView;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Taobao */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static AtomicInteger f440a = new AtomicInteger(0);

    public static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        if (str != null) {
            sb.append(str);
            sb.append('.');
        }
        if (str2 != null) {
            sb.append(str2);
            sb.append(f440a.incrementAndGet() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        }
        return sb.toString();
    }
}
