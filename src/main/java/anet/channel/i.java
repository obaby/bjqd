package anet.channel;

import anet.channel.util.HttpConstant;
import anetwork.channel.cache.CachePrediction;
import java.util.Map;

/* compiled from: Taobao */
class i implements CachePrediction {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ h f191a;

    i(h hVar) {
        this.f191a = hVar;
    }

    public boolean handleCache(String str, Map<String, String> map) {
        return "weex".equals(map.get(HttpConstant.F_REFER));
    }
}
