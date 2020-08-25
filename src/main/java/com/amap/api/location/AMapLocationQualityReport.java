package com.amap.api.location;

import com.amap.api.location.AMapLocationClientOption;

public class AMapLocationQualityReport implements Cloneable {
    public static final int GPS_STATUS_MODE_SAVING = 3;
    public static final int GPS_STATUS_NOGPSPERMISSION = 4;
    public static final int GPS_STATUS_NOGPSPROVIDER = 1;
    public static final int GPS_STATUS_OFF = 2;
    public static final int GPS_STATUS_OK = 0;

    /* renamed from: a  reason: collision with root package name */
    AMapLocationClientOption.AMapLocationMode f814a = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;

    /* renamed from: b  reason: collision with root package name */
    private boolean f815b = false;

    /* renamed from: c  reason: collision with root package name */
    private int f816c = 2;
    private int d = 0;
    private String e = "UNKNOWN";
    private long f = 0;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0027, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0028, code lost:
        com.loc.di.a(r1, "AMapLocationQualityReport", "clone");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0008 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.amap.api.location.AMapLocationQualityReport clone() {
        /*
            r4 = this;
            com.amap.api.location.AMapLocationQualityReport r0 = new com.amap.api.location.AMapLocationQualityReport
            r0.<init>()
            super.clone()     // Catch:{ Throwable -> 0x0008 }
        L_0x0008:
            int r1 = r4.f816c     // Catch:{ Throwable -> 0x0027 }
            r0.setGpsStatus(r1)     // Catch:{ Throwable -> 0x0027 }
            int r1 = r4.d     // Catch:{ Throwable -> 0x0027 }
            r0.setGPSSatellites(r1)     // Catch:{ Throwable -> 0x0027 }
            boolean r1 = r4.f815b     // Catch:{ Throwable -> 0x0027 }
            r0.setWifiAble(r1)     // Catch:{ Throwable -> 0x0027 }
            long r1 = r4.f     // Catch:{ Throwable -> 0x0027 }
            r0.setNetUseTime(r1)     // Catch:{ Throwable -> 0x0027 }
            java.lang.String r1 = r4.e     // Catch:{ Throwable -> 0x0027 }
            r0.setNetworkType(r1)     // Catch:{ Throwable -> 0x0027 }
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r4.f814a     // Catch:{ Throwable -> 0x0027 }
            r0.setLocationMode(r1)     // Catch:{ Throwable -> 0x0027 }
            goto L_0x002f
        L_0x0027:
            r1 = move-exception
            java.lang.String r2 = "AMapLocationQualityReport"
            java.lang.String r3 = "clone"
            com.loc.di.a(r1, r2, r3)
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.AMapLocationQualityReport.clone():com.amap.api.location.AMapLocationQualityReport");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAdviseMessage() {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r3.f814a
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r2 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Battery_Saving
            if (r1 == r2) goto L_0x0037
            int r1 = r3.f816c
            if (r1 == 0) goto L_0x0028
            int r1 = r3.f816c
            switch(r1) {
                case 1: goto L_0x0021;
                case 2: goto L_0x001d;
                case 3: goto L_0x0019;
                case 4: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0037
        L_0x0015:
            java.lang.String r1 = "您的设置禁用了GPS定位权限，开启GPS定位权限有助于提高定位的精确度\n"
            goto L_0x0024
        L_0x0019:
            java.lang.String r1 = "您的设备当前设置的定位模式不包含GPS定位，选择包含GPS模式的定位模式有助于提高定位的精确度\n"
            goto L_0x0024
        L_0x001d:
            java.lang.String r1 = "您的设备关闭了GPS定位功能，开启GPS定位功能有助于提高定位的精确度\n"
            goto L_0x0024
        L_0x0021:
            java.lang.String r1 = "您的设备没有GPS模块或者GPS模块异常，无法进行GPS定位\n"
        L_0x0024:
            r0.append(r1)
            goto L_0x0037
        L_0x0028:
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r3.f814a
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r2 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Device_Sensors
            if (r1 != r2) goto L_0x0037
            int r1 = r3.d
            r2 = 4
            if (r1 >= r2) goto L_0x0037
            java.lang.String r1 = "当前GPS信号弱，位置更新可能会延迟\n"
            goto L_0x0024
        L_0x0037:
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r3.f814a
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r2 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Device_Sensors
            if (r1 == r2) goto L_0x0066
            java.lang.String r1 = r3.e
            java.lang.String r2 = "DISCONNECTED"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x004e
            java.lang.String r1 = "您的设备未连接到网络，无法进行网络定位\n"
        L_0x004a:
            r0.append(r1)
            goto L_0x005c
        L_0x004e:
            java.lang.String r1 = r3.e
            java.lang.String r2 = "2G"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x005c
            java.lang.String r1 = "您的设备网络状态不太好，网络定位可能会有延迟\n"
            goto L_0x004a
        L_0x005c:
            boolean r1 = r3.f815b
            if (r1 != 0) goto L_0x0066
            java.lang.String r1 = "您的设备WIFI开关已关闭，打开WIFI开关有助于提高定位的成功率\n"
            r0.append(r1)
        L_0x0066:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.AMapLocationQualityReport.getAdviseMessage():java.lang.String");
    }

    public int getGPSSatellites() {
        return this.d;
    }

    public int getGPSStatus() {
        return this.f816c;
    }

    public long getNetUseTime() {
        return this.f;
    }

    public String getNetworkType() {
        return this.e;
    }

    public boolean isWifiAble() {
        return this.f815b;
    }

    public void setGPSSatellites(int i) {
        this.d = i;
    }

    public void setGpsStatus(int i) {
        this.f816c = i;
    }

    public void setLocationMode(AMapLocationClientOption.AMapLocationMode aMapLocationMode) {
        this.f814a = aMapLocationMode;
    }

    public void setNetUseTime(long j) {
        this.f = j;
    }

    public void setNetworkType(String str) {
        this.e = str;
    }

    public void setWifiAble(boolean z) {
        this.f815b = z;
    }
}
