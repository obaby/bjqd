package com.alibaba.sdk.android.httpdns.a;

public class g {
    public long g;
    public long id;
    public String m;
    public String n;

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("[IpRecord] ");
        sb.append("id:");
        sb.append(this.id);
        sb.append("|");
        sb.append("host_id:");
        sb.append(this.g);
        sb.append("|");
        sb.append("ip:");
        sb.append(this.m);
        sb.append("|");
        sb.append("ttl:");
        sb.append(this.n);
        sb.append("|");
        return sb.toString();
    }
}
