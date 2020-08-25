package anet.channel.util;

import java.net.MalformedURLException;
import java.net.URL;

/* compiled from: Taobao */
public class HttpUrl {
    private String host;
    private volatile boolean isSchemeLocked = false;
    private String path;
    private int port;
    private String scheme;
    private String simpleUrl;
    private String url;

    private HttpUrl() {
    }

    public HttpUrl(HttpUrl httpUrl) {
        this.scheme = httpUrl.scheme;
        this.host = httpUrl.host;
        this.path = httpUrl.path;
        this.url = httpUrl.url;
        this.simpleUrl = httpUrl.simpleUrl;
        this.isSchemeLocked = httpUrl.isSchemeLocked;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static anet.channel.util.HttpUrl parse(java.lang.String r11) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.String r11 = r11.trim()
            anet.channel.util.HttpUrl r0 = new anet.channel.util.HttpUrl
            r0.<init>()
            r0.url = r11
            java.lang.String r2 = "//"
            boolean r2 = r11.startsWith(r2)
            r8 = 0
            if (r2 == 0) goto L_0x0020
            r0.scheme = r1
            r2 = 0
            goto L_0x0045
        L_0x0020:
            r3 = 1
            java.lang.String r5 = "https:"
            r6 = 0
            r7 = 6
            r4 = 0
            r2 = r11
            boolean r2 = r2.regionMatches(r3, r4, r5, r6, r7)
            if (r2 == 0) goto L_0x0033
            java.lang.String r2 = "https"
            r0.scheme = r2
            r2 = 6
            goto L_0x0045
        L_0x0033:
            r3 = 1
            java.lang.String r5 = "http:"
            r6 = 0
            r7 = 5
            r4 = 0
            r2 = r11
            boolean r2 = r2.regionMatches(r3, r4, r5, r6, r7)
            if (r2 == 0) goto L_0x0138
            java.lang.String r2 = "http"
            r0.scheme = r2
            r2 = 5
        L_0x0045:
            int r3 = r11.length()
            int r2 = r2 + 2
            r4 = r2
        L_0x004c:
            r5 = 58
            r6 = 35
            r7 = 63
            r9 = 47
            if (r4 >= r3) goto L_0x006c
            char r10 = r11.charAt(r4)
            if (r10 == r9) goto L_0x0066
            if (r10 == r5) goto L_0x0066
            if (r10 == r7) goto L_0x0066
            if (r10 != r6) goto L_0x0063
            goto L_0x0066
        L_0x0063:
            int r4 = r4 + 1
            goto L_0x004c
        L_0x0066:
            java.lang.String r10 = r11.substring(r2, r4)
            r0.host = r10
        L_0x006c:
            if (r4 != r3) goto L_0x0074
            java.lang.String r2 = r11.substring(r2)
            r0.host = r2
        L_0x0074:
            r2 = 0
        L_0x0075:
            if (r4 >= r3) goto L_0x008e
            char r10 = r11.charAt(r4)
            if (r10 != r5) goto L_0x0082
            if (r2 != 0) goto L_0x0082
            int r2 = r4 + 1
            goto L_0x0089
        L_0x0082:
            if (r10 == r9) goto L_0x008c
            if (r10 == r6) goto L_0x008c
            if (r10 != r7) goto L_0x0089
            goto L_0x008c
        L_0x0089:
            int r4 = r4 + 1
            goto L_0x0075
        L_0x008c:
            r5 = r4
            goto L_0x008f
        L_0x008e:
            r5 = r3
        L_0x008f:
            if (r2 == 0) goto L_0x00a8
            java.lang.String r2 = r11.substring(r2, r5)
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x00a7 }
            r0.port = r2     // Catch:{ NumberFormatException -> 0x00a7 }
            int r2 = r0.port     // Catch:{ NumberFormatException -> 0x00a7 }
            if (r2 <= 0) goto L_0x00a6
            int r2 = r0.port     // Catch:{ NumberFormatException -> 0x00a7 }
            r5 = 65535(0xffff, float:9.1834E-41)
            if (r2 <= r5) goto L_0x00a8
        L_0x00a6:
            return r1
        L_0x00a7:
            return r1
        L_0x00a8:
            if (r4 >= r3) goto L_0x00c0
            char r2 = r11.charAt(r4)
            if (r2 != r9) goto L_0x00b4
            if (r8 != 0) goto L_0x00b4
            r8 = r4
            goto L_0x00b9
        L_0x00b4:
            if (r2 == r7) goto L_0x00bc
            if (r2 != r6) goto L_0x00b9
            goto L_0x00bc
        L_0x00b9:
            int r4 = r4 + 1
            goto L_0x00a8
        L_0x00bc:
            if (r8 == 0) goto L_0x00c0
            r2 = r4
            goto L_0x00c1
        L_0x00c0:
            r2 = r3
        L_0x00c1:
            if (r8 == 0) goto L_0x00ca
            java.lang.String r2 = r11.substring(r8, r2)
            r0.path = r2
            goto L_0x00cc
        L_0x00ca:
            r0.path = r1
        L_0x00cc:
            java.lang.String r2 = r0.scheme
            if (r2 != 0) goto L_0x00f2
            int r2 = r0.port
            r5 = 80
            if (r2 != r5) goto L_0x00db
            java.lang.String r1 = "http"
            r0.scheme = r1
            goto L_0x00f2
        L_0x00db:
            int r2 = r0.port
            r5 = 443(0x1bb, float:6.21E-43)
            if (r2 != r5) goto L_0x00e6
            java.lang.String r1 = "https"
            r0.scheme = r1
            goto L_0x00f2
        L_0x00e6:
            anet.channel.strategy.IStrategyInstance r2 = anet.channel.strategy.StrategyCenter.getInstance()
            java.lang.String r5 = r0.host
            java.lang.String r1 = r2.getSchemeByHost(r5, r1)
            r0.scheme = r1
        L_0x00f2:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = r0.scheme
            r1.<init>(r2)
            java.lang.String r2 = "://"
            r1.append(r2)
            java.lang.String r2 = r0.host
            r1.append(r2)
            boolean r2 = r0.containsNonDefaultPort()
            if (r2 == 0) goto L_0x0113
            java.lang.String r2 = ":"
            r1.append(r2)
            int r2 = r0.port
            r1.append(r2)
        L_0x0113:
            java.lang.String r2 = r0.path
            if (r2 == 0) goto L_0x011d
            java.lang.String r2 = r0.path
            r1.append(r2)
            goto L_0x0124
        L_0x011d:
            if (r4 == r3) goto L_0x0124
            java.lang.String r2 = "/"
            r1.append(r2)
        L_0x0124:
            java.lang.String r2 = r1.toString()
            r0.simpleUrl = r2
            java.lang.String r11 = r11.substring(r4)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r0.url = r11
            return r0
        L_0x0138:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.HttpUrl.parse(java.lang.String):anet.channel.util.HttpUrl");
    }

    public String scheme() {
        return this.scheme;
    }

    public String host() {
        return this.host;
    }

    public String path() {
        return this.path;
    }

    public int getPort() {
        return this.port;
    }

    public String urlString() {
        return this.url;
    }

    public String simpleUrlString() {
        return this.simpleUrl;
    }

    public URL toURL() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    public boolean containsNonDefaultPort() {
        return this.port != 0 && ((HttpConstant.HTTP.equals(this.scheme) && this.port != 80) || ("https".equals(this.scheme) && this.port != 443));
    }

    public void downgradeSchemeAndLock() {
        this.isSchemeLocked = true;
        if (!HttpConstant.HTTP.equals(this.scheme)) {
            this.scheme = HttpConstant.HTTP;
            this.url = StringUtils.concatString(this.scheme, ":", this.url.substring(this.url.indexOf("//")));
        }
    }

    public boolean isSchemeLocked() {
        return this.isSchemeLocked;
    }

    public void lockScheme() {
        this.isSchemeLocked = true;
    }

    public void setScheme(String str) {
        if (!this.isSchemeLocked && !str.equalsIgnoreCase(this.scheme)) {
            this.scheme = str;
            this.url = StringUtils.concatString(str, ":", this.url.substring(this.url.indexOf("//")));
            this.simpleUrl = StringUtils.concatString(str, ":", this.simpleUrl.substring(this.url.indexOf("//")));
        }
    }

    public void replaceIpAndPort(String str, int i) {
        if (i != 0 && str != null) {
            int indexOf = this.url.indexOf("//") + 2;
            while (indexOf < this.url.length() && this.url.charAt(indexOf) != '/') {
                indexOf++;
            }
            StringBuilder sb = new StringBuilder(this.url.length() + str.length());
            sb.append(this.scheme);
            sb.append(HttpConstant.SCHEME_SPLIT);
            sb.append(str);
            sb.append(':');
            sb.append(i);
            sb.append(this.url.substring(indexOf));
            this.url = sb.toString();
        }
    }

    public String toString() {
        return this.url;
    }
}
