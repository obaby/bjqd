package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import com.alibaba.sdk.android.httpdns.a.b;
import com.alibaba.sdk.android.utils.AMSDevReporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpDns implements HttpDnsService {
    private static DegradationFilter degradationFilter = null;
    private static b hostManager = b.a();
    static HttpDns instance = null;
    private static boolean isEnabled = true;
    private static ExecutorService pool = Executors.newFixedThreadPool(3, new h());
    private boolean isExpiredIPEnabled = false;

    private HttpDns() {
    }

    private String getIpByHost(String str) {
        if (!isEnabled) {
            g.f("HttpDns service turned off");
            return null;
        }
        String[] ipsByHost = getIpsByHost(str);
        if (ipsByHost.length > 0) {
            return ipsByHost[0];
        }
        return null;
    }

    private String[] getIpsByHost(String str) {
        if (!isEnabled) {
            g.f("HttpDns service turned off");
        } else if (!j.b(str)) {
            return d.f7d;
        } else {
            if (j.c(str)) {
                return new String[]{str};
            } else if (degradationFilter != null && degradationFilter.shouldDegradeHttpDNS(str)) {
                return d.f7d;
            } else {
                if (s.d()) {
                    return getIpsByHostAsync(str);
                }
                c a2 = hostManager.a(str);
                if (a2 != null && a2.b() && this.isExpiredIPEnabled) {
                    if (!hostManager.a(str)) {
                        g.e("refresh host async: " + str);
                        pool.submit(new l(str, n.QUERY_HOST));
                    }
                    return a2.a();
                } else if (a2 != null && !a2.b()) {
                    return a2.a();
                } else {
                    g.e("refresh host sync: " + str);
                    try {
                        return (String[]) pool.submit(new l(str, n.QUERY_HOST)).get();
                    } catch (Exception e) {
                        g.a(e);
                    }
                }
            }
        }
        return d.f7d;
    }

    public static HttpDnsService getService(Context context, String str) {
        if (instance == null) {
            synchronized (HttpDns.class) {
                if (instance == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(AMSDevReporter.AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString(), "1.1.3.1");
                    AMSDevReporter.asyncReport(context, AMSDevReporter.AMSSdkTypeEnum.AMS_HTTPDNS, hashMap);
                    k.setContext(context);
                    l.setContext(context);
                    b.a(context);
                    b.b(context);
                    s.a(context);
                    d.d(str);
                    o.a().a(context);
                    instance = new HttpDns();
                }
            }
        }
        return instance;
    }

    public static HttpDnsService getService(Context context, String str, String str2) {
        if (instance == null) {
            synchronized (HttpDns.class) {
                if (instance == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(AMSDevReporter.AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString(), "1.1.3.1");
                    AMSDevReporter.asyncReport(context, AMSDevReporter.AMSSdkTypeEnum.AMS_HTTPDNS, hashMap);
                    k.setContext(context);
                    l.setContext(context);
                    b.a(context);
                    b.b(context);
                    s.a(context);
                    d.d(str);
                    o.a().a(context);
                    a.a(str2);
                    instance = new HttpDns();
                }
            }
        }
        return instance;
    }

    static synchronized void switchDnsService(boolean z) {
        synchronized (HttpDns.class) {
            isEnabled = z;
            if (!isEnabled) {
                g.f("httpdns service disabled");
            }
        }
    }

    public String getIpByHostAsync(String str) {
        if (!isEnabled) {
            g.f("HttpDns service turned off");
            return null;
        }
        String[] ipsByHostAsync = getIpsByHostAsync(str);
        if (ipsByHostAsync.length > 0) {
            return ipsByHostAsync[0];
        }
        return null;
    }

    public String[] getIpsByHostAsync(String str) {
        if (!isEnabled) {
            g.f("HttpDns service turned off");
            return d.f7d;
        } else if (!j.b(str)) {
            return d.f7d;
        } else {
            boolean z = false;
            if (j.c(str)) {
                return new String[]{str};
            } else if (degradationFilter != null && degradationFilter.shouldDegradeHttpDNS(str)) {
                return d.f7d;
            } else {
                c a2 = hostManager.a(str);
                if ((a2 == null || (z = a2.b())) && !hostManager.a(str)) {
                    if (s.d()) {
                        q.a().g(str);
                    } else {
                        g.e("refresh host async: " + str);
                        pool.submit(new l(str, n.QUERY_HOST));
                    }
                }
                return a2 == null ? d.f7d : s.d() ? d.f7d : this.isExpiredIPEnabled ? a2.a() : !z ? a2.a() : d.f7d;
            }
        }
    }

    public void setAuthCurrentTime(long j) {
        a.setAuthCurrentTime(j);
    }

    public void setCachedIPEnabled(boolean z) {
        b.c(z);
        b.a().a();
    }

    public void setDegradationFilter(DegradationFilter degradationFilter2) {
        degradationFilter = degradationFilter2;
    }

    public void setExpiredIPEnabled(boolean z) {
        this.isExpiredIPEnabled = z;
    }

    public void setHTTPSRequestEnabled(boolean z) {
        d.setHTTPSRequestEnabled(z);
    }

    public void setLogEnabled(boolean z) {
        g.setLogEnabled(z);
    }

    public void setPreResolveAfterNetworkChanged(boolean z) {
        k.f477b = z;
    }

    public void setPreResolveHosts(ArrayList<String> arrayList) {
        if (!isEnabled) {
            g.f("HttpDns service turned off");
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            String str = arrayList.get(i);
            if (!hostManager.a(str)) {
                pool.submit(new l(str, n.QUERY_HOST));
            }
        }
    }

    public void setTimeoutInterval(int i) {
        d.setTimeoutInterval(i);
    }
}
