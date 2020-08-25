package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.strategy.k;
import anet.channel.util.HttpConstant;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public class ConnProtocol implements Serializable {
    public static final ConnProtocol HTTP = valueOf(HttpConstant.HTTP, (String) null, (String) null);
    public static final ConnProtocol HTTPS = valueOf("https", (String) null, (String) null);
    private static Map<String, ConnProtocol> protocolMap = new HashMap();
    private static final long serialVersionUID = -3523201990674557001L;
    final int isHttp;
    public final String name;
    public final String protocol;
    public final String publicKey;
    public final String rtt;

    public static ConnProtocol valueOf(k.a aVar) {
        if (aVar == null) {
            return null;
        }
        return valueOf(aVar.f308b, aVar.g, aVar.h);
    }

    @Deprecated
    public static ConnProtocol valueOf(String str, String str2, String str3, @Deprecated boolean z) {
        return valueOf(str, str2, str3);
    }

    public static ConnProtocol valueOf(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String buildName = buildName(str, str2, str3);
        synchronized (protocolMap) {
            if (protocolMap.containsKey(buildName)) {
                ConnProtocol connProtocol = protocolMap.get(buildName);
                return connProtocol;
            }
            ConnProtocol connProtocol2 = new ConnProtocol(buildName, str, str2, str3);
            protocolMap.put(buildName, connProtocol2);
            return connProtocol2;
        }
    }

    private ConnProtocol(String str, String str2, String str3, String str4) {
        this.name = str;
        this.protocol = str2;
        this.rtt = str3;
        this.publicKey = str4;
        this.isHttp = (HttpConstant.HTTP.equalsIgnoreCase(str2) || "https".equalsIgnoreCase(str2)) ? 1 : 0;
    }

    private static String buildName(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(18);
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append("_");
            sb.append(str2);
        } else {
            sb.append("_0rtt");
        }
        sb.append("_");
        sb.append(str3);
        return sb.toString();
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ConnProtocol)) {
            return false;
        }
        return this.name.equals(((ConnProtocol) obj).name);
    }

    public int hashCode() {
        int hashCode = 527 + this.protocol.hashCode();
        if (this.rtt != null) {
            hashCode = (hashCode * 31) + this.rtt.hashCode();
        }
        return this.publicKey != null ? (hashCode * 31) + this.publicKey.hashCode() : hashCode;
    }
}
