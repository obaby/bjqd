package anet.channel.util;

import android.text.TextUtils;
import anet.channel.request.Request;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
public class HttpHelper {
    public static Map<String, List<String>> cloneMap(Map<String, List<String>> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(next.getKey(), new ArrayList((Collection) next.getValue()));
        }
        return hashMap;
    }

    public static List<String> getHeaderFieldByKey(Map<String, List<String>> map, String str) {
        if (map == null || map.isEmpty() || TextUtils.isEmpty(str)) {
            return null;
        }
        for (Map.Entry next : map.entrySet()) {
            if (str.equalsIgnoreCase((String) next.getKey())) {
                return (List) next.getValue();
            }
        }
        return null;
    }

    public static String getSingleHeaderFieldByKey(Map<String, List<String>> map, String str) {
        List<String> headerFieldByKey = getHeaderFieldByKey(map, str);
        if (headerFieldByKey == null || headerFieldByKey.isEmpty()) {
            return null;
        }
        return headerFieldByKey.get(0);
    }

    public static void removeHeaderFiledByKey(Map<String, List<String>> map, String str) {
        if (str != null) {
            Iterator<String> it = map.keySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (str.equalsIgnoreCase(it.next())) {
                        break;
                    }
                } else {
                    str = null;
                    break;
                }
            }
            if (str != null) {
                map.remove(str);
            }
        }
    }

    public static boolean checkRedirect(Request request, int i) {
        return request.isRedirectEnable() && i >= 300 && i < 400 && i != 304 && request.getRedirectTimes() < 10;
    }

    public static int parseContentLength(Map<String, List<String>> map) {
        try {
            return Integer.parseInt(getSingleHeaderFieldByKey(map, "Content-Length"));
        } catch (Exception unused) {
            return 0;
        }
    }

    public static long parseServerRT(Map<String, List<String>> map) {
        try {
            List list = map.get(HttpConstant.SERVER_RT);
            if (list == null || list.isEmpty()) {
                return 0;
            }
            return Long.parseLong((String) list.get(0));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static int parseStatusCode(Map<String, List<String>> map) {
        try {
            List list = map.get(HttpConstant.STATUS);
            if (list != null && !list.isEmpty()) {
                return Integer.parseInt((String) list.get(0));
            }
        } catch (NumberFormatException unused) {
        }
        return 0;
    }

    public static String trySolveFileExtFromUrlPath(String str) {
        int lastIndexOf;
        if (str == null) {
            return null;
        }
        try {
            int length = str.length();
            if (length > 1 && (lastIndexOf = str.lastIndexOf(47)) != -1) {
                if (lastIndexOf != length - 1) {
                    int lastIndexOf2 = str.lastIndexOf(46);
                    if (lastIndexOf2 != -1) {
                        if (lastIndexOf2 > lastIndexOf) {
                            return str.substring(lastIndexOf2 + 1, length);
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @Deprecated
    public static String trySolveFileExtFromURL(URL url) {
        return trySolveFileExtFromUrlPath(url.getPath());
    }
}
