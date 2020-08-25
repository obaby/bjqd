package anetwork.channel.entity;

import anet.channel.request.Request;
import anet.channel.statist.RequestStatistic;
import anet.channel.strategy.utils.c;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import anet.channel.util.Utils;
import anetwork.channel.aidl.ParcelableRequest;
import anetwork.channel.config.NetworkConfigCenter;
import anetwork.channel.util.RequestConstant;
import anetwork.channel.util.a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public int f414a = 0;

    /* renamed from: b  reason: collision with root package name */
    public RequestStatistic f415b;

    /* renamed from: c  reason: collision with root package name */
    public final int f416c;
    public final int d;
    public final String e;
    public final int f;
    private ParcelableRequest g;
    private Request h = null;
    private int i = 0;
    private int j = 0;
    private final boolean k;

    public g(ParcelableRequest parcelableRequest, int i2, boolean z) {
        if (parcelableRequest != null) {
            this.g = parcelableRequest;
            this.f = i2;
            this.k = z;
            this.e = a.a(parcelableRequest.seqNo, this.f == 0 ? "HTTP" : "DGRD");
            this.f416c = parcelableRequest.connectTimeout <= 0 ? (int) (Utils.getNetworkTimeFactor() * 15000.0f) : parcelableRequest.connectTimeout;
            this.d = parcelableRequest.readTimeout <= 0 ? (int) (Utils.getNetworkTimeFactor() * 15000.0f) : parcelableRequest.readTimeout;
            this.j = (parcelableRequest.retryTime < 0 || parcelableRequest.retryTime > 3) ? 2 : parcelableRequest.retryTime;
            HttpUrl l = l();
            this.f415b = new RequestStatistic(l.host(), String.valueOf(parcelableRequest.bizId));
            this.f415b.url = l.simpleUrlString();
            this.h = b(l);
            return;
        }
        throw new IllegalArgumentException("request is null");
    }

    public Request a() {
        return this.h;
    }

    public void a(Request request) {
        this.h = request;
    }

    private HttpUrl l() {
        HttpUrl parse = HttpUrl.parse(this.g.url);
        if (parse != null) {
            if (!NetworkConfigCenter.isSSLEnabled()) {
                parse.downgradeSchemeAndLock();
            } else if (RequestConstant.FALSE.equalsIgnoreCase(this.g.getExtProperty(RequestConstant.ENABLE_SCHEME_REPLACE))) {
                parse.lockScheme();
            }
            return parse;
        }
        throw new IllegalArgumentException("url is invalid. url=" + this.g.url);
    }

    private Request b(HttpUrl httpUrl) {
        Request.Builder requestStatistic = new Request.Builder().setUrl(httpUrl).setMethod(this.g.method).setBody(this.g.bodyEntry).setReadTimeout(this.d).setConnectTimeout(this.f416c).setRedirectEnable(this.g.allowRedirect).setRedirectTimes(this.i).setBizId(this.g.bizId).setSeq(this.e).setRequestStatistic(this.f415b);
        requestStatistic.setParams(this.g.params);
        if (this.g.charset != null) {
            requestStatistic.setCharset(this.g.charset);
        }
        requestStatistic.setHeaders(c(httpUrl));
        return requestStatistic.build();
    }

    public int b() {
        return this.d * (this.j + 1);
    }

    public boolean c() {
        return this.k;
    }

    public String a(String str) {
        return this.g.getExtProperty(str);
    }

    public boolean d() {
        return this.f414a < this.j;
    }

    public boolean e() {
        return NetworkConfigCenter.isHttpSessionEnable() && !RequestConstant.FALSE.equalsIgnoreCase(this.g.getExtProperty(RequestConstant.ENABLE_HTTP_DNS)) && (NetworkConfigCenter.isAllowHttpIpRetry() || this.f414a == 0);
    }

    public HttpUrl f() {
        return this.h.getHttpUrl();
    }

    public String g() {
        return this.h.getUrlString();
    }

    public Map<String, String> h() {
        return this.h.getHeaders();
    }

    private Map<String, String> c(HttpUrl httpUrl) {
        boolean z = !c.a(httpUrl.host());
        HashMap hashMap = new HashMap();
        if (this.g.headers != null) {
            for (Map.Entry next : this.g.headers.entrySet()) {
                String str = (String) next.getKey();
                if (!"Host".equalsIgnoreCase(str) && !":host".equalsIgnoreCase(str)) {
                    boolean equalsIgnoreCase = RequestConstant.TRUE.equalsIgnoreCase(this.g.getExtProperty(RequestConstant.KEEP_CUSTOM_COOKIE));
                    if (!HttpConstant.COOKIE.equalsIgnoreCase(str) || equalsIgnoreCase) {
                        hashMap.put(str, next.getValue());
                    }
                } else if (!z) {
                    hashMap.put("Host", next.getValue());
                }
            }
        }
        return hashMap;
    }

    public boolean i() {
        return !RequestConstant.FALSE.equalsIgnoreCase(this.g.getExtProperty(RequestConstant.ENABLE_COOKIE));
    }

    public boolean j() {
        return RequestConstant.TRUE.equals(this.g.getExtProperty(RequestConstant.CHECK_CONTENT_LENGTH));
    }

    public void k() {
        this.f414a++;
        this.f415b.retryTimes = this.f414a;
    }

    public void a(HttpUrl httpUrl) {
        this.i++;
        this.f415b = new RequestStatistic(httpUrl.host(), String.valueOf(this.g.bizId));
        this.f415b.url = httpUrl.simpleUrlString();
        this.h = b(httpUrl);
    }
}
