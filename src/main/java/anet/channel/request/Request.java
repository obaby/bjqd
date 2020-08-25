package anet.channel.request;

import android.text.TextUtils;
import anet.channel.statist.RequestStatistic;
import anet.channel.strategy.utils.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: Taobao */
public class Request {
    public static final String DEFAULT_CHARSET = "UTF-8";

    /* renamed from: a  reason: collision with root package name */
    public final RequestStatistic f211a;

    /* renamed from: b  reason: collision with root package name */
    private HttpUrl f212b;

    /* renamed from: c  reason: collision with root package name */
    private HttpUrl f213c;
    private HttpUrl d;
    private URL e;
    private String f;
    private Map<String, String> g;
    private Map<String, String> h;
    private String i;
    private BodyEntry j;
    private boolean k;
    private String l;
    private String m;
    private int n;
    private int o;
    private int p;
    private HostnameVerifier q;
    private SSLSocketFactory r;

    /* compiled from: Taobao */
    public static final class Method {
        public static final String DELETE = "DELETE";
        public static final String GET = "GET";
        public static final String HEAD = "HEAD";
        public static final String OPTION = "OPTIONS";
        public static final String POST = "POST";
        public static final String PUT = "PUT";

        static boolean a(String str) {
            return str.equals(POST) || str.equals(PUT);
        }

        static boolean b(String str) {
            return a(str) || str.equals(DELETE) || str.equals(OPTION);
        }
    }

    private Request(Builder builder) {
        this.f = Method.GET;
        this.k = true;
        this.n = 0;
        this.o = 10000;
        this.p = 10000;
        this.f = builder.f216c;
        this.g = builder.d;
        this.h = builder.e;
        this.j = builder.g;
        this.i = builder.f;
        this.k = builder.h;
        this.n = builder.i;
        this.q = builder.j;
        this.r = builder.k;
        this.l = builder.l;
        this.m = builder.m;
        this.o = builder.n;
        this.p = builder.o;
        this.f212b = builder.f214a;
        this.f213c = builder.f215b;
        if (this.f213c == null) {
            a();
        }
        this.f211a = builder.p != null ? builder.p : new RequestStatistic(getHost(), this.l);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        String unused = builder.f216c = this.f;
        Map unused2 = builder.d = this.g;
        Map unused3 = builder.e = this.h;
        BodyEntry unused4 = builder.g = this.j;
        String unused5 = builder.f = this.i;
        boolean unused6 = builder.h = this.k;
        int unused7 = builder.i = this.n;
        HostnameVerifier unused8 = builder.j = this.q;
        SSLSocketFactory unused9 = builder.k = this.r;
        HttpUrl unused10 = builder.f214a = this.f212b;
        HttpUrl unused11 = builder.f215b = this.f213c;
        String unused12 = builder.l = this.l;
        String unused13 = builder.m = this.m;
        int unused14 = builder.n = this.o;
        int unused15 = builder.o = this.p;
        RequestStatistic unused16 = builder.p = this.f211a;
        return builder;
    }

    public HttpUrl getHttpUrl() {
        return this.f213c;
    }

    public String getUrlString() {
        return this.f213c.urlString();
    }

    public URL getUrl() {
        if (this.e == null) {
            this.e = (this.d != null ? this.d : this.f213c).toURL();
        }
        return this.e;
    }

    public void setDnsOptimize(String str, int i2) {
        if (str != null && i2 != 0) {
            if (this.d == null) {
                this.d = new HttpUrl(this.f213c);
            }
            this.d.replaceIpAndPort(str, i2);
            this.f211a.setIPAndPort(str, i2);
            this.e = null;
        }
    }

    public void setUrlScheme(boolean z) {
        if (this.d == null) {
            this.d = new HttpUrl(this.f213c);
        }
        this.d.setScheme(z ? "https" : HttpConstant.HTTP);
        this.e = null;
    }

    public int getRedirectTimes() {
        return this.n;
    }

    public String getHost() {
        return this.f213c.host();
    }

    public String getMethod() {
        return this.f;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.g);
    }

    public String getContentEncoding() {
        return this.i != null ? this.i : "UTF-8";
    }

    public boolean isRedirectEnable() {
        return this.k;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.q;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.r;
    }

    public int postBody(OutputStream outputStream) throws IOException {
        if (this.j != null) {
            return this.j.writeTo(outputStream);
        }
        return 0;
    }

    public byte[] getBodyBytes() {
        if (this.j == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(128);
        try {
            postBody(byteArrayOutputStream);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public boolean containsBody() {
        return this.j != null;
    }

    public String getBizId() {
        return this.l;
    }

    public String getSeq() {
        return this.m;
    }

    public int getReadTimeout() {
        return this.p;
    }

    public int getConnectTimeout() {
        return this.o;
    }

    private void a() {
        String a2 = c.a(this.h, getContentEncoding());
        if (!TextUtils.isEmpty(a2)) {
            if (!Method.a(this.f) || this.j != null) {
                String urlString = this.f212b.urlString();
                StringBuilder sb = new StringBuilder(urlString);
                if (sb.indexOf("?") == -1) {
                    sb.append('?');
                } else if (urlString.charAt(urlString.length() - 1) != '&') {
                    sb.append('&');
                }
                sb.append(a2);
                HttpUrl parse = HttpUrl.parse(sb.toString());
                if (parse != null) {
                    this.f213c = parse;
                }
            } else {
                try {
                    this.j = new ByteArrayEntry(a2.getBytes(getContentEncoding()));
                    Map<String, String> map = this.g;
                    map.put("Content-Type", "application/x-www-form-urlencoded; charset=" + getContentEncoding());
                } catch (UnsupportedEncodingException unused) {
                }
            }
        }
        if (this.f213c == null) {
            this.f213c = this.f212b;
        }
    }

    /* compiled from: Taobao */
    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public HttpUrl f214a;
        /* access modifiers changed from: private */

        /* renamed from: b  reason: collision with root package name */
        public HttpUrl f215b;
        /* access modifiers changed from: private */

        /* renamed from: c  reason: collision with root package name */
        public String f216c = Method.GET;
        /* access modifiers changed from: private */
        public Map<String, String> d = new HashMap();
        /* access modifiers changed from: private */
        public Map<String, String> e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public BodyEntry g;
        /* access modifiers changed from: private */
        public boolean h = true;
        /* access modifiers changed from: private */
        public int i = 0;
        /* access modifiers changed from: private */
        public HostnameVerifier j;
        /* access modifiers changed from: private */
        public SSLSocketFactory k;
        /* access modifiers changed from: private */
        public String l;
        /* access modifiers changed from: private */
        public String m;
        /* access modifiers changed from: private */
        public int n = 10000;
        /* access modifiers changed from: private */
        public int o = 10000;
        /* access modifiers changed from: private */
        public RequestStatistic p = null;

        public Builder setUrl(HttpUrl httpUrl) {
            this.f214a = httpUrl;
            this.f215b = null;
            return this;
        }

        public Builder setUrl(String str) {
            this.f214a = HttpUrl.parse(str);
            this.f215b = null;
            if (this.f214a != null) {
                return this;
            }
            throw new IllegalArgumentException("toURL is invalid! toURL = " + str);
        }

        public Builder setMethod(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (Method.GET.equalsIgnoreCase(str)) {
                    this.f216c = Method.GET;
                } else if (Method.POST.equalsIgnoreCase(str)) {
                    this.f216c = Method.POST;
                } else if (Method.OPTION.equalsIgnoreCase(str)) {
                    this.f216c = Method.OPTION;
                } else if (Method.HEAD.equalsIgnoreCase(str)) {
                    this.f216c = Method.HEAD;
                } else if (Method.PUT.equalsIgnoreCase(str)) {
                    this.f216c = Method.PUT;
                } else if (Method.DELETE.equalsIgnoreCase(str)) {
                    this.f216c = Method.DELETE;
                } else {
                    this.f216c = Method.GET;
                }
                return this;
            }
            throw new IllegalArgumentException("method is null or empty");
        }

        public Builder setHeaders(Map<String, String> map) {
            this.d.clear();
            if (map != null) {
                this.d.putAll(map);
            }
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.d.put(str, str2);
            return this;
        }

        public Builder setParams(Map<String, String> map) {
            this.e = map;
            this.f215b = null;
            return this;
        }

        public Builder addParam(String str, String str2) {
            if (this.e == null) {
                this.e = new HashMap();
            }
            this.e.put(str, str2);
            this.f215b = null;
            return this;
        }

        public Builder setCharset(String str) {
            this.f = str;
            this.f215b = null;
            return this;
        }

        public Builder setBody(BodyEntry bodyEntry) {
            this.g = bodyEntry;
            return this;
        }

        public Builder setRedirectEnable(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setRedirectTimes(int i2) {
            this.i = i2;
            return this;
        }

        public Builder setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.j = hostnameVerifier;
            return this;
        }

        public Builder setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.k = sSLSocketFactory;
            return this;
        }

        public Builder setBizId(String str) {
            this.l = str;
            return this;
        }

        public Builder setSeq(String str) {
            this.m = str;
            return this;
        }

        public Builder setReadTimeout(int i2) {
            if (i2 > 0) {
                this.o = i2;
            }
            return this;
        }

        public Builder setConnectTimeout(int i2) {
            if (i2 > 0) {
                this.n = i2;
            }
            return this;
        }

        public Builder setRequestStatistic(RequestStatistic requestStatistic) {
            this.p = requestStatistic;
            return this;
        }

        public Request build() {
            if (this.g == null && this.e == null && Method.a(this.f216c)) {
                ALog.e("awcn.Request", "method " + this.f216c + " must have a request body", (String) null, new Object[0]);
            }
            if (this.g != null && !Method.b(this.f216c)) {
                ALog.e("awcn.Request", "method " + this.f216c + " should not have a request body", (String) null, new Object[0]);
                this.g = null;
            }
            if (!(this.g == null || this.g.getContentType() == null)) {
                addHeader("Content-Type", this.g.getContentType());
            }
            return new Request(this);
        }
    }
}
