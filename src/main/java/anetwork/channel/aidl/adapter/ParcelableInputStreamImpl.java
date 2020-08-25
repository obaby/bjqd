package anetwork.channel.aidl.adapter;

import android.os.RemoteException;
import anet.channel.bytes.ByteArray;
import anet.channel.util.ALog;
import anetwork.channel.aidl.ParcelableInputStream;
import anetwork.channel.entity.g;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Taobao */
public class ParcelableInputStreamImpl extends ParcelableInputStream.Stub {
    private static final ByteArray EOS = ByteArray.create(0);
    private static final String TAG = "anet.ParcelableInputStreamImpl";
    private int blockIndex;
    private int blockOffset;
    private LinkedList<ByteArray> byteList = new LinkedList<>();
    private int contentLength;
    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    final ReentrantLock lock = new ReentrantLock();
    final Condition newDataArrive = this.lock.newCondition();
    private int rto = 10000;
    private String seqNo = "";

    public void write(ByteArray byteArray) {
        if (!this.isClosed.get()) {
            this.lock.lock();
            try {
                this.byteList.add(byteArray);
                this.newDataArrive.signal();
            } finally {
                this.lock.unlock();
            }
        }
    }

    public void writeEnd() {
        write(EOS);
        if (ALog.isPrintLog(1)) {
            ALog.d(TAG, "set EOS flag to stream", this.seqNo, new Object[0]);
        }
    }

    private void recycleCurrentItem() {
        this.lock.lock();
        try {
            this.byteList.set(this.blockIndex, EOS).recycle();
        } finally {
            this.lock.unlock();
        }
    }

    public int available() throws RemoteException {
        if (!this.isClosed.get()) {
            this.lock.lock();
            try {
                int i = 0;
                if (this.blockIndex == this.byteList.size()) {
                    return 0;
                }
                ListIterator<ByteArray> listIterator = this.byteList.listIterator(this.blockIndex);
                while (listIterator.hasNext()) {
                    i += listIterator.next().getDataLength();
                }
                int i2 = i - this.blockOffset;
                this.lock.unlock();
                return i2;
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new RuntimeException("Stream is closed");
        }
    }

    public void close() throws RemoteException {
        if (this.isClosed.compareAndSet(false, true)) {
            this.lock.lock();
            try {
                Iterator it = this.byteList.iterator();
                while (it.hasNext()) {
                    ByteArray byteArray = (ByteArray) it.next();
                    if (byteArray != EOS) {
                        byteArray.recycle();
                    }
                }
                this.byteList.clear();
                this.byteList = null;
                this.blockIndex = -1;
                this.blockOffset = -1;
                this.contentLength = 0;
            } finally {
                this.lock.unlock();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:19|20|22|23|24) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        r4.lock.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007b, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x006b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int readByte() throws android.os.RemoteException {
        /*
            r4 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.isClosed
            boolean r0 = r0.get()
            if (r0 != 0) goto L_0x007c
            java.util.concurrent.locks.ReentrantLock r0 = r4.lock
            r0.lock()
        L_0x000d:
            int r0 = r4.blockIndex     // Catch:{ InterruptedException -> 0x006b }
            java.util.LinkedList<anet.channel.bytes.ByteArray> r1 = r4.byteList     // Catch:{ InterruptedException -> 0x006b }
            int r1 = r1.size()     // Catch:{ InterruptedException -> 0x006b }
            if (r0 != r1) goto L_0x0030
            java.util.concurrent.locks.Condition r0 = r4.newDataArrive     // Catch:{ InterruptedException -> 0x006b }
            int r1 = r4.rto     // Catch:{ InterruptedException -> 0x006b }
            long r1 = (long) r1     // Catch:{ InterruptedException -> 0x006b }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x006b }
            boolean r0 = r0.await(r1, r3)     // Catch:{ InterruptedException -> 0x006b }
            if (r0 == 0) goto L_0x0025
            goto L_0x0030
        L_0x0025:
            r4.close()     // Catch:{ InterruptedException -> 0x006b }
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ InterruptedException -> 0x006b }
            java.lang.String r1 = "await timeout."
            r0.<init>(r1)     // Catch:{ InterruptedException -> 0x006b }
            throw r0     // Catch:{ InterruptedException -> 0x006b }
        L_0x0030:
            java.util.LinkedList<anet.channel.bytes.ByteArray> r0 = r4.byteList     // Catch:{ InterruptedException -> 0x006b }
            int r1 = r4.blockIndex     // Catch:{ InterruptedException -> 0x006b }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ InterruptedException -> 0x006b }
            anet.channel.bytes.ByteArray r0 = (anet.channel.bytes.ByteArray) r0     // Catch:{ InterruptedException -> 0x006b }
            anet.channel.bytes.ByteArray r1 = EOS     // Catch:{ InterruptedException -> 0x006b }
            if (r0 != r1) goto L_0x0040
            r0 = -1
            goto L_0x0056
        L_0x0040:
            int r1 = r4.blockOffset     // Catch:{ InterruptedException -> 0x006b }
            int r2 = r0.getDataLength()     // Catch:{ InterruptedException -> 0x006b }
            if (r1 >= r2) goto L_0x005c
            byte[] r0 = r0.getBuffer()     // Catch:{ InterruptedException -> 0x006b }
            int r1 = r4.blockOffset     // Catch:{ InterruptedException -> 0x006b }
            byte r0 = r0[r1]     // Catch:{ InterruptedException -> 0x006b }
            int r1 = r4.blockOffset     // Catch:{ InterruptedException -> 0x006b }
            int r1 = r1 + 1
            r4.blockOffset = r1     // Catch:{ InterruptedException -> 0x006b }
        L_0x0056:
            java.util.concurrent.locks.ReentrantLock r1 = r4.lock
            r1.unlock()
            return r0
        L_0x005c:
            r4.recycleCurrentItem()     // Catch:{ InterruptedException -> 0x006b }
            int r0 = r4.blockIndex     // Catch:{ InterruptedException -> 0x006b }
            int r0 = r0 + 1
            r4.blockIndex = r0     // Catch:{ InterruptedException -> 0x006b }
            r0 = 0
            r4.blockOffset = r0     // Catch:{ InterruptedException -> 0x006b }
            goto L_0x000d
        L_0x0069:
            r0 = move-exception
            goto L_0x0076
        L_0x006b:
            r4.close()     // Catch:{ all -> 0x0069 }
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0069 }
            java.lang.String r1 = "await interrupt"
            r0.<init>(r1)     // Catch:{ all -> 0x0069 }
            throw r0     // Catch:{ all -> 0x0069 }
        L_0x0076:
            java.util.concurrent.locks.ReentrantLock r1 = r4.lock
            r1.unlock()
            throw r0
        L_0x007c:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Stream is closed"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.aidl.adapter.ParcelableInputStreamImpl.readByte():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007e, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008a, code lost:
        throw new java.lang.RuntimeException("await interrupt");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        r5.lock.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0090, code lost:
        throw r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0080 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int readBytes(byte[] r6, int r7, int r8) throws android.os.RemoteException {
        /*
            r5 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.isClosed
            boolean r0 = r0.get()
            if (r0 != 0) goto L_0x00a8
            if (r6 == 0) goto L_0x00a2
            if (r7 < 0) goto L_0x009c
            if (r8 < 0) goto L_0x009c
            int r8 = r8 + r7
            int r0 = r6.length
            if (r8 > r0) goto L_0x009c
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            r0.lock()
            r0 = r7
        L_0x0018:
            if (r0 >= r8) goto L_0x0091
            int r1 = r5.blockIndex     // Catch:{ InterruptedException -> 0x0080 }
            java.util.LinkedList<anet.channel.bytes.ByteArray> r2 = r5.byteList     // Catch:{ InterruptedException -> 0x0080 }
            int r2 = r2.size()     // Catch:{ InterruptedException -> 0x0080 }
            if (r1 != r2) goto L_0x003d
            java.util.concurrent.locks.Condition r1 = r5.newDataArrive     // Catch:{ InterruptedException -> 0x0080 }
            int r2 = r5.rto     // Catch:{ InterruptedException -> 0x0080 }
            long r2 = (long) r2     // Catch:{ InterruptedException -> 0x0080 }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x0080 }
            boolean r1 = r1.await(r2, r4)     // Catch:{ InterruptedException -> 0x0080 }
            if (r1 == 0) goto L_0x0032
            goto L_0x003d
        L_0x0032:
            r5.close()     // Catch:{ InterruptedException -> 0x0080 }
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ InterruptedException -> 0x0080 }
            java.lang.String r7 = "await timeout."
            r6.<init>(r7)     // Catch:{ InterruptedException -> 0x0080 }
            throw r6     // Catch:{ InterruptedException -> 0x0080 }
        L_0x003d:
            java.util.LinkedList<anet.channel.bytes.ByteArray> r1 = r5.byteList     // Catch:{ InterruptedException -> 0x0080 }
            int r2 = r5.blockIndex     // Catch:{ InterruptedException -> 0x0080 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ InterruptedException -> 0x0080 }
            anet.channel.bytes.ByteArray r1 = (anet.channel.bytes.ByteArray) r1     // Catch:{ InterruptedException -> 0x0080 }
            anet.channel.bytes.ByteArray r2 = EOS     // Catch:{ InterruptedException -> 0x0080 }
            if (r1 != r2) goto L_0x004c
            goto L_0x0091
        L_0x004c:
            int r2 = r1.getDataLength()     // Catch:{ InterruptedException -> 0x0080 }
            int r3 = r5.blockOffset     // Catch:{ InterruptedException -> 0x0080 }
            int r2 = r2 - r3
            int r3 = r8 - r0
            if (r2 >= r3) goto L_0x006e
            byte[] r1 = r1.getBuffer()     // Catch:{ InterruptedException -> 0x0080 }
            int r3 = r5.blockOffset     // Catch:{ InterruptedException -> 0x0080 }
            java.lang.System.arraycopy(r1, r3, r6, r0, r2)     // Catch:{ InterruptedException -> 0x0080 }
            int r0 = r0 + r2
            r5.recycleCurrentItem()     // Catch:{ InterruptedException -> 0x0080 }
            int r1 = r5.blockIndex     // Catch:{ InterruptedException -> 0x0080 }
            int r1 = r1 + 1
            r5.blockIndex = r1     // Catch:{ InterruptedException -> 0x0080 }
            r1 = 0
            r5.blockOffset = r1     // Catch:{ InterruptedException -> 0x0080 }
            goto L_0x0018
        L_0x006e:
            byte[] r1 = r1.getBuffer()     // Catch:{ InterruptedException -> 0x0080 }
            int r2 = r5.blockOffset     // Catch:{ InterruptedException -> 0x0080 }
            java.lang.System.arraycopy(r1, r2, r6, r0, r3)     // Catch:{ InterruptedException -> 0x0080 }
            int r1 = r5.blockOffset     // Catch:{ InterruptedException -> 0x0080 }
            int r1 = r1 + r3
            r5.blockOffset = r1     // Catch:{ InterruptedException -> 0x0080 }
            int r0 = r0 + r3
            goto L_0x0018
        L_0x007e:
            r6 = move-exception
            goto L_0x008b
        L_0x0080:
            r5.close()     // Catch:{ all -> 0x007e }
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ all -> 0x007e }
            java.lang.String r7 = "await interrupt"
            r6.<init>(r7)     // Catch:{ all -> 0x007e }
            throw r6     // Catch:{ all -> 0x007e }
        L_0x008b:
            java.util.concurrent.locks.ReentrantLock r7 = r5.lock
            r7.unlock()
            throw r6
        L_0x0091:
            java.util.concurrent.locks.ReentrantLock r6 = r5.lock
            r6.unlock()
            int r0 = r0 - r7
            if (r0 <= 0) goto L_0x009a
            goto L_0x009b
        L_0x009a:
            r0 = -1
        L_0x009b:
            return r0
        L_0x009c:
            java.lang.ArrayIndexOutOfBoundsException r6 = new java.lang.ArrayIndexOutOfBoundsException
            r6.<init>()
            throw r6
        L_0x00a2:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            r6.<init>()
            throw r6
        L_0x00a8:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.String r7 = "Stream is closed"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.aidl.adapter.ParcelableInputStreamImpl.readBytes(byte[], int, int):int");
    }

    public int read(byte[] bArr) throws RemoteException {
        return readBytes(bArr, 0, bArr.length);
    }

    public long skip(int i) throws RemoteException {
        this.lock.lock();
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            try {
                if (this.blockIndex == this.byteList.size()) {
                    break;
                }
                ByteArray byteArray = this.byteList.get(this.blockIndex);
                if (byteArray == EOS) {
                    break;
                }
                int dataLength = byteArray.getDataLength();
                if (dataLength - this.blockOffset < i - i2) {
                    i2 += dataLength - this.blockOffset;
                    recycleCurrentItem();
                    this.blockIndex++;
                    this.blockOffset = 0;
                    break;
                }
                this.blockOffset += i - i;
                i2 = i;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
        this.lock.unlock();
        return (long) i2;
    }

    public int length() throws RemoteException {
        return this.contentLength;
    }

    public void init(g gVar, int i) {
        this.contentLength = i;
        this.seqNo = gVar.e;
        this.rto = gVar.d;
    }
}
