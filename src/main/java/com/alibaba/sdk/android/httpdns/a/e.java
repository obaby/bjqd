package com.alibaba.sdk.android.httpdns.a;

import java.util.ArrayList;
import java.util.Iterator;

public class e {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<g> f460a;
    public long id;
    public String j;
    public String k;
    public String l;

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("[HostRecord] ");
        sb.append("id:");
        sb.append(this.id);
        sb.append("|");
        sb.append("host:");
        sb.append(this.j);
        sb.append("|");
        sb.append("sp:");
        sb.append(this.k);
        sb.append("|");
        sb.append("time:");
        sb.append(this.l);
        sb.append("|");
        sb.append("ips:");
        if (this.f460a != null && this.f460a.size() > 0) {
            Iterator<g> it = this.f460a.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
        }
        sb.append("|");
        return sb.toString();
    }
}
