package anet.channel.statist;

import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.k;

@Monitor(module = "networkPrefer", monitorPoint = "horseRace")
/* compiled from: Taobao */
public class HorseRaceStat extends StatObject {
    @Dimension
    public volatile String bssid = NetworkStatusHelper.getWifiBSSID();
    @Dimension
    public volatile int connErrorCode;
    @Dimension
    public volatile int connRet = 0;
    @Measure
    public volatile long connTime;
    @Dimension
    public volatile String host;
    @Dimension
    public volatile String ip;
    @Dimension
    public volatile String mnc = NetworkStatusHelper.getCarrier();
    @Dimension
    public volatile String nettype = NetworkStatusHelper.getNetworkSubType();
    @Dimension
    public volatile String path;
    @Dimension
    public volatile int port;
    @Dimension
    public volatile String protocol;
    @Dimension
    public volatile int reqErrorCode;
    @Dimension
    public volatile int reqRet = 0;
    @Measure
    public volatile long reqTime;

    public HorseRaceStat(String str, k.e eVar) {
        this.host = str;
        this.ip = eVar.f318a;
        this.port = eVar.f319b.f307a;
        this.protocol = ConnProtocol.valueOf(eVar.f319b).name;
        this.path = eVar.f320c;
    }
}
