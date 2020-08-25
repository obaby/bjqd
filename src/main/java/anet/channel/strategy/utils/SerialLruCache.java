package anet.channel.strategy.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: Taobao */
public class SerialLruCache<K, V> extends LinkedHashMap<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private int f324a;

    public boolean entryRemoved(Map.Entry<K, V> entry) {
        return true;
    }

    public SerialLruCache(int i) {
        super(i + 1, 1.0f, true);
        this.f324a = i;
    }

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        if (size() > this.f324a) {
            return entryRemoved(entry);
        }
        return false;
    }
}
