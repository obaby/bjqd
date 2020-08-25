package cn.xports.baselib.util;

import android.util.Base64;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static void write2File(String str, String str2) throws IOException {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(str2);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readFromFile(java.lang.String r7) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r7)
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream
            r7.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0040, all -> 0x003d }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0040, all -> 0x003d }
            java.nio.channels.FileChannel r0 = r2.getChannel()     // Catch:{ IOException -> 0x003b }
            r3 = 8192(0x2000, float:1.14794E-41)
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)     // Catch:{ IOException -> 0x003b }
        L_0x001a:
            int r4 = r0.read(r3)     // Catch:{ IOException -> 0x003b }
            r5 = -1
            if (r4 == r5) goto L_0x0030
            r3.flip()     // Catch:{ IOException -> 0x003b }
            byte[] r5 = r3.array()     // Catch:{ IOException -> 0x003b }
            r6 = 0
            r7.write(r5, r6, r4)     // Catch:{ IOException -> 0x003b }
            r3.clear()     // Catch:{ IOException -> 0x003b }
            goto L_0x001a
        L_0x0030:
            java.lang.String r0 = r7.toString()     // Catch:{ IOException -> 0x003b }
            r2.close()
            r7.close()
            return r0
        L_0x003b:
            r0 = move-exception
            goto L_0x0042
        L_0x003d:
            r0 = move-exception
            r2 = r1
            goto L_0x004f
        L_0x0040:
            r0 = move-exception
            r2 = r1
        L_0x0042:
            r0.printStackTrace()     // Catch:{ all -> 0x004e }
            if (r2 == 0) goto L_0x004a
            r2.close()
        L_0x004a:
            r7.close()
            return r1
        L_0x004e:
            r0 = move-exception
        L_0x004f:
            if (r2 == 0) goto L_0x0054
            r2.close()
        L_0x0054:
            r7.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.baselib.util.FileUtil.readFromFile(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0039 A[SYNTHETIC, Splitter:B:29:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x003e A[Catch:{ IOException -> 0x004e }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0045 A[Catch:{ IOException -> 0x004e }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x004a A[Catch:{ IOException -> 0x004e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean writeFileToSDCard(okhttp3.ResponseBody r4, java.lang.String r5) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x004e }
            r1.<init>(r5)     // Catch:{ IOException -> 0x004e }
            r5 = 4096(0x1000, float:5.74E-42)
            r2 = 0
            byte[] r5 = new byte[r5]     // Catch:{ IOException -> 0x0042, all -> 0x0035 }
            java.io.InputStream r4 = r4.byteStream()     // Catch:{ IOException -> 0x0042, all -> 0x0035 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
        L_0x0014:
            int r1 = r4.read(r5)     // Catch:{ IOException -> 0x002f, all -> 0x002c }
            r2 = -1
            if (r1 != r2) goto L_0x0028
            r3.flush()     // Catch:{ IOException -> 0x002f, all -> 0x002c }
            r5 = 1
            if (r4 == 0) goto L_0x0024
            r4.close()     // Catch:{ IOException -> 0x004e }
        L_0x0024:
            r3.close()     // Catch:{ IOException -> 0x004e }
            return r5
        L_0x0028:
            r3.write(r5, r0, r1)     // Catch:{ IOException -> 0x002f, all -> 0x002c }
            goto L_0x0014
        L_0x002c:
            r5 = move-exception
            r2 = r3
            goto L_0x0037
        L_0x002f:
            r2 = r3
            goto L_0x0043
        L_0x0031:
            r5 = move-exception
            goto L_0x0037
        L_0x0033:
            goto L_0x0043
        L_0x0035:
            r5 = move-exception
            r4 = r2
        L_0x0037:
            if (r4 == 0) goto L_0x003c
            r4.close()     // Catch:{ IOException -> 0x004e }
        L_0x003c:
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ IOException -> 0x004e }
        L_0x0041:
            throw r5     // Catch:{ IOException -> 0x004e }
        L_0x0042:
            r4 = r2
        L_0x0043:
            if (r4 == 0) goto L_0x0048
            r4.close()     // Catch:{ IOException -> 0x004e }
        L_0x0048:
            if (r2 == 0) goto L_0x004d
            r2.close()     // Catch:{ IOException -> 0x004e }
        L_0x004d:
            return r0
        L_0x004e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.baselib.util.FileUtil.writeFileToSDCard(okhttp3.ResponseBody, java.lang.String):boolean");
    }

    public static String encodeFile2Base64(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[((int) file.length())];
        fileInputStream.read(bArr);
        fileInputStream.close();
        return Base64.encodeToString(bArr, 2).trim();
    }
}
