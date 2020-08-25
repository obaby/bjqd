package anetwork.channel.cache;

import anet.channel.util.HttpHelper;
import anetwork.channel.cache.Cache;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* compiled from: Taobao */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final TimeZone f382a = TimeZone.getTimeZone("GMT");

    /* renamed from: b  reason: collision with root package name */
    private static final DateFormat f383b = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);

    static {
        f383b.setTimeZone(f382a);
    }

    public static String a(long j) {
        return f383b.format(new Date(j));
    }

    private static long a(String str) {
        if (str.length() == 0) {
            return 0;
        }
        try {
            ParsePosition parsePosition = new ParsePosition(0);
            Date parse = f383b.parse(str, parsePosition);
            if (parsePosition.getIndex() == str.length()) {
                return parse.getTime();
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    public static Cache.Entry a(Map<String, List<String>> map) {
        long j;
        long currentTimeMillis = System.currentTimeMillis();
        String singleHeaderFieldByKey = HttpHelper.getSingleHeaderFieldByKey(map, "Cache-Control");
        int i = 0;
        long j2 = 0;
        if (singleHeaderFieldByKey != null) {
            String[] split = singleHeaderFieldByKey.split(",");
            j = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                }
                i++;
            }
            i = 1;
        } else {
            j = 0;
        }
        String singleHeaderFieldByKey2 = HttpHelper.getSingleHeaderFieldByKey(map, "Date");
        long a2 = singleHeaderFieldByKey2 != null ? a(singleHeaderFieldByKey2) : 0;
        String singleHeaderFieldByKey3 = HttpHelper.getSingleHeaderFieldByKey(map, "Expires");
        long a3 = singleHeaderFieldByKey3 != null ? a(singleHeaderFieldByKey3) : 0;
        String singleHeaderFieldByKey4 = HttpHelper.getSingleHeaderFieldByKey(map, "Last-Modified");
        long a4 = singleHeaderFieldByKey4 != null ? a(singleHeaderFieldByKey4) : 0;
        String singleHeaderFieldByKey5 = HttpHelper.getSingleHeaderFieldByKey(map, "ETag");
        if (i != 0) {
            j2 = currentTimeMillis + (j * 1000);
        } else if (a2 > 0 && a3 >= a2) {
            j2 = currentTimeMillis + (a3 - a2);
        }
        Cache.Entry entry = new Cache.Entry();
        entry.etag = singleHeaderFieldByKey5;
        entry.ttl = j2;
        entry.serverDate = a2;
        entry.lastModified = a4;
        entry.responseHeaders = map;
        return entry;
    }
}
