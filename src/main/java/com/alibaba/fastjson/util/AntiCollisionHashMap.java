package com.alibaba.fastjson.util;

import android.support.v7.widget.ActivityChooserView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

public class AntiCollisionHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int KEY = 16777619;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int M_MASK = -2023358765;
    static final int SEED = -2128831035;
    private static final long serialVersionUID = 362498820763181265L;
    private transient Set<Map.Entry<K, V>> entrySet;
    volatile transient Set<K> keySet;
    final float loadFactor;
    volatile transient int modCount;
    final int random;
    transient int size;
    transient Entry<K, V>[] table;
    int threshold;
    volatile transient Collection<V> values;

    static int hash(int i) {
        int i2 = i * i;
        int i3 = i2 ^ ((i2 >>> 20) ^ (i2 >>> 12));
        return (i3 >>> 4) ^ ((i3 >>> 7) ^ i3);
    }

    static int indexFor(int i, int i2) {
        return i & (i2 - 1);
    }

    /* access modifiers changed from: package-private */
    public void init() {
    }

    private int hashString(String str) {
        int i = this.random * SEED;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i = (i * KEY) ^ str.charAt(i2);
        }
        return ((i >> 1) ^ i) & M_MASK;
    }

    public AntiCollisionHashMap(int i, float f) {
        this.keySet = null;
        this.values = null;
        this.random = new Random().nextInt(99999);
        this.entrySet = null;
        if (i >= 0) {
            i = i > MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY : i;
            if (f <= 0.0f || Float.isNaN(f)) {
                throw new IllegalArgumentException("Illegal load factor: " + f);
            }
            int i2 = 1;
            while (i2 < i) {
                i2 <<= 1;
            }
            this.loadFactor = f;
            this.threshold = (int) (((float) i2) * f);
            this.table = new Entry[i2];
            init();
            return;
        }
        throw new IllegalArgumentException("Illegal initial capacity: " + i);
    }

    public AntiCollisionHashMap(int i) {
        this(i, DEFAULT_LOAD_FACTOR);
    }

    public AntiCollisionHashMap() {
        this.keySet = null;
        this.values = null;
        this.random = new Random().nextInt(99999);
        this.entrySet = null;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.threshold = 12;
        this.table = new Entry[16];
        init();
    }

    public AntiCollisionHashMap(Map<? extends K, ? extends V> map) {
        this(Math.max(((int) (((float) map.size()) / DEFAULT_LOAD_FACTOR)) + 1, 16), DEFAULT_LOAD_FACTOR);
        putAllForCreate(map);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public V get(Object obj) {
        int i;
        K k;
        if (obj == null) {
            return getForNullKey();
        }
        if (obj instanceof String) {
            i = hash(hashString((String) obj));
        } else {
            i = hash(obj.hashCode());
        }
        for (Entry<K, V> entry = this.table[indexFor(i, this.table.length)]; entry != null; entry = entry.next) {
            if (entry.hash == i && ((k = entry.key) == obj || obj.equals(k))) {
                return entry.value;
            }
        }
        return null;
    }

    private V getForNullKey() {
        for (Entry<K, V> entry = this.table[0]; entry != null; entry = entry.next) {
            if (entry.key == null) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean containsKey(Object obj) {
        return getEntry(obj) != null;
    }

    /* access modifiers changed from: package-private */
    public final Entry<K, V> getEntry(Object obj) {
        int i;
        K k;
        if (obj == null) {
            i = 0;
        } else if (obj instanceof String) {
            i = hash(hashString((String) obj));
        } else {
            i = hash(obj.hashCode());
        }
        for (Entry<K, V> entry = this.table[indexFor(i, this.table.length)]; entry != null; entry = entry.next) {
            if (entry.hash == i && ((k = entry.key) == obj || (obj != null && obj.equals(k)))) {
                return entry;
            }
        }
        return null;
    }

    public V put(K k, V v) {
        int i;
        K k2;
        if (k == null) {
            return putForNullKey(v);
        }
        if (k instanceof String) {
            i = hash(hashString((String) k));
        } else {
            i = hash(k.hashCode());
        }
        int indexFor = indexFor(i, this.table.length);
        Entry<K, V> entry = this.table[indexFor];
        while (entry != null) {
            if (entry.hash != i || ((k2 = entry.key) != k && !k.equals(k2))) {
                entry = entry.next;
            } else {
                V v2 = entry.value;
                entry.value = v;
                entry.recordAccess(this);
                return v2;
            }
        }
        this.modCount++;
        addEntry(i, k, v, indexFor);
        return null;
    }

    private V putForNullKey(V v) {
        for (Entry<K, V> entry = this.table[0]; entry != null; entry = entry.next) {
            if (entry.key == null) {
                V v2 = entry.value;
                entry.value = v;
                entry.recordAccess(this);
                return v2;
            }
        }
        this.modCount++;
        addEntry(0, (Object) null, v, 0);
        return null;
    }

    private void putForCreate(K k, V v) {
        int i;
        K k2;
        if (k == null) {
            i = 0;
        } else if (k instanceof String) {
            i = hash(hashString((String) k));
        } else {
            i = hash(k.hashCode());
        }
        int indexFor = indexFor(i, this.table.length);
        Entry<K, V> entry = this.table[indexFor];
        while (entry != null) {
            if (entry.hash != i || ((k2 = entry.key) != k && (k == null || !k.equals(k2)))) {
                entry = entry.next;
            } else {
                entry.value = v;
                return;
            }
        }
        createEntry(i, k, v, indexFor);
    }

    private void putAllForCreate(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            putForCreate(next.getKey(), next.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    public void resize(int i) {
        if (this.table.length == MAXIMUM_CAPACITY) {
            this.threshold = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            return;
        }
        Entry<K, V>[] entryArr = new Entry[i];
        transfer(entryArr);
        this.table = entryArr;
        this.threshold = (int) (((float) i) * this.loadFactor);
    }

    /* access modifiers changed from: package-private */
    public void transfer(Entry[] entryArr) {
        Entry<K, V>[] entryArr2 = this.table;
        int length = entryArr.length;
        for (int i = 0; i < entryArr2.length; i++) {
            Entry<K, V> entry = entryArr2[i];
            if (entry != null) {
                entryArr2[i] = null;
                while (true) {
                    Entry<K, V> entry2 = entry.next;
                    int indexFor = indexFor(entry.hash, length);
                    entry.next = entryArr[indexFor];
                    entryArr[indexFor] = entry;
                    if (entry2 == null) {
                        break;
                    }
                    entry = entry2;
                }
            }
        }
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        int size2 = map.size();
        if (size2 != 0) {
            if (size2 > this.threshold) {
                int i = (int) ((((float) size2) / this.loadFactor) + 1.0f);
                if (i > MAXIMUM_CAPACITY) {
                    i = MAXIMUM_CAPACITY;
                }
                int length = this.table.length;
                while (length < i) {
                    length <<= 1;
                }
                if (length > this.table.length) {
                    resize(length);
                }
            }
            for (Map.Entry next : map.entrySet()) {
                put(next.getKey(), next.getValue());
            }
        }
    }

    public V remove(Object obj) {
        Entry removeEntryForKey = removeEntryForKey(obj);
        if (removeEntryForKey == null) {
            return null;
        }
        return removeEntryForKey.value;
    }

    /* access modifiers changed from: package-private */
    public final Entry<K, V> removeEntryForKey(Object obj) {
        int i;
        K k;
        if (obj == null) {
            i = 0;
        } else if (obj instanceof String) {
            i = hash(hashString((String) obj));
        } else {
            i = hash(obj.hashCode());
        }
        int indexFor = indexFor(i, this.table.length);
        Entry<K, V> entry = this.table[indexFor];
        Entry<K, V> entry2 = entry;
        while (entry != null) {
            Entry<K, V> entry3 = entry.next;
            if (entry.hash != i || ((k = entry.key) != obj && (obj == null || !obj.equals(k)))) {
                entry2 = entry;
                entry = entry3;
            } else {
                this.modCount++;
                this.size--;
                if (entry2 == entry) {
                    this.table[indexFor] = entry3;
                } else {
                    entry2.next = entry3;
                }
                entry.recordRemoval(this);
                return entry;
            }
        }
        return entry;
    }

    /* access modifiers changed from: package-private */
    public final Entry<K, V> removeMapping(Object obj) {
        int i;
        if (!(obj instanceof Map.Entry)) {
            return null;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        if (key == null) {
            i = 0;
        } else if (key instanceof String) {
            i = hash(hashString((String) key));
        } else {
            i = hash(key.hashCode());
        }
        int indexFor = indexFor(i, this.table.length);
        Entry<K, V> entry2 = this.table[indexFor];
        Entry<K, V> entry3 = entry2;
        while (entry2 != null) {
            Entry<K, V> entry4 = entry2.next;
            if (entry2.hash != i || !entry2.equals(entry)) {
                entry3 = entry2;
                entry2 = entry4;
            } else {
                this.modCount++;
                this.size--;
                if (entry3 == entry2) {
                    this.table[indexFor] = entry4;
                } else {
                    entry3.next = entry4;
                }
                entry2.recordRemoval(this);
                return entry2;
            }
        }
        return entry2;
    }

    public void clear() {
        this.modCount++;
        Entry<K, V>[] entryArr = this.table;
        for (int i = 0; i < entryArr.length; i++) {
            entryArr[i] = null;
        }
        this.size = 0;
    }

    public boolean containsValue(Object obj) {
        if (obj == null) {
            return containsNullValue();
        }
        Entry<K, V>[] entryArr = this.table;
        for (Entry<K, V> entry : entryArr) {
            while (entry != null) {
                if (obj.equals(entry.value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    private boolean containsNullValue() {
        Entry<K, V>[] entryArr = this.table;
        for (Entry<K, V> entry : entryArr) {
            while (entry != null) {
                if (entry.value == null) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    public Object clone() {
        AntiCollisionHashMap antiCollisionHashMap;
        try {
            antiCollisionHashMap = (AntiCollisionHashMap) super.clone();
        } catch (CloneNotSupportedException unused) {
            antiCollisionHashMap = null;
        }
        antiCollisionHashMap.table = new Entry[this.table.length];
        antiCollisionHashMap.entrySet = null;
        antiCollisionHashMap.modCount = 0;
        antiCollisionHashMap.size = 0;
        antiCollisionHashMap.init();
        antiCollisionHashMap.putAllForCreate(this);
        return antiCollisionHashMap;
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        Entry<K, V> next;
        V value;

        /* access modifiers changed from: package-private */
        public void recordAccess(AntiCollisionHashMap<K, V> antiCollisionHashMap) {
        }

        /* access modifiers changed from: package-private */
        public void recordRemoval(AntiCollisionHashMap<K, V> antiCollisionHashMap) {
        }

        Entry(int i, K k, V v, Entry<K, V> entry) {
            this.value = v;
            this.next = entry;
            this.key = k;
            this.hash = i;
        }

        public final K getKey() {
            return this.key;
        }

        public final V getValue() {
            return this.value;
        }

        public final V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key2 = getKey();
            Object key3 = entry.getKey();
            if (key2 == key3 || (key2 != null && key2.equals(key3))) {
                Object value2 = getValue();
                Object value3 = entry.getValue();
                if (value2 == value3) {
                    return true;
                }
                if (value2 == null || !value2.equals(value3)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int i = 0;
            int hashCode = this.key == null ? 0 : this.key.hashCode();
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode ^ i;
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* access modifiers changed from: package-private */
    public void addEntry(int i, K k, V v, int i2) {
        this.table[i2] = new Entry<>(i, k, v, this.table[i2]);
        int i3 = this.size;
        this.size = i3 + 1;
        if (i3 >= this.threshold) {
            resize(this.table.length * 2);
        }
    }

    /* access modifiers changed from: package-private */
    public void createEntry(int i, K k, V v, int i2) {
        this.table[i2] = new Entry<>(i, k, v, this.table[i2]);
        this.size++;
    }

    private abstract class HashIterator<E> implements Iterator<E> {
        Entry<K, V> current;
        int expectedModCount;
        int index;
        Entry<K, V> next;

        HashIterator() {
            this.expectedModCount = AntiCollisionHashMap.this.modCount;
            if (AntiCollisionHashMap.this.size > 0) {
                Entry<K, V>[] entryArr = AntiCollisionHashMap.this.table;
                while (this.index < entryArr.length) {
                    int i = this.index;
                    this.index = i + 1;
                    Entry<K, V> entry = entryArr[i];
                    this.next = entry;
                    if (entry != null) {
                        return;
                    }
                }
            }
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        /* access modifiers changed from: package-private */
        public final Entry<K, V> nextEntry() {
            if (AntiCollisionHashMap.this.modCount == this.expectedModCount) {
                Entry<K, V> entry = this.next;
                if (entry != null) {
                    Entry<K, V> entry2 = entry.next;
                    this.next = entry2;
                    if (entry2 == null) {
                        Entry<K, V>[] entryArr = AntiCollisionHashMap.this.table;
                        while (this.index < entryArr.length) {
                            int i = this.index;
                            this.index = i + 1;
                            Entry<K, V> entry3 = entryArr[i];
                            this.next = entry3;
                            if (entry3 != null) {
                                break;
                            }
                        }
                    }
                    this.current = entry;
                    return entry;
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        public void remove() {
            if (this.current == null) {
                throw new IllegalStateException();
            } else if (AntiCollisionHashMap.this.modCount == this.expectedModCount) {
                K k = this.current.key;
                this.current = null;
                AntiCollisionHashMap.this.removeEntryForKey(k);
                this.expectedModCount = AntiCollisionHashMap.this.modCount;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    private final class ValueIterator extends AntiCollisionHashMap<K, V>.HashIterator<V> {
        private ValueIterator() {
            super();
        }

        public V next() {
            return nextEntry().value;
        }
    }

    private final class KeyIterator extends AntiCollisionHashMap<K, V>.HashIterator<K> {
        private KeyIterator() {
            super();
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    private final class EntryIterator extends AntiCollisionHashMap<K, V>.HashIterator<Map.Entry<K, V>> {
        private EntryIterator() {
            super();
        }

        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<K> newKeyIterator() {
        return new KeyIterator();
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> newValueIterator() {
        return new ValueIterator();
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> newEntryIterator() {
        return new EntryIterator();
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    private final class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        public Iterator<K> iterator() {
            return AntiCollisionHashMap.this.newKeyIterator();
        }

        public int size() {
            return AntiCollisionHashMap.this.size;
        }

        public boolean contains(Object obj) {
            return AntiCollisionHashMap.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return AntiCollisionHashMap.this.removeEntryForKey(obj) != null;
        }

        public void clear() {
            AntiCollisionHashMap.this.clear();
        }
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values2 = new Values();
        this.values = values2;
        return values2;
    }

    private final class Values extends AbstractCollection<V> {
        private Values() {
        }

        public Iterator<V> iterator() {
            return AntiCollisionHashMap.this.newValueIterator();
        }

        public int size() {
            return AntiCollisionHashMap.this.size;
        }

        public boolean contains(Object obj) {
            return AntiCollisionHashMap.this.containsValue(obj);
        }

        public void clear() {
            AntiCollisionHashMap.this.clear();
        }
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet0();
    }

    private Set<Map.Entry<K, V>> entrySet0() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return AntiCollisionHashMap.this.newEntryIterator();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Entry entry2 = AntiCollisionHashMap.this.getEntry(entry.getKey());
            if (entry2 == null || !entry2.equals(entry)) {
                return false;
            }
            return true;
        }

        public boolean remove(Object obj) {
            return AntiCollisionHashMap.this.removeMapping(obj) != null;
        }

        public int size() {
            return AntiCollisionHashMap.this.size;
        }

        public void clear() {
            AntiCollisionHashMap.this.clear();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Iterator it = this.size > 0 ? entrySet0().iterator() : null;
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.table.length);
        objectOutputStream.writeInt(this.size);
        if (it != null) {
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.table = new Entry[objectInputStream.readInt()];
        init();
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            putForCreate(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* access modifiers changed from: package-private */
    public int capacity() {
        return this.table.length;
    }

    /* access modifiers changed from: package-private */
    public float loadFactor() {
        return this.loadFactor;
    }
}
