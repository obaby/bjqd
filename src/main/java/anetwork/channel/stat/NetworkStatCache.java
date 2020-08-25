package anetwork.channel.stat;

import anet.channel.util.StringUtils;
import anetwork.channel.statist.StatisticData;
import com.alipay.sdk.util.h;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: Taobao */
class NetworkStatCache implements INetworkStat {
    private static final int MAX_SIZE = 100;
    private static final String RESET_STAT = "{\"oneWayTime\" : 0, \"totalSize\" : 0}";
    private Map<String, String> lruCache;

    /* compiled from: Taobao */
    private static class holder {
        public static NetworkStatCache instance = new NetworkStatCache();

        private holder() {
        }
    }

    public static NetworkStatCache getInstance() {
        return holder.instance;
    }

    private NetworkStatCache() {
        this.lruCache = Collections.synchronizedMap(new LinkedHashMap<String, String>() {
            /* access modifiers changed from: protected */
            public boolean removeEldestEntry(Map.Entry<String, String> entry) {
                return size() > 100;
            }
        });
    }

    public void put(String str, StatisticData statisticData) {
        if (!StringUtils.isBlank(str)) {
            StringBuilder sb = new StringBuilder(48);
            sb.append("{\"oneWayTime\" : ");
            sb.append(statisticData.oneWayTime_ANet);
            sb.append(", \"totalSize\" : ");
            sb.append(statisticData.totalSize);
            sb.append(h.d);
            this.lruCache.put(str, sb.toString());
        }
    }

    public void reset(String str) {
        if (this.lruCache.containsKey(str)) {
            this.lruCache.put(str, RESET_STAT);
        }
    }

    public String get(String str) {
        return this.lruCache.get(str);
    }
}
