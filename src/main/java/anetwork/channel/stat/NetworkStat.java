package anetwork.channel.stat;

/* compiled from: Taobao */
public class NetworkStat {
    public static INetworkStat getNetworkStat() {
        return NetworkStatCache.getInstance();
    }
}
