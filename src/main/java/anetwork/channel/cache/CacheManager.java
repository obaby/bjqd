package anetwork.channel.cache;

import anet.channel.util.ALog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: Taobao */
public class CacheManager {

    /* renamed from: a  reason: collision with root package name */
    private static List<a> f376a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private static final ReentrantReadWriteLock f377b = new ReentrantReadWriteLock();

    /* renamed from: c  reason: collision with root package name */
    private static final ReentrantReadWriteLock.ReadLock f378c = f377b.readLock();
    private static final ReentrantReadWriteLock.WriteLock d = f377b.writeLock();

    /* compiled from: Taobao */
    private static class a implements Comparable<a> {

        /* renamed from: a  reason: collision with root package name */
        final Cache f379a;

        /* renamed from: b  reason: collision with root package name */
        final CachePrediction f380b;

        /* renamed from: c  reason: collision with root package name */
        final int f381c;

        a(Cache cache, CachePrediction cachePrediction, int i) {
            this.f379a = cache;
            this.f380b = cachePrediction;
            this.f381c = i;
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            return this.f381c - aVar.f381c;
        }
    }

    public static void addCache(Cache cache, CachePrediction cachePrediction, int i) {
        if (cache == null) {
            throw new IllegalArgumentException("cache is null");
        } else if (cachePrediction != null) {
            try {
                d.lock();
                f376a.add(new a(cache, cachePrediction, i));
                Collections.sort(f376a);
            } finally {
                d.unlock();
            }
        } else {
            throw new IllegalArgumentException("prediction is null");
        }
    }

    public static void removeCache(Cache cache) {
        try {
            d.lock();
            ListIterator<a> listIterator = f376a.listIterator();
            while (true) {
                if (listIterator.hasNext()) {
                    if (listIterator.next().f379a == cache) {
                        listIterator.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
        } finally {
            d.unlock();
        }
    }

    public static Cache getCache(String str, Map<String, String> map) {
        Cache cache;
        try {
            f378c.lock();
            Iterator<a> it = f376a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    cache = null;
                    break;
                }
                a next = it.next();
                if (next.f380b.handleCache(str, map)) {
                    cache = next.f379a;
                    break;
                }
            }
            return cache;
        } finally {
            f378c.unlock();
        }
    }

    public static void clearAllCache() {
        ALog.w("anet.CacheManager", "clearAllCache", (String) null, new Object[0]);
        for (a aVar : f376a) {
            try {
                aVar.f379a.clear();
            } catch (Exception unused) {
            }
        }
    }
}
