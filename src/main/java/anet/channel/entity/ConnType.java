package anet.channel.entity;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.strategy.ConnProtocol;
import anet.channel.util.HttpConstant;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public class ConnType {
    public static final String H2S = "h2s";
    public static ConnType HTTP = new ConnType(HttpConstant.HTTP);
    public static final String HTTP2 = "http2";
    public static ConnType HTTPS = new ConnType("https");
    public static final String PK_ACS = "acs";
    public static final String PK_AUTO = "auto";
    public static final String PK_CDN = "cdn";
    public static final String PK_OPEN = "open";
    public static final String RTT_0 = "0rtt";
    public static final String RTT_1 = "1rtt";
    public static final String SPDY = "spdy";
    private static Map<ConnProtocol, ConnType> connTypeMap = new HashMap();
    private String name = "";
    private String publicKey;
    private int spdyProtocol;

    @Deprecated
    /* compiled from: Taobao */
    public enum TypeLevel {
        SPDY,
        HTTP
    }

    private ConnType(String str) {
        this.name = str;
    }

    public static ConnType valueOf(ConnProtocol connProtocol) {
        if (connProtocol == null) {
            return null;
        }
        if (HttpConstant.HTTP.equalsIgnoreCase(connProtocol.protocol)) {
            return HTTP;
        }
        if ("https".equalsIgnoreCase(connProtocol.protocol)) {
            return HTTPS;
        }
        synchronized (connTypeMap) {
            if (connTypeMap.containsKey(connProtocol)) {
                ConnType connType = connTypeMap.get(connProtocol);
                return connType;
            }
            ConnType connType2 = new ConnType(connProtocol.toString());
            connType2.publicKey = connProtocol.publicKey;
            if (HTTP2.equalsIgnoreCase(connProtocol.protocol)) {
                connType2.spdyProtocol |= 8;
            } else if (SPDY.equalsIgnoreCase(connProtocol.protocol)) {
                connType2.spdyProtocol |= 2;
            } else if (H2S.equals(connProtocol.protocol)) {
                connType2.spdyProtocol = 40;
            }
            if (connType2.spdyProtocol == 0) {
                return null;
            }
            if (!TextUtils.isEmpty(connProtocol.publicKey)) {
                connType2.spdyProtocol |= 128;
                if (RTT_1.equalsIgnoreCase(connProtocol.rtt)) {
                    connType2.spdyProtocol |= 8192;
                } else if (!RTT_0.equalsIgnoreCase(connProtocol.rtt)) {
                    return null;
                } else {
                    connType2.spdyProtocol |= 4096;
                }
            }
            connTypeMap.put(connProtocol, connType2);
            return connType2;
        }
    }

    public int getTnetConType() {
        return this.spdyProtocol;
    }

    public int getTnetPublicKey(boolean z) {
        if (PK_CDN.equals(this.publicKey)) {
            return 1;
        }
        if (GlobalAppRuntimeInfo.getEnv() == ENV.TEST) {
            return 0;
        }
        if (PK_OPEN.equals(this.publicKey)) {
            return z ? 11 : 10;
        }
        if (PK_ACS.equals(this.publicKey)) {
            return z ? 4 : 3;
        }
        return -1;
    }

    public boolean isPublicKeyAuto() {
        return PK_AUTO.equals(this.publicKey);
    }

    public boolean isH2S() {
        return this.spdyProtocol == 40;
    }

    public boolean isHttpType() {
        return equals(HTTP) || equals(HTTPS);
    }

    public boolean isSSL() {
        return ((this.spdyProtocol & 128) == 0 && (this.spdyProtocol & 32) == 0 && !equals(HTTPS)) ? false : true;
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ConnType)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.name.equals(((ConnType) obj).name);
    }

    @Deprecated
    public TypeLevel getTypeLevel() {
        if (isHttpType()) {
            return TypeLevel.HTTP;
        }
        return TypeLevel.SPDY;
    }

    public int getType() {
        if (equals(HTTP) || equals(HTTPS)) {
            return c.f182b;
        }
        return c.f181a;
    }

    private int getPriority() {
        if ((this.spdyProtocol & 8) == 0) {
            return 0;
        }
        return (this.spdyProtocol & 2) == 0 ? 1 : 2;
    }

    public static int compare(ConnType connType, ConnType connType2) {
        return connType.getPriority() - connType2.getPriority();
    }
}
