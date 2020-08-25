package anetwork.channel.entity;

import android.text.TextUtils;
import anet.channel.request.BodyEntry;
import anet.channel.request.Request;
import anet.channel.util.ALog;
import anetwork.channel.Header;
import anetwork.channel.IBodyHandler;
import anetwork.channel.Param;
import anetwork.channel.Request;
import anetwork.channel.util.RequestConstant;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
public class RequestImpl implements Request {
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    private URI f397a;
    @Deprecated

    /* renamed from: b  reason: collision with root package name */
    private URL f398b;

    /* renamed from: c  reason: collision with root package name */
    private String f399c;
    private boolean d = true;
    private List<Header> e;
    private String f = Request.Method.GET;
    private List<Param> g;
    private int h = 2;
    private String i = "utf-8";
    private BodyEntry j = null;
    private int k;
    private int l;
    private String m;
    private String n;
    private Map<String, String> o;

    @Deprecated
    public IBodyHandler getBodyHandler() {
        return null;
    }

    public RequestImpl() {
    }

    @Deprecated
    public RequestImpl(URI uri) {
        this.f397a = uri;
        this.f399c = uri.toString();
    }

    @Deprecated
    public RequestImpl(URL url) {
        this.f398b = url;
        this.f399c = url.toString();
    }

    public RequestImpl(String str) {
        this.f399c = str;
    }

    @Deprecated
    public URI getURI() {
        if (this.f397a != null) {
            return this.f397a;
        }
        if (this.f399c != null) {
            try {
                this.f397a = new URI(this.f399c);
            } catch (Exception e2) {
                ALog.e("anet.RequestImpl", "uri error", this.n, e2, new Object[0]);
            }
        }
        return this.f397a;
    }

    @Deprecated
    public void setUri(URI uri) {
        this.f397a = uri;
    }

    @Deprecated
    public URL getURL() {
        if (this.f398b != null) {
            return this.f398b;
        }
        if (this.f399c != null) {
            try {
                this.f398b = new URL(this.f399c);
            } catch (Exception e2) {
                ALog.e("anet.RequestImpl", "url error", this.n, e2, new Object[0]);
            }
        }
        return this.f398b;
    }

    @Deprecated
    public void setUrL(URL url) {
        this.f398b = url;
        this.f399c = url.toString();
    }

    public String getUrlString() {
        return this.f399c;
    }

    public boolean getFollowRedirects() {
        return this.d;
    }

    public void setFollowRedirects(boolean z) {
        this.d = z;
    }

    public List<Header> getHeaders() {
        return this.e;
    }

    public void setHeaders(List<Header> list) {
        this.e = list;
    }

    public void addHeader(String str, String str2) {
        if (str != null && str2 != null) {
            if (this.e == null) {
                this.e = new ArrayList();
            }
            this.e.add(new BasicHeader(str, str2));
        }
    }

    public void removeHeader(Header header) {
        if (this.e != null) {
            this.e.remove(header);
        }
    }

    public void setHeader(Header header) {
        if (header != null) {
            if (this.e == null) {
                this.e = new ArrayList();
            }
            int i2 = 0;
            int size = this.e.size();
            while (true) {
                if (i2 >= size) {
                    break;
                }
                if (header.getName().equalsIgnoreCase(this.e.get(i2).getName())) {
                    this.e.set(i2, header);
                    break;
                }
                i2++;
            }
            if (i2 < this.e.size()) {
                this.e.add(header);
            }
        }
    }

    public Header[] getHeaders(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (this.e == null) {
            return null;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (!(this.e.get(i2) == null || this.e.get(i2).getName() == null || !this.e.get(i2).getName().equalsIgnoreCase(str))) {
                arrayList.add(this.e.get(i2));
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        Header[] headerArr = new Header[arrayList.size()];
        arrayList.toArray(headerArr);
        return headerArr;
    }

    public String getMethod() {
        return this.f;
    }

    public void setMethod(String str) {
        this.f = str;
    }

    public int getRetryTime() {
        return this.h;
    }

    public void setRetryTime(int i2) {
        this.h = i2;
    }

    public String getCharset() {
        return this.i;
    }

    public void setCharset(String str) {
        this.i = str;
    }

    public List<Param> getParams() {
        return this.g;
    }

    public void setParams(List<Param> list) {
        this.g = list;
    }

    public BodyEntry getBodyEntry() {
        return this.j;
    }

    public void setBodyEntry(BodyEntry bodyEntry) {
        this.j = bodyEntry;
    }

    public void setBodyHandler(IBodyHandler iBodyHandler) {
        this.j = new BodyHandlerEntry(iBodyHandler);
    }

    public int getConnectTimeout() {
        return this.k;
    }

    public int getReadTimeout() {
        return this.l;
    }

    public void setConnectTimeout(int i2) {
        this.k = i2;
    }

    public void setReadTimeout(int i2) {
        this.l = i2;
    }

    @Deprecated
    public void setBizId(int i2) {
        this.m = String.valueOf(i2);
    }

    public void setBizId(String str) {
        this.m = str;
    }

    public String getBizId() {
        return this.m;
    }

    public void setSeqNo(String str) {
        this.n = str;
    }

    public String getSeqNo() {
        return this.n;
    }

    @Deprecated
    public boolean isCookieEnabled() {
        return !RequestConstant.FALSE.equals(getExtProperty(RequestConstant.ENABLE_COOKIE));
    }

    @Deprecated
    public void setCookieEnabled(boolean z) {
        setExtProperty(RequestConstant.ENABLE_COOKIE, z ? RequestConstant.TRUE : RequestConstant.FALSE);
    }

    public void setExtProperty(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (this.o == null) {
                this.o = new HashMap();
            }
            this.o.put(str, str2);
        }
    }

    public String getExtProperty(String str) {
        if (this.o == null) {
            return null;
        }
        return this.o.get(str);
    }

    public Map<String, String> getExtProperties() {
        return this.o;
    }
}
