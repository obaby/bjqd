package anet.channel.strategy;

import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.util.HttpConstant;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Taobao */
public class HttpDnsAdapter {
    public static void setHosts(ArrayList<String> arrayList) {
        HttpDispatcher.getInstance().addHosts(arrayList);
    }

    public static HttpDnsOrigin getOriginByHttpDns(String str) {
        List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(str);
        if (connStrategyListByHost.isEmpty()) {
            return null;
        }
        return new HttpDnsOrigin(connStrategyListByHost.get(0));
    }

    public static ArrayList<HttpDnsOrigin> getOriginsByHttpDns(String str) {
        List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(str);
        if (connStrategyListByHost.isEmpty()) {
            return null;
        }
        ArrayList<HttpDnsOrigin> arrayList = new ArrayList<>(connStrategyListByHost.size());
        for (IConnStrategy httpDnsOrigin : connStrategyListByHost) {
            arrayList.add(new HttpDnsOrigin(httpDnsOrigin));
        }
        return arrayList;
    }

    public static String getIpByHttpDns(String str) {
        List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(str);
        if (connStrategyListByHost.isEmpty()) {
            return null;
        }
        return connStrategyListByHost.get(0).getIp();
    }

    /* compiled from: Taobao */
    public static final class HttpDnsOrigin {
        private final IConnStrategy connStrategy;

        HttpDnsOrigin(IConnStrategy iConnStrategy) {
            this.connStrategy = iConnStrategy;
        }

        public String getOriginIP() {
            return this.connStrategy.getIp();
        }

        public int getOriginPort() {
            return this.connStrategy.getPort();
        }

        public boolean canWithSPDY() {
            String str = this.connStrategy.getProtocol().protocol;
            return !str.equalsIgnoreCase(HttpConstant.HTTP) && !str.equalsIgnoreCase("https");
        }

        public String toString() {
            return this.connStrategy.toString();
        }
    }
}
