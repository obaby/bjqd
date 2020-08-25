package anet.channel.flow;

import anet.channel.statist.RequestStatistic;

/* compiled from: Taobao */
public class FlowStat {
    public long downstream;
    public String protocoltype;
    public String refer;
    public String req_identifier;
    public long upstream;

    public FlowStat() {
    }

    public FlowStat(String str, RequestStatistic requestStatistic) {
        this.refer = str;
        this.protocoltype = requestStatistic.protocolType;
        this.req_identifier = requestStatistic.url;
        this.upstream = requestStatistic.sendDataSize;
        this.downstream = requestStatistic.recDataSize;
    }

    public String toString() {
        return "FlowStat{refer='" + this.refer + '\'' + ", protocoltype='" + this.protocoltype + '\'' + ", req_identifier='" + this.req_identifier + '\'' + ", upstream=" + this.upstream + ", downstream=" + this.downstream + '}';
    }
}
