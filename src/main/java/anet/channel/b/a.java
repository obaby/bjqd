package anet.channel.b;

import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import anetwork.channel.cache.Cache;
import com.taobao.alivfssdk.cache.AVFSCache;
import com.taobao.alivfssdk.cache.AVFSCacheConfig;
import com.taobao.alivfssdk.cache.AVFSCacheManager;
import com.taobao.alivfssdk.cache.IAVFSCache;

/* compiled from: Taobao */
public class a implements Cache {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f148a = false;

    /* renamed from: b  reason: collision with root package name */
    private static Object f149b;

    /* renamed from: c  reason: collision with root package name */
    private static Object f150c;

    static {
        try {
            Class.forName("com.taobao.alivfssdk.cache.AVFSCacheManager");
            f149b = new b();
            f150c = new c();
        } catch (ClassNotFoundException unused) {
            ALog.w("anet.AVFSCacheImpl", "no alivfs sdk!", (String) null, new Object[0]);
        }
    }

    public void a() {
        AVFSCache cacheForModule;
        if (f148a && (cacheForModule = AVFSCacheManager.getInstance().cacheForModule("networksdk.httpcache")) != null) {
            AVFSCacheConfig aVFSCacheConfig = new AVFSCacheConfig();
            aVFSCacheConfig.limitSize = 5242880L;
            aVFSCacheConfig.fileMemMaxSize = 1048576;
            cacheForModule.moduleConfig(aVFSCacheConfig);
        }
    }

    public Cache.Entry get(String str) {
        if (!f148a) {
            return null;
        }
        try {
            IAVFSCache b2 = b();
            if (b2 != null) {
                return (Cache.Entry) b2.objectForKey(StringUtils.md5ToHex(str));
            }
        } catch (Exception e) {
            ALog.e("anet.AVFSCacheImpl", "get cache failed", (String) null, e, new Object[0]);
        }
        return null;
    }

    public void put(String str, Cache.Entry entry) {
        if (f148a) {
            try {
                IAVFSCache b2 = b();
                if (b2 != null) {
                    b2.setObjectForKey(StringUtils.md5ToHex(str), entry, (IAVFSCache.OnObjectSetCallback) f149b);
                }
            } catch (Exception e) {
                ALog.e("anet.AVFSCacheImpl", "put cache failed", (String) null, e, new Object[0]);
            }
        }
    }

    public void clear() {
        if (f148a) {
            try {
                IAVFSCache b2 = b();
                if (b2 != null) {
                    b2.removeAllObject((IAVFSCache.OnAllObjectRemoveCallback) f150c);
                }
            } catch (Exception e) {
                ALog.e("anet.AVFSCacheImpl", "clear cache failed", (String) null, e, new Object[0]);
            }
        }
    }

    private IAVFSCache b() {
        AVFSCache cacheForModule = AVFSCacheManager.getInstance().cacheForModule("networksdk.httpcache");
        if (cacheForModule != null) {
            return cacheForModule.getFileCache();
        }
        return null;
    }
}
