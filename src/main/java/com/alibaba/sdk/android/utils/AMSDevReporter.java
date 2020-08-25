package com.alibaba.sdk.android.utils;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AMSDevReporter {

    /* renamed from: a  reason: collision with root package name */
    private static Context f494a;

    /* renamed from: a  reason: collision with other field name */
    private static c f12a = new c("AMSDevReporter");

    /* renamed from: a  reason: collision with other field name */
    private static ConcurrentHashMap<AMSSdkTypeEnum, AMSReportStatusEnum> f13a = new ConcurrentHashMap<>();

    /* renamed from: a  reason: collision with other field name */
    private static final ExecutorService f14a = Executors.newSingleThreadExecutor(new a());

    /* renamed from: a  reason: collision with other field name */
    private static boolean f15a = false;

    public enum AMSReportStatusEnum {
        UNREPORTED,
        REPORTED
    }

    public enum AMSSdkTypeEnum {
        AMS_MAN("MAN"),
        AMS_HTTPDNS("HTTPDNS"),
        AMS_MPUSH("MPUSH"),
        AMS_MAC("MAC"),
        AMS_API("API"),
        AMS_HOTFIX("HOTFIX"),
        AMS_FEEDBACK("FEEDBACK"),
        AMS_IM("IM");
        
        private String description;

        private AMSSdkTypeEnum(String str) {
            this.description = str;
        }

        public String toString() {
            return this.description;
        }
    }

    public enum AMSSdkExtInfoKeyEnum {
        AMS_EXTINFO_KEY_VERSION("SdkVersion"),
        AMS_EXTINFO_KEY_PACKAGE("PackageName");
        
        private String description;

        private AMSSdkExtInfoKeyEnum(String str) {
            this.description = str;
        }

        public String toString() {
            return this.description;
        }
    }

    static {
        for (AMSSdkTypeEnum put : AMSSdkTypeEnum.values()) {
            f13a.put(put, AMSReportStatusEnum.UNREPORTED);
        }
    }

    public static void setLogEnabled(boolean z) {
        f12a.setLogEnabled(z);
    }

    public static AMSReportStatusEnum getReportStatus(AMSSdkTypeEnum aMSSdkTypeEnum) {
        return f13a.get(aMSSdkTypeEnum);
    }

    public static void asyncReport(Context context, AMSSdkTypeEnum aMSSdkTypeEnum) {
        asyncReport(context, aMSSdkTypeEnum, (Map<String, Object>) null);
    }

    public static void asyncReport(Context context, final AMSSdkTypeEnum aMSSdkTypeEnum, final Map<String, Object> map) {
        if (context == null) {
            f12a.c("Context is null, return.");
            return;
        }
        f494a = context;
        c cVar = f12a;
        cVar.b("Add [" + aMSSdkTypeEnum.toString() + "] to report queue.");
        f15a = false;
        f14a.execute(new Runnable() {
            public void run() {
                if (AMSDevReporter.a()) {
                    AMSDevReporter.a().c("Unable to execute remain task in queue, return.");
                    return;
                }
                c a2 = AMSDevReporter.a();
                a2.b("Get [" + aMSSdkTypeEnum.toString() + "] from report queue.");
                AMSDevReporter.a(aMSSdkTypeEnum, (Map<String, Object>) map);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void a(AMSSdkTypeEnum aMSSdkTypeEnum, Map<String, Object> map) {
        String aMSSdkTypeEnum2 = aMSSdkTypeEnum.toString();
        if (f13a.get(aMSSdkTypeEnum) != AMSReportStatusEnum.UNREPORTED) {
            c cVar = f12a;
            cVar.b("[" + aMSSdkTypeEnum2 + "] already reported, return.");
            return;
        }
        int i = 0;
        int i2 = 5;
        while (true) {
            c cVar2 = f12a;
            StringBuilder sb = new StringBuilder();
            sb.append("Report [");
            sb.append(aMSSdkTypeEnum2);
            sb.append("], times: [");
            i++;
            sb.append(i);
            sb.append("].");
            cVar2.b(sb.toString());
            if (!a(aMSSdkTypeEnum, map)) {
                if (i > 10) {
                    c cVar3 = f12a;
                    cVar3.c("Report [" + aMSSdkTypeEnum2 + "] stat failed, exceed max retry times, return.");
                    f13a.put(aMSSdkTypeEnum, AMSReportStatusEnum.UNREPORTED);
                    f15a = true;
                    break;
                }
                c cVar4 = f12a;
                cVar4.b("Report [" + aMSSdkTypeEnum2 + "] failed, wait for [" + i2 + "] seconds.");
                d.a((double) i2);
                i2 *= 2;
                if (i2 >= 60) {
                    i2 = 60;
                }
            } else {
                c cVar5 = f12a;
                cVar5.b("Report [" + aMSSdkTypeEnum2 + "] stat success.");
                f13a.put(aMSSdkTypeEnum, AMSReportStatusEnum.REPORTED);
                break;
            }
        }
        if (f15a) {
            c cVar6 = f12a;
            cVar6.c("Report [" + aMSSdkTypeEnum2 + "] failed, clear remain report in queue.");
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01c6 A[SYNTHETIC, Splitter:B:66:0x01c6] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01ce A[Catch:{ IOException -> 0x01ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01e3 A[SYNTHETIC, Splitter:B:80:0x01e3] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01eb A[Catch:{ IOException -> 0x01e7 }] */
    /* renamed from: a  reason: collision with other method in class */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m11a(com.alibaba.sdk.android.utils.AMSDevReporter.AMSSdkTypeEnum r8, java.util.Map<java.lang.String, java.lang.Object> r9) {
        /*
            r0 = 0
            r1 = 0
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r3 = 14
            if (r2 < r3) goto L_0x000e
            r2 = 40965(0xa005, float:5.7404E-41)
            android.net.TrafficStats.setThreadStatsTag(r2)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
        L_0x000e:
            android.content.Context r2 = f494a     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r2 = com.ut.device.UTDevice.getUtdid(r2)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            com.alibaba.sdk.android.utils.c r3 = f12a     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r4.<init>()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r5 = "stat: "
            r4.append(r5)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r4.append(r2)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r3.b(r4)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r8 = a(r8, r2, r9)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r9.<init>()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r2 = "23356390Raw"
            r9.append(r2)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r2 = com.alibaba.sdk.android.utils.d.a((java.lang.String) r8)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r9.append(r2)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r2.<init>()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r3 = "16594f72217bece5a457b4803a48f2da"
            r2.append(r3)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r9 = com.alibaba.sdk.android.utils.d.a((java.lang.String) r9)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r2.append(r9)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r9 = "16594f72217bece5a457b4803a48f2da"
            r2.append(r9)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r9 = r2.toString()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r9 = com.alibaba.sdk.android.utils.d.b(r9)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r2.<init>()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r3 = "http://adash.man.aliyuncs.com:80/man/api?ak=23356390&s="
            r2.append(r3)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r2.append(r9)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.lang.String r9 = r2.toString()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r2.<init>(r9)     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.net.URLConnection r9 = r2.openConnection()     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ Exception -> 0x01b7, all -> 0x01b3 }
            r2 = 1
            r9.setDoOutput(r2)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r9.setUseCaches(r0)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r3 = 15000(0x3a98, float:2.102E-41)
            r9.setConnectTimeout(r3)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r3.<init>()     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r4 = "==="
            r3.append(r4)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r3.append(r4)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r4 = "==="
            r3.append(r4)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r4 = "Content-Type"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r5.<init>()     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r6 = "multipart/form-data; boundary="
            r5.append(r6)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r5.append(r3)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r9.setRequestProperty(r4, r5)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r4.<init>()     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r5 = "--"
            r4.append(r5)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r4.append(r3)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r5 = "\r\nContent-Disposition: form-data; name=\""
            r4.append(r5)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r5 = "Raw"
            r4.append(r5)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r5 = "\"\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n"
            r4.append(r5)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r4.append(r8)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r8 = "\r\n--"
            r4.append(r8)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            r4.append(r3)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r8 = "--\r\n"
            r4.append(r8)     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.lang.String r8 = r4.toString()     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            java.io.OutputStream r3 = r9.getOutputStream()     // Catch:{ Exception -> 0x01ae, all -> 0x01ab }
            byte[] r8 = r8.getBytes()     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            r3.write(r8)     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            int r8 = r9.getResponseCode()     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r8 != r4) goto L_0x017c
            java.io.DataInputStream r8 = new java.io.DataInputStream     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            java.io.InputStream r4 = r9.getInputStream()     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            r8.<init>(r4)     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r1.<init>()     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x0176, all -> 0x016f }
        L_0x010e:
            int r5 = r8.read(r4)     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r6 = -1
            if (r5 == r6) goto L_0x011e
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r6.<init>(r4, r0, r5)     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r1.append(r6)     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            goto L_0x010e
        L_0x011e:
            com.alibaba.sdk.android.utils.c r4 = f12a     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r5.<init>()     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            java.lang.String r6 = "Get MAN response: "
            r5.append(r6)     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            java.lang.String r6 = r1.toString()     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r5.append(r6)     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r4.a((java.lang.String) r5)     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0168 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x0168 }
            r4.<init>(r1)     // Catch:{ JSONException -> 0x0168 }
            java.lang.String r1 = "success"
            java.lang.Object r1 = r4.get(r1)     // Catch:{ JSONException -> 0x0168 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ JSONException -> 0x0168 }
            java.lang.String r4 = "success"
            boolean r1 = r1.equals(r4)     // Catch:{ JSONException -> 0x0168 }
            if (r1 == 0) goto L_0x0193
            if (r9 == 0) goto L_0x0156
            r9.disconnect()
        L_0x0156:
            if (r3 == 0) goto L_0x015e
            r3.close()     // Catch:{ IOException -> 0x015c }
            goto L_0x015e
        L_0x015c:
            r8 = move-exception
            goto L_0x0162
        L_0x015e:
            r8.close()     // Catch:{ IOException -> 0x015c }
            goto L_0x0167
        L_0x0162:
            com.alibaba.sdk.android.utils.c r9 = f12a
            r9.a((java.lang.Throwable) r8)
        L_0x0167:
            return r2
        L_0x0168:
            r1 = move-exception
            com.alibaba.sdk.android.utils.c r2 = f12a     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            r2.a((java.lang.Throwable) r1)     // Catch:{ Exception -> 0x0176, all -> 0x016f }
            goto L_0x0193
        L_0x016f:
            r0 = move-exception
            r1 = r3
            r7 = r0
            r0 = r8
            r8 = r7
            goto L_0x01dc
        L_0x0176:
            r1 = move-exception
            r7 = r9
            r9 = r8
            r8 = r1
            r1 = r7
            goto L_0x01ba
        L_0x017c:
            com.alibaba.sdk.android.utils.c r2 = f12a     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            r4.<init>()     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            java.lang.String r5 = "MAN API error, response code: "
            r4.append(r5)     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            r4.append(r8)     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            java.lang.String r8 = r4.toString()     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            r2.c(r8)     // Catch:{ Exception -> 0x01a6, all -> 0x01a3 }
            r8 = r1
        L_0x0193:
            if (r9 == 0) goto L_0x0198
            r9.disconnect()
        L_0x0198:
            if (r3 == 0) goto L_0x019d
            r3.close()     // Catch:{ IOException -> 0x01ca }
        L_0x019d:
            if (r8 == 0) goto L_0x01d7
            r8.close()     // Catch:{ IOException -> 0x01ca }
            goto L_0x01d7
        L_0x01a3:
            r8 = move-exception
            r0 = r1
            goto L_0x01db
        L_0x01a6:
            r8 = move-exception
            r7 = r1
            r1 = r9
            r9 = r7
            goto L_0x01ba
        L_0x01ab:
            r8 = move-exception
            r0 = r1
            goto L_0x01dc
        L_0x01ae:
            r8 = move-exception
            r3 = r1
            r1 = r9
            r9 = r3
            goto L_0x01ba
        L_0x01b3:
            r8 = move-exception
            r9 = r1
            r0 = r9
            goto L_0x01dc
        L_0x01b7:
            r8 = move-exception
            r9 = r1
            r3 = r9
        L_0x01ba:
            com.alibaba.sdk.android.utils.c r2 = f12a     // Catch:{ all -> 0x01d8 }
            r2.a((java.lang.Throwable) r8)     // Catch:{ all -> 0x01d8 }
            if (r1 == 0) goto L_0x01c4
            r1.disconnect()
        L_0x01c4:
            if (r3 == 0) goto L_0x01cc
            r3.close()     // Catch:{ IOException -> 0x01ca }
            goto L_0x01cc
        L_0x01ca:
            r8 = move-exception
            goto L_0x01d2
        L_0x01cc:
            if (r9 == 0) goto L_0x01d7
            r9.close()     // Catch:{ IOException -> 0x01ca }
            goto L_0x01d7
        L_0x01d2:
            com.alibaba.sdk.android.utils.c r9 = f12a
            r9.a((java.lang.Throwable) r8)
        L_0x01d7:
            return r0
        L_0x01d8:
            r8 = move-exception
            r0 = r9
            r9 = r1
        L_0x01db:
            r1 = r3
        L_0x01dc:
            if (r9 == 0) goto L_0x01e1
            r9.disconnect()
        L_0x01e1:
            if (r1 == 0) goto L_0x01e9
            r1.close()     // Catch:{ IOException -> 0x01e7 }
            goto L_0x01e9
        L_0x01e7:
            r9 = move-exception
            goto L_0x01ef
        L_0x01e9:
            if (r0 == 0) goto L_0x01f4
            r0.close()     // Catch:{ IOException -> 0x01e7 }
            goto L_0x01f4
        L_0x01ef:
            com.alibaba.sdk.android.utils.c r0 = f12a
            r0.a((java.lang.Throwable) r9)
        L_0x01f4:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.utils.AMSDevReporter.m11a(com.alibaba.sdk.android.utils.AMSDevReporter$AMSSdkTypeEnum, java.util.Map):boolean");
    }

    private static String a(AMSSdkTypeEnum aMSSdkTypeEnum, String str, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(aMSSdkTypeEnum);
        sb.append("-");
        sb.append(str);
        if (map != null) {
            String str2 = (String) map.get(AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString());
            if (!d.a(str2)) {
                sb.append("-");
                sb.append(str2);
            }
            String str3 = (String) map.get(AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_PACKAGE.toString());
            if (!d.a(str3)) {
                sb.append("-");
                sb.append(str3);
            }
        }
        return sb.toString();
    }
}
