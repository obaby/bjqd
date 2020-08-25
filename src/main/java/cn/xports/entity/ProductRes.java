package cn.xports.entity;

import java.io.Serializable;

public class ProductRes implements Serializable {
    private String disableTag;
    private String endDatae;
    private String limitTag;
    private String offsetEndDate;
    private String offsetStartDate;
    private String offsetUnit;
    private String offsetValue;
    private String productResId;
    private String resType;
    private String startDate;

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDatae() {
        return this.endDatae;
    }

    public void setEndDatae(String str) {
        this.endDatae = str;
    }

    public String getProductResId() {
        return this.productResId;
    }

    public void setProductResId(String str) {
        this.productResId = str;
    }

    public String getResType() {
        return this.resType;
    }

    public void setResType(String str) {
        this.resType = str;
    }

    public String getDisableTag() {
        return this.disableTag;
    }

    public void setDisableTag(String str) {
        this.disableTag = str;
    }

    public String getOffsetValue() {
        return this.offsetValue;
    }

    public void setOffsetValue(String str) {
        this.offsetValue = str;
    }

    public String getOffsetUnit() {
        if (this.offsetUnit == null) {
            return "";
        }
        return this.offsetUnit;
    }

    public void setOffsetUnit(String str) {
        this.offsetUnit = str;
    }

    public String getLimitTag() {
        return this.limitTag;
    }

    public void setLimitTag(String str) {
        this.limitTag = str;
    }

    public String getOffsetStartDate() {
        return this.offsetStartDate;
    }

    public void setOffsetStartDate(String str) {
        this.offsetStartDate = str;
    }

    public String getOffsetEndDate() {
        return this.offsetEndDate;
    }

    public void setOffsetEndDate(String str) {
        this.offsetEndDate = str;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getLimitInfo() {
        /*
            r6 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = r6.getOffsetUnit()
            int r2 = r1.hashCode()
            r3 = 0
            r4 = 2
            switch(r2) {
                case 48: goto L_0x002e;
                case 49: goto L_0x0024;
                case 50: goto L_0x001a;
                case 51: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0038
        L_0x0010:
            java.lang.String r2 = "3"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0038
            r1 = 2
            goto L_0x0039
        L_0x001a:
            java.lang.String r2 = "2"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0038
            r1 = 3
            goto L_0x0039
        L_0x0024:
            java.lang.String r2 = "1"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0038
            r1 = 1
            goto L_0x0039
        L_0x002e:
            java.lang.String r2 = "0"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0038
            r1 = 0
            goto L_0x0039
        L_0x0038:
            r1 = -1
        L_0x0039:
            switch(r1) {
                case 0: goto L_0x0049;
                case 1: goto L_0x0045;
                case 2: goto L_0x0041;
                case 3: goto L_0x003d;
                default: goto L_0x003c;
            }
        L_0x003c:
            goto L_0x004c
        L_0x003d:
            java.lang.String r0 = "季"
            goto L_0x004c
        L_0x0041:
            java.lang.String r0 = "年"
            goto L_0x004c
        L_0x0045:
            java.lang.String r0 = "月"
            goto L_0x004c
        L_0x0049:
            java.lang.String r0 = "天"
        L_0x004c:
            java.lang.String r1 = ""
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L_0x0074
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "自办卡之日起"
            r1.append(r2)
            java.lang.String r2 = r6.getOffsetValue()
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "内"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            return r0
        L_0x0074:
            java.lang.String r0 = r6.getOffsetStartDate()     // Catch:{ Exception -> 0x00f3 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00f3 }
            if (r0 != 0) goto L_0x00bc
            java.lang.String r0 = r6.getOffsetStartDate()     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r1 = r6.getOffsetEndDate()     // Catch:{ Exception -> 0x00f3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f3 }
            r2.<init>()     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r5 = r0.substring(r3, r4)     // Catch:{ Exception -> 0x00f3 }
            r2.append(r5)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r5 = "/"
            r2.append(r5)     // Catch:{ Exception -> 0x00f3 }
            r5 = 4
            java.lang.String r0 = r0.substring(r4, r5)     // Catch:{ Exception -> 0x00f3 }
            r2.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = "-"
            r2.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = r1.substring(r3, r4)     // Catch:{ Exception -> 0x00f3 }
            r2.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = "/"
            r2.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = r1.substring(r4, r5)     // Catch:{ Exception -> 0x00f3 }
            r2.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x00f3 }
            return r0
        L_0x00bc:
            java.lang.String r0 = r6.getStartDate()     // Catch:{ Exception -> 0x00f3 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00f3 }
            if (r0 != 0) goto L_0x00f3
            java.lang.String r0 = r6.getStartDate()     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r1 = r6.getEndDatae()     // Catch:{ Exception -> 0x00f3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f3 }
            r2.<init>()     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r3 = "-"
            java.lang.String r4 = "/"
            java.lang.String r0 = r0.replace(r3, r4)     // Catch:{ Exception -> 0x00f3 }
            r2.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = "-"
            r2.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = "-"
            java.lang.String r3 = "/"
            java.lang.String r0 = r1.replace(r0, r3)     // Catch:{ Exception -> 0x00f3 }
            r2.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x00f3 }
            return r0
        L_0x00f3:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.entity.ProductRes.getLimitInfo():java.lang.String");
    }
}
