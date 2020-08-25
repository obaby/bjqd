package anet.channel.util;

import android.content.Context;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.statist.StrategyStatObject;
import java.io.File;
import java.io.Serializable;

/* compiled from: Taobao */
public class SerializeHelper {
    private static final String TAG = "awcn.SerializeHelper";
    private static File cacheDir;

    public static File getCacheFiles(String str) {
        Context context;
        if (cacheDir == null && (context = GlobalAppRuntimeInfo.getContext()) != null) {
            cacheDir = context.getCacheDir();
        }
        return new File(cacheDir, str);
    }

    public static synchronized void persist(Serializable serializable, File file) {
        synchronized (SerializeHelper.class) {
            persist(serializable, file, (StrategyStatObject) null);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:48|(0)|52|53) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004d, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        r13 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
        r7 = null;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0069, code lost:
        r15.appendErrorTrace("SerializeHelper.persist()", r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x00e2 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0069 A[Catch:{ all -> 0x00dc }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070 A[SYNTHETIC, Splitter:B:31:0x0070] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00df A[SYNTHETIC, Splitter:B:50:0x00df] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:52:0x00e2=Splitter:B:52:0x00e2, B:35:0x0074=Splitter:B:35:0x0074} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void persist(java.io.Serializable r13, java.io.File r14, anet.channel.statist.StrategyStatObject r15) {
        /*
            java.lang.Class<anet.channel.util.SerializeHelper> r0 = anet.channel.util.SerializeHelper.class
            monitor-enter(r0)
            r1 = 0
            r2 = 0
            if (r13 == 0) goto L_0x00e3
            if (r14 != 0) goto L_0x000b
            goto L_0x00e3
        L_0x000b:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00ee }
            r5 = 2
            r6 = 1
            java.util.UUID r7 = java.util.UUID.randomUUID()     // Catch:{ Exception -> 0x0051, all -> 0x004d }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0051, all -> 0x004d }
            java.lang.String r8 = "-"
            java.lang.String r9 = ""
            java.lang.String r7 = r7.replace(r8, r9)     // Catch:{ Exception -> 0x0051, all -> 0x004d }
            java.io.File r7 = getCacheFiles(r7)     // Catch:{ Exception -> 0x0051, all -> 0x004d }
            r7.createNewFile()     // Catch:{ Exception -> 0x004a, all -> 0x004d }
            r7.setReadable(r6)     // Catch:{ Exception -> 0x004a, all -> 0x004d }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x004a, all -> 0x004d }
            r8.<init>(r7)     // Catch:{ Exception -> 0x004a, all -> 0x004d }
            java.io.ObjectOutputStream r9 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x0048 }
            java.io.BufferedOutputStream r10 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x0048 }
            r10.<init>(r8)     // Catch:{ Exception -> 0x0048 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0048 }
            r9.writeObject(r13)     // Catch:{ Exception -> 0x0048 }
            r9.flush()     // Catch:{ Exception -> 0x0048 }
            r9.close()     // Catch:{ Exception -> 0x0048 }
            r8.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0046:
            r13 = 1
            goto L_0x0074
        L_0x0048:
            r13 = move-exception
            goto L_0x0054
        L_0x004a:
            r13 = move-exception
            r8 = r2
            goto L_0x0054
        L_0x004d:
            r13 = move-exception
            r8 = r2
            goto L_0x00dd
        L_0x0051:
            r13 = move-exception
            r7 = r2
            r8 = r7
        L_0x0054:
            java.lang.String r9 = "awcn.SerializeHelper"
            java.lang.String r10 = "persist fail. "
            java.lang.Object[] r11 = new java.lang.Object[r5]     // Catch:{ all -> 0x00dc }
            java.lang.String r12 = "file"
            r11[r1] = r12     // Catch:{ all -> 0x00dc }
            java.lang.String r12 = r14.getName()     // Catch:{ all -> 0x00dc }
            r11[r6] = r12     // Catch:{ all -> 0x00dc }
            anet.channel.util.ALog.e(r9, r10, r2, r13, r11)     // Catch:{ all -> 0x00dc }
            if (r15 == 0) goto L_0x006e
            java.lang.String r9 = "SerializeHelper.persist()"
            r15.appendErrorTrace(r9, r13)     // Catch:{ all -> 0x00dc }
        L_0x006e:
            if (r8 == 0) goto L_0x0073
            r8.close()     // Catch:{ IOException -> 0x0073 }
        L_0x0073:
            r13 = 0
        L_0x0074:
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00ee }
            r10 = 0
            long r8 = r8 - r3
            if (r15 == 0) goto L_0x008c
            java.lang.String r3 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x00ee }
            r15.writeTempFilePath = r3     // Catch:{ all -> 0x00ee }
            java.lang.String r3 = java.lang.String.valueOf(r14)     // Catch:{ all -> 0x00ee }
            r15.writeStrategyFilePath = r3     // Catch:{ all -> 0x00ee }
            r15.isTempWriteSucceed = r13     // Catch:{ all -> 0x00ee }
            r15.writeCostTime = r8     // Catch:{ all -> 0x00ee }
        L_0x008c:
            if (r13 == 0) goto L_0x00da
            boolean r13 = r7.renameTo(r14)     // Catch:{ all -> 0x00ee }
            if (r13 == 0) goto L_0x00c4
            java.lang.String r3 = "awcn.SerializeHelper"
            java.lang.String r4 = "persist end."
            r7 = 6
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = "file"
            r7[r1] = r10     // Catch:{ all -> 0x00ee }
            java.io.File r1 = r14.getAbsoluteFile()     // Catch:{ all -> 0x00ee }
            r7[r6] = r1     // Catch:{ all -> 0x00ee }
            java.lang.String r1 = "size"
            r7[r5] = r1     // Catch:{ all -> 0x00ee }
            r1 = 3
            long r5 = r14.length()     // Catch:{ all -> 0x00ee }
            java.lang.Long r14 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x00ee }
            r7[r1] = r14     // Catch:{ all -> 0x00ee }
            r14 = 4
            java.lang.String r1 = "cost"
            r7[r14] = r1     // Catch:{ all -> 0x00ee }
            r14 = 5
            java.lang.Long r1 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x00ee }
            r7[r14] = r1     // Catch:{ all -> 0x00ee }
            anet.channel.util.ALog.i(r3, r4, r2, r7)     // Catch:{ all -> 0x00ee }
            goto L_0x00cd
        L_0x00c4:
            java.lang.String r14 = "awcn.SerializeHelper"
            java.lang.String r3 = "rename failed."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00ee }
            anet.channel.util.ALog.e(r14, r3, r2, r1)     // Catch:{ all -> 0x00ee }
        L_0x00cd:
            if (r15 == 0) goto L_0x00da
            r15.isRenameSucceed = r13     // Catch:{ all -> 0x00ee }
            r15.isSucceed = r13     // Catch:{ all -> 0x00ee }
            anet.channel.appmonitor.IAppMonitor r13 = anet.channel.appmonitor.AppMonitor.getInstance()     // Catch:{ all -> 0x00ee }
            r13.commitStat(r15)     // Catch:{ all -> 0x00ee }
        L_0x00da:
            monitor-exit(r0)
            return
        L_0x00dc:
            r13 = move-exception
        L_0x00dd:
            if (r8 == 0) goto L_0x00e2
            r8.close()     // Catch:{ IOException -> 0x00e2 }
        L_0x00e2:
            throw r13     // Catch:{ all -> 0x00ee }
        L_0x00e3:
            java.lang.String r13 = "awcn.SerializeHelper"
            java.lang.String r14 = "persist fail. Invalid parameter"
            java.lang.Object[] r15 = new java.lang.Object[r1]     // Catch:{ all -> 0x00ee }
            anet.channel.util.ALog.e(r13, r14, r2, r15)     // Catch:{ all -> 0x00ee }
            monitor-exit(r0)
            return
        L_0x00ee:
            r13 = move-exception
            monitor-exit(r0)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.SerializeHelper.persist(java.io.Serializable, java.io.File, anet.channel.statist.StrategyStatObject):void");
    }

    public static synchronized <T> T restore(File file) {
        T restore;
        synchronized (SerializeHelper.class) {
            restore = restore(file, (StrategyStatObject) null);
        }
        return restore;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:45|(0)|49|50) */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b2, code lost:
        if (r4 != null) goto L_0x008d;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00bd */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a2 A[Catch:{ all -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ad A[Catch:{ all -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ba A[SYNTHETIC, Splitter:B:47:0x00ba] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized <T> T restore(java.io.File r14, anet.channel.statist.StrategyStatObject r15) {
        /*
            java.lang.Class<anet.channel.util.SerializeHelper> r0 = anet.channel.util.SerializeHelper.class
            monitor-enter(r0)
            if (r15 == 0) goto L_0x000f
            java.lang.String r1 = java.lang.String.valueOf(r14)     // Catch:{ all -> 0x000c }
            r15.readStrategyFilePath = r1     // Catch:{ all -> 0x000c }
            goto L_0x000f
        L_0x000c:
            r14 = move-exception
            goto L_0x00be
        L_0x000f:
            r1 = 0
            r2 = 3
            r3 = 0
            boolean r4 = r14.exists()     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            r5 = 2
            r6 = 1
            if (r4 != 0) goto L_0x0035
            boolean r4 = anet.channel.util.ALog.isPrintLog(r2)     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            if (r4 == 0) goto L_0x0033
            java.lang.String r4 = "awcn.SerializeHelper"
            java.lang.String r7 = "file not exist."
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            java.lang.String r8 = "file"
            r5[r1] = r8     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            java.lang.String r14 = r14.getName()     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            r5[r6] = r14     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            anet.channel.util.ALog.w(r4, r7, r3, r5)     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        L_0x0033:
            monitor-exit(r0)
            return r3
        L_0x0035:
            if (r15 == 0) goto L_0x0039
            r15.isFileExists = r6     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        L_0x0039:
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            r4.<init>(r14)     // Catch:{ Throwable -> 0x0099, all -> 0x0096 }
            java.io.ObjectInputStream r9 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0093 }
            java.io.BufferedInputStream r10 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0093 }
            r10.<init>(r4)     // Catch:{ Throwable -> 0x0093 }
            r9.<init>(r10)     // Catch:{ Throwable -> 0x0093 }
            java.lang.Object r10 = r9.readObject()     // Catch:{ Throwable -> 0x0093 }
            r9.close()     // Catch:{ Throwable -> 0x0091 }
            long r11 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0091 }
            r9 = 0
            long r11 = r11 - r7
            if (r15 == 0) goto L_0x005f
            r15.isReadObjectSucceed = r6     // Catch:{ Throwable -> 0x0091 }
            r15.readCostTime = r11     // Catch:{ Throwable -> 0x0091 }
        L_0x005f:
            java.lang.String r7 = "awcn.SerializeHelper"
            java.lang.String r8 = "restore end."
            r9 = 6
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0091 }
            java.lang.String r13 = "file"
            r9[r1] = r13     // Catch:{ Throwable -> 0x0091 }
            java.io.File r13 = r14.getAbsoluteFile()     // Catch:{ Throwable -> 0x0091 }
            r9[r6] = r13     // Catch:{ Throwable -> 0x0091 }
            java.lang.String r6 = "size"
            r9[r5] = r6     // Catch:{ Throwable -> 0x0091 }
            long r5 = r14.length()     // Catch:{ Throwable -> 0x0091 }
            java.lang.Long r14 = java.lang.Long.valueOf(r5)     // Catch:{ Throwable -> 0x0091 }
            r9[r2] = r14     // Catch:{ Throwable -> 0x0091 }
            r14 = 4
            java.lang.String r5 = "cost"
            r9[r14] = r5     // Catch:{ Throwable -> 0x0091 }
            r14 = 5
            java.lang.Long r5 = java.lang.Long.valueOf(r11)     // Catch:{ Throwable -> 0x0091 }
            r9[r14] = r5     // Catch:{ Throwable -> 0x0091 }
            anet.channel.util.ALog.i(r7, r8, r3, r9)     // Catch:{ Throwable -> 0x0091 }
        L_0x008d:
            r4.close()     // Catch:{ IOException -> 0x00b5 }
            goto L_0x00b5
        L_0x0091:
            r14 = move-exception
            goto L_0x009c
        L_0x0093:
            r14 = move-exception
            r10 = r3
            goto L_0x009c
        L_0x0096:
            r14 = move-exception
            r4 = r3
            goto L_0x00b8
        L_0x0099:
            r14 = move-exception
            r4 = r3
            r10 = r4
        L_0x009c:
            boolean r2 = anet.channel.util.ALog.isPrintLog(r2)     // Catch:{ all -> 0x00b7 }
            if (r2 == 0) goto L_0x00ab
            java.lang.String r2 = "awcn.SerializeHelper"
            java.lang.String r5 = "restore file fail."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00b7 }
            anet.channel.util.ALog.w(r2, r5, r3, r14, r1)     // Catch:{ all -> 0x00b7 }
        L_0x00ab:
            if (r15 == 0) goto L_0x00b2
            java.lang.String r1 = "SerializeHelper.restore()"
            r15.appendErrorTrace(r1, r14)     // Catch:{ all -> 0x00b7 }
        L_0x00b2:
            if (r4 == 0) goto L_0x00b5
            goto L_0x008d
        L_0x00b5:
            monitor-exit(r0)
            return r10
        L_0x00b7:
            r14 = move-exception
        L_0x00b8:
            if (r4 == 0) goto L_0x00bd
            r4.close()     // Catch:{ IOException -> 0x00bd }
        L_0x00bd:
            throw r14     // Catch:{ all -> 0x000c }
        L_0x00be:
            monitor-exit(r0)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.SerializeHelper.restore(java.io.File, anet.channel.statist.StrategyStatObject):java.lang.Object");
    }
}
