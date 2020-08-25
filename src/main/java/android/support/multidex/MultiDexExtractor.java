package android.support.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.alipay.sdk.util.e;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

final class MultiDexExtractor {
    private static final int BUFFER_SIZE = 16384;
    private static final String DEX_PREFIX = "classes";
    private static final String DEX_SUFFIX = ".dex";
    private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String LOCK_FILENAME = "MultiDex.lock";
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final long NO_VALUE = -1;
    private static final String PREFS_FILE = "multidex.version";
    private static final String TAG = "MultiDex";

    MultiDexExtractor() {
    }

    private static class ExtractedDex extends File {
        public long crc = -1;

        public ExtractedDex(File file, String str) {
            super(file, str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x011e A[SYNTHETIC, Splitter:B:40:0x011e] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<? extends java.io.File> load(android.content.Context r14, java.io.File r15, java.io.File r16, java.lang.String r17, boolean r18) throws java.io.IOException {
        /*
            r2 = r17
            r0 = r18
            java.lang.String r1 = "MultiDex"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "MultiDexExtractor.load("
            r3.append(r4)
            java.lang.String r4 = r15.getPath()
            r3.append(r4)
            java.lang.String r4 = ", "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r4 = ", "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r4 = ")"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.i(r1, r3)
            long r5 = getZipCrc(r15)
            java.io.File r8 = new java.io.File
            java.lang.String r1 = "MultiDex.lock"
            r3 = r16
            r8.<init>(r3, r1)
            java.io.RandomAccessFile r9 = new java.io.RandomAccessFile
            java.lang.String r1 = "rw"
            r9.<init>(r8, r1)
            r10 = 0
            java.nio.channels.FileChannel r11 = r9.getChannel()     // Catch:{ all -> 0x0119 }
            java.lang.String r1 = "MultiDex"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0116 }
            r4.<init>()     // Catch:{ all -> 0x0116 }
            java.lang.String r7 = "Blocking on lock "
            r4.append(r7)     // Catch:{ all -> 0x0116 }
            java.lang.String r7 = r8.getPath()     // Catch:{ all -> 0x0116 }
            r4.append(r7)     // Catch:{ all -> 0x0116 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0116 }
            android.util.Log.i(r1, r4)     // Catch:{ all -> 0x0116 }
            java.nio.channels.FileLock r12 = r11.lock()     // Catch:{ all -> 0x0116 }
            java.lang.String r1 = "MultiDex"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0114 }
            r4.<init>()     // Catch:{ all -> 0x0114 }
            java.lang.String r7 = r8.getPath()     // Catch:{ all -> 0x0114 }
            r4.append(r7)     // Catch:{ all -> 0x0114 }
            java.lang.String r7 = " locked"
            r4.append(r7)     // Catch:{ all -> 0x0114 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0114 }
            android.util.Log.i(r1, r4)     // Catch:{ all -> 0x0114 }
            if (r0 != 0) goto L_0x00ad
            r1 = r14
            r4 = r15
            boolean r0 = isModified(r14, r15, r5, r2)     // Catch:{ all -> 0x0114 }
            if (r0 != 0) goto L_0x00af
            java.util.List r0 = loadExistingExtractions(r14, r15, r16, r17)     // Catch:{ IOException -> 0x0094 }
        L_0x0092:
            r1 = r0
            goto L_0x00c6
        L_0x0094:
            r0 = move-exception
            r7 = r0
            java.lang.String r0 = "MultiDex"
            java.lang.String r13 = "Failed to reload existing extracted secondary dex files, falling back to fresh extraction"
            android.util.Log.w(r0, r13, r7)     // Catch:{ all -> 0x0114 }
            java.util.List r0 = performExtractions(r15, r16)     // Catch:{ all -> 0x0114 }
            long r3 = getTimeStamp(r15)     // Catch:{ all -> 0x0114 }
            r1 = r14
            r2 = r17
            r7 = r0
            putStoredApkInfo(r1, r2, r3, r5, r7)     // Catch:{ all -> 0x0114 }
            goto L_0x0092
        L_0x00ad:
            r1 = r14
            r4 = r15
        L_0x00af:
            java.lang.String r0 = "MultiDex"
            java.lang.String r7 = "Detected that extraction must be performed."
            android.util.Log.i(r0, r7)     // Catch:{ all -> 0x0114 }
            java.util.List r0 = performExtractions(r15, r16)     // Catch:{ all -> 0x0114 }
            long r3 = getTimeStamp(r15)     // Catch:{ all -> 0x0114 }
            r1 = r14
            r2 = r17
            r7 = r0
            putStoredApkInfo(r1, r2, r3, r5, r7)     // Catch:{ all -> 0x0114 }
            goto L_0x0092
        L_0x00c6:
            if (r12 == 0) goto L_0x00e8
            r12.release()     // Catch:{ IOException -> 0x00cc }
            goto L_0x00e8
        L_0x00cc:
            r0 = move-exception
            java.lang.String r2 = "MultiDex"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Failed to release lock on "
            r3.append(r4)
            java.lang.String r4 = r8.getPath()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
            goto L_0x00e9
        L_0x00e8:
            r0 = r10
        L_0x00e9:
            if (r11 == 0) goto L_0x00ee
            closeQuietly(r11)
        L_0x00ee:
            closeQuietly(r9)
            if (r0 != 0) goto L_0x0113
            java.lang.String r0 = "MultiDex"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "load found "
            r2.append(r3)
            int r3 = r1.size()
            r2.append(r3)
            java.lang.String r3 = " secondary dex files"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r0, r2)
            return r1
        L_0x0113:
            throw r0
        L_0x0114:
            r0 = move-exception
            goto L_0x011c
        L_0x0116:
            r0 = move-exception
            r12 = r10
            goto L_0x011c
        L_0x0119:
            r0 = move-exception
            r11 = r10
            r12 = r11
        L_0x011c:
            if (r12 == 0) goto L_0x013c
            r12.release()     // Catch:{ IOException -> 0x0122 }
            goto L_0x013c
        L_0x0122:
            java.lang.String r1 = "MultiDex"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to release lock on "
            r2.append(r3)
            java.lang.String r3 = r8.getPath()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r1, r2)
        L_0x013c:
            if (r11 == 0) goto L_0x0141
            closeQuietly(r11)
        L_0x0141:
            closeQuietly(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.multidex.MultiDexExtractor.load(android.content.Context, java.io.File, java.io.File, java.lang.String, boolean):java.util.List");
    }

    private static List<ExtractedDex> loadExistingExtractions(Context context, File file, File file2, String str) throws IOException {
        String str2 = str;
        Log.i(TAG, "loading existing secondary dex files");
        String str3 = file.getName() + EXTRACTED_NAME_EXT;
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        int i = multiDexPreferences.getInt(str2 + KEY_DEX_NUMBER, 1);
        ArrayList arrayList = new ArrayList(i + -1);
        int i2 = 2;
        while (i2 <= i) {
            ExtractedDex extractedDex = new ExtractedDex(file2, str3 + i2 + EXTRACTED_SUFFIX);
            if (extractedDex.isFile()) {
                extractedDex.crc = getZipCrc(extractedDex);
                long j = multiDexPreferences.getLong(str2 + KEY_DEX_CRC + i2, -1);
                long j2 = multiDexPreferences.getLong(str2 + KEY_DEX_TIME + i2, -1);
                long lastModified = extractedDex.lastModified();
                if (j2 == lastModified) {
                    String str4 = str3;
                    SharedPreferences sharedPreferences = multiDexPreferences;
                    if (j == extractedDex.crc) {
                        arrayList.add(extractedDex);
                        i2++;
                        str3 = str4;
                        multiDexPreferences = sharedPreferences;
                    }
                }
                throw new IOException("Invalid extracted dex: " + extractedDex + " (key \"" + str2 + "\"), expected modification time: " + j2 + ", modification time: " + lastModified + ", expected crc: " + j + ", file crc: " + extractedDex.crc);
            }
            throw new IOException("Missing extracted secondary dex file '" + extractedDex.getPath() + "'");
        }
        return arrayList;
    }

    private static boolean isModified(Context context, File file, long j, String str) {
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        if (multiDexPreferences.getLong(str + KEY_TIME_STAMP, -1) == getTimeStamp(file)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(KEY_CRC);
            return multiDexPreferences.getLong(sb.toString(), -1) != j;
        }
    }

    private static long getTimeStamp(File file) {
        long lastModified = file.lastModified();
        return lastModified == -1 ? lastModified - 1 : lastModified;
    }

    private static long getZipCrc(File file) throws IOException {
        long zipCrc = ZipUtil.getZipCrc(file);
        return zipCrc == -1 ? zipCrc - 1 : zipCrc;
    }

    private static List<ExtractedDex> performExtractions(File file, File file2) throws IOException {
        ExtractedDex extractedDex;
        boolean z;
        String str = file.getName() + EXTRACTED_NAME_EXT;
        prepareDexDir(file2, str);
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        int i = 2;
        try {
            ZipEntry entry = zipFile.getEntry(DEX_PREFIX + 2 + DEX_SUFFIX);
            while (entry != null) {
                extractedDex = new ExtractedDex(file2, str + i + EXTRACTED_SUFFIX);
                arrayList.add(extractedDex);
                Log.i(TAG, "Extraction is needed for file " + extractedDex);
                int i2 = 0;
                z = false;
                while (i2 < 3 && !z) {
                    i2++;
                    extract(zipFile, entry, extractedDex, str);
                    extractedDex.crc = getZipCrc(extractedDex);
                    z = true;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Extraction ");
                    sb.append(z ? "succeeded" : e.f729b);
                    sb.append(" - length ");
                    sb.append(extractedDex.getAbsolutePath());
                    sb.append(": ");
                    sb.append(extractedDex.length());
                    sb.append(" - crc: ");
                    sb.append(extractedDex.crc);
                    Log.i(TAG, sb.toString());
                    if (!z) {
                        extractedDex.delete();
                        if (extractedDex.exists()) {
                            Log.w(TAG, "Failed to delete corrupted secondary dex '" + extractedDex.getPath() + "'");
                        }
                    }
                }
                if (z) {
                    i++;
                    entry = zipFile.getEntry(DEX_PREFIX + i + DEX_SUFFIX);
                } else {
                    throw new IOException("Could not create zip file " + extractedDex.getAbsolutePath() + " for secondary dex (" + i + ")");
                }
            }
            try {
                zipFile.close();
            } catch (IOException e) {
                Log.w(TAG, "Failed to close resource", e);
            }
            return arrayList;
        } catch (IOException e2) {
            Log.w(TAG, "Failed to read crc from " + extractedDex.getAbsolutePath(), e2);
            z = false;
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (IOException e3) {
                Log.w(TAG, "Failed to close resource", e3);
            }
            throw th;
        }
    }

    private static void putStoredApkInfo(Context context, String str, long j, long j2, List<ExtractedDex> list) {
        SharedPreferences.Editor edit = getMultiDexPreferences(context).edit();
        edit.putLong(str + KEY_TIME_STAMP, j);
        edit.putLong(str + KEY_CRC, j2);
        edit.putInt(str + KEY_DEX_NUMBER, list.size() + 1);
        int i = 2;
        for (ExtractedDex next : list) {
            edit.putLong(str + KEY_DEX_CRC + i, next.crc);
            edit.putLong(str + KEY_DEX_TIME + i, next.lastModified());
            i++;
        }
        edit.commit();
    }

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private static void prepareDexDir(File file, final String str) {
        File[] listFiles = file.listFiles(new FileFilter() {
            public boolean accept(File file) {
                String name = file.getName();
                return !name.startsWith(str) && !name.equals(MultiDexExtractor.LOCK_FILENAME);
            }
        });
        if (listFiles == null) {
            Log.w(TAG, "Failed to list secondary dex dir content (" + file.getPath() + ").");
            return;
        }
        for (File file2 : listFiles) {
            Log.i(TAG, "Trying to delete old file " + file2.getPath() + " of size " + file2.length());
            if (!file2.delete()) {
                Log.w(TAG, "Failed to delete old file " + file2.getPath());
            } else {
                Log.i(TAG, "Deleted old file " + file2.getPath());
            }
        }
    }

    private static void extract(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        ZipOutputStream zipOutputStream;
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File createTempFile = File.createTempFile("tmp-" + str, EXTRACTED_SUFFIX, file.getParentFile());
        Log.i(TAG, "Extracting " + createTempFile.getPath());
        try {
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile)));
            ZipEntry zipEntry2 = new ZipEntry("classes.dex");
            zipEntry2.setTime(zipEntry.getTime());
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            if (createTempFile.setReadOnly()) {
                Log.i(TAG, "Renaming to " + file.getPath());
                if (createTempFile.renameTo(file)) {
                    closeQuietly(inputStream);
                    createTempFile.delete();
                    return;
                }
                throw new IOException("Failed to rename \"" + createTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
            }
            throw new IOException("Failed to mark readonly \"" + createTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
        } catch (Throwable th) {
            closeQuietly(inputStream);
            createTempFile.delete();
            throw th;
        }
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "Failed to close resource", e);
        }
    }
}
