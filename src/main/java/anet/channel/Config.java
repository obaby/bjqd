package anet.channel;

import android.text.TextUtils;
import anet.channel.entity.ENV;
import anet.channel.security.ISecurity;
import anet.channel.security.c;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public final class Config {
    public static final Config DEFAULT_CONFIG = new Builder().setTag("[default]").setAppkey("[default]").setEnv(ENV.ONLINE).build();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static Map<String, Config> f112a = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public String f113b;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public String f114c;
    /* access modifiers changed from: private */
    public ENV d = ENV.ONLINE;
    /* access modifiers changed from: private */
    public ISecurity e;

    protected Config() {
    }

    public static Config getConfigByTag(String str) {
        Config config;
        synchronized (f112a) {
            config = f112a.get(str);
        }
        return config;
    }

    public static Config getConfig(String str, ENV env) {
        synchronized (f112a) {
            for (Config next : f112a.values()) {
                if (next.d == env && next.f114c.equals(str)) {
                    return next;
                }
            }
            return null;
        }
    }

    public String getTag() {
        return this.f113b;
    }

    public String getAppkey() {
        return this.f114c;
    }

    public ENV getEnv() {
        return this.d;
    }

    public ISecurity getSecurity() {
        return this.e;
    }

    public String toString() {
        return this.f113b;
    }

    /* compiled from: Taobao */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private String f115a;

        /* renamed from: b  reason: collision with root package name */
        private String f116b;

        /* renamed from: c  reason: collision with root package name */
        private ENV f117c = ENV.ONLINE;
        private String d;
        private String e;

        public Builder setTag(String str) {
            this.f115a = str;
            return this;
        }

        public Builder setAppkey(String str) {
            this.f116b = str;
            return this;
        }

        public Builder setEnv(ENV env) {
            this.f117c = env;
            return this;
        }

        public Builder setAuthCode(String str) {
            this.d = str;
            return this;
        }

        public Builder setAppSecret(String str) {
            this.e = str;
            return this;
        }

        public Config build() {
            if (!TextUtils.isEmpty(this.f116b)) {
                for (Config config : Config.f112a.values()) {
                    if (config.d == this.f117c && config.f114c.equals(this.f116b)) {
                        ALog.w("awcn.Config", "duplicated config exist!", (String) null, "appkey", this.f116b, "env", this.f117c);
                        if (!TextUtils.isEmpty(this.f115a)) {
                            synchronized (Config.f112a) {
                                Config.f112a.put(this.f115a, config);
                            }
                        }
                        return config;
                    }
                }
                Config config2 = new Config();
                String unused = config2.f114c = this.f116b;
                ENV unused2 = config2.d = this.f117c;
                if (TextUtils.isEmpty(this.f115a)) {
                    String unused3 = config2.f113b = StringUtils.concatString(this.f116b, "$", this.f117c.toString());
                } else {
                    String unused4 = config2.f113b = this.f115a;
                }
                if (!TextUtils.isEmpty(this.e)) {
                    ISecurity unused5 = config2.e = c.a().createNonSecurity(this.e);
                } else {
                    ISecurity unused6 = config2.e = c.a().createSecurity(this.d);
                }
                synchronized (Config.f112a) {
                    Config.f112a.put(config2.f113b, config2);
                }
                return config2;
            }
            throw new RuntimeException("appkey can not be null or empty!");
        }
    }
}
