package cn.xports.baselib.util;

import anet.channel.strategy.dispatch.DispatchConstants;
import cn.xports.baselib.mvp.GlobalStaticConfig;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SignUtil {
    public static Map<String, String> getRequestParams(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            if (next.getValue() != null && !((String) next.getValue()).isEmpty()) {
                hashMap.put(next.getKey(), next.getValue());
            }
        }
        return hashMap;
    }

    public static void wrapData(Map<String, String> map, String str, boolean z) {
        map.put("apiKey", z ? GlobalStaticConfig.getInstance().getcApiKey() : GlobalStaticConfig.getInstance().getApiKey());
        map.put("timestamp", Long.toString(new Date().getTime()));
        map.put(DispatchConstants.SIGN, getSign(map.entrySet(), str, z));
    }

    public static String getSign(Set<Map.Entry<String, String>> set, String str, boolean z) {
        StringBuilder sb = new StringBuilder(str);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : set) {
            arrayList.add(((String) next.getKey()) + "=" + ((String) next.getValue()));
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        Arrays.sort(strArr);
        for (String append : strArr) {
            sb.append(append);
        }
        sb.append(z ? GlobalStaticConfig.getInstance().getcSecretKey() : GlobalStaticConfig.getInstance().getSecretKey());
        try {
            return MD5Utils.getMD5(URLEncoder.encode(sb.toString(), "UTF-8").replaceAll("\\+", "%20"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
