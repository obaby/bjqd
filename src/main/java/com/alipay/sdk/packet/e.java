package com.alipay.sdk.packet;

import com.alipay.sdk.cons.a;
import com.alipay.sdk.encrypt.c;
import com.alipay.sdk.encrypt.d;
import com.alipay.sdk.util.l;
import java.util.Locale;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    private boolean f702a;

    /* renamed from: b  reason: collision with root package name */
    private String f703b = l.e();

    public e(boolean z) {
        this.f702a = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        r4 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0055 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x0009] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d A[SYNTHETIC, Splitter:B:26:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0065 A[SYNTHETIC, Splitter:B:33:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x006b A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.alipay.sdk.packet.b a(com.alipay.sdk.packet.c r6) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0061, all -> 0x0059 }
            byte[] r2 = r6.f698b     // Catch:{ Exception -> 0x0061, all -> 0x0059 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0061, all -> 0x0059 }
            r2 = 5
            byte[] r3 = new byte[r2]     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            r1.read(r3)     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            int r3 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            r1.read(r3)     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            r1.read(r2)     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            int r2 = java.lang.Integer.parseInt(r3)     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            if (r2 <= 0) goto L_0x004e
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            r1.read(r2)     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            boolean r3 = r5.f702a     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            if (r3 == 0) goto L_0x0040
            java.lang.String r3 = r5.f703b     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            byte[] r2 = com.alipay.sdk.encrypt.e.b((java.lang.String) r3, (byte[]) r2)     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
        L_0x0040:
            boolean r6 = r6.f697a     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            if (r6 == 0) goto L_0x0048
            byte[] r2 = com.alipay.sdk.encrypt.c.b(r2)     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
        L_0x0048:
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            r6.<init>(r2)     // Catch:{ Exception -> 0x0053, all -> 0x0055 }
            goto L_0x004f
        L_0x004e:
            r6 = r0
        L_0x004f:
            r1.close()     // Catch:{ Exception -> 0x0069 }
            goto L_0x0069
        L_0x0053:
            goto L_0x0063
        L_0x0055:
            r6 = move-exception
            goto L_0x005b
        L_0x0057:
            r4 = r0
            goto L_0x0063
        L_0x0059:
            r6 = move-exception
            r1 = r0
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ Exception -> 0x0060 }
        L_0x0060:
            throw r6
        L_0x0061:
            r1 = r0
            r4 = r1
        L_0x0063:
            if (r1 == 0) goto L_0x0068
            r1.close()     // Catch:{ Exception -> 0x0068 }
        L_0x0068:
            r6 = r0
        L_0x0069:
            if (r4 != 0) goto L_0x006e
            if (r6 != 0) goto L_0x006e
            return r0
        L_0x006e:
            com.alipay.sdk.packet.b r0 = new com.alipay.sdk.packet.b
            r0.<init>(r4, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.e.a(com.alipay.sdk.packet.c):com.alipay.sdk.packet.b");
    }

    private static byte[] a(String str, String str2) {
        return d.a(str, str2);
    }

    private static byte[] a(String str, byte[] bArr) {
        return com.alipay.sdk.encrypt.e.a(str, bArr);
    }

    private static byte[] b(String str, byte[] bArr) {
        return com.alipay.sdk.encrypt.e.b(str, bArr);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:3|4|5|6|7|(3:11|9|8)|46|12|13|14|15|16|49) */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055 A[SYNTHETIC, Splitter:B:26:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005c A[SYNTHETIC, Splitter:B:30:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0064 A[SYNTHETIC, Splitter:B:37:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x006b A[SYNTHETIC, Splitter:B:41:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(byte[]... r9) {
        /*
            int r0 = r9.length
            r1 = 0
            if (r0 != 0) goto L_0x0005
            return r1
        L_0x0005:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0060, all -> 0x0050 }
            r0.<init>()     // Catch:{ Exception -> 0x0060, all -> 0x0050 }
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r2.<init>(r0)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r3 = 0
            r4 = 0
        L_0x0011:
            int r5 = r9.length     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            if (r4 >= r5) goto L_0x0039
            r5 = r9[r4]     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            int r5 = r5.length     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.lang.String r7 = "%05d"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r8[r3] = r5     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.lang.String r5 = java.lang.String.format(r6, r7, r8)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            byte[] r5 = r5.getBytes()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r2.write(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r5 = r9[r4]     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r2.write(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0039:
            r2.flush()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            byte[] r9 = r0.toByteArray()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r0.close()     // Catch:{ Exception -> 0x0043 }
        L_0x0043:
            r2.close()     // Catch:{ Exception -> 0x006f }
            goto L_0x006f
        L_0x0047:
            r9 = move-exception
            goto L_0x0053
        L_0x0049:
            goto L_0x0062
        L_0x004b:
            r9 = move-exception
            r2 = r1
            goto L_0x0053
        L_0x004e:
            r2 = r1
            goto L_0x0062
        L_0x0050:
            r9 = move-exception
            r0 = r1
            r2 = r0
        L_0x0053:
            if (r0 == 0) goto L_0x005a
            r0.close()     // Catch:{ Exception -> 0x0059 }
            goto L_0x005a
        L_0x0059:
        L_0x005a:
            if (r2 == 0) goto L_0x005f
            r2.close()     // Catch:{ Exception -> 0x005f }
        L_0x005f:
            throw r9
        L_0x0060:
            r0 = r1
            r2 = r0
        L_0x0062:
            if (r0 == 0) goto L_0x0069
            r0.close()     // Catch:{ Exception -> 0x0068 }
            goto L_0x0069
        L_0x0068:
        L_0x0069:
            if (r2 == 0) goto L_0x006e
            r2.close()     // Catch:{ Exception -> 0x006e }
        L_0x006e:
            r9 = r1
        L_0x006f:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.e.a(byte[][]):byte[]");
    }

    private static String a(int i) {
        return String.format(Locale.getDefault(), "%05d", new Object[]{Integer.valueOf(i)});
    }

    private static int a(String str) {
        return Integer.parseInt(str);
    }

    public final c a(b bVar, boolean z) {
        byte[] bArr;
        byte[] bytes = bVar.f695a.getBytes();
        byte[] bytes2 = bVar.f696b.getBytes();
        if (z) {
            try {
                bytes2 = c.a(bytes2);
            } catch (Exception unused) {
                z = false;
            }
        }
        if (this.f702a) {
            bArr = a(bytes, d.a(this.f703b, a.f666c), com.alipay.sdk.encrypt.e.a(this.f703b, bytes2));
        } else {
            bArr = a(bytes, bytes2);
        }
        return new c(z, bArr);
    }
}
