package au.com.gridstone.rxstore;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.locks.ReentrantReadWriteLock;

final class Utils {
    private Utils() {
        throw new AssertionError("No instances.");
    }

    static void assertNotNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str + "must not be null.");
        }
    }

    static void runInReadLock(ReentrantReadWriteLock reentrantReadWriteLock, ThrowingRunnable throwingRunnable) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        try {
            throwingRunnable.run();
            readLock.unlock();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            readLock.unlock();
            throw th;
        }
    }

    static void runInWriteLock(ReentrantReadWriteLock reentrantReadWriteLock, ThrowingRunnable throwingRunnable) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            throwingRunnable.run();
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
            throw th;
        }
    }

    static <T> void converterWrite(T t, Converter converter, Type type, File file) throws IOException {
        File file2 = new File(file.getAbsolutePath() + ".tmp");
        converter.write(t, type, file2);
        if (!file.delete() || !file2.renameTo(file)) {
            throw new IOException("Failed to write value to file.");
        }
    }
}
