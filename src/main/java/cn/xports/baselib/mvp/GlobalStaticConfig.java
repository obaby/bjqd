package cn.xports.baselib.mvp;

import android.content.SharedPreferences;
import cn.xports.baselib.App;
import java.util.HashMap;
import java.util.Map;

public class GlobalStaticConfig {
    private String apiKey;
    private String baseUrl;
    private String cApiKey;
    private String cSecretKey;
    private boolean isDebug;
    private Map<String, String> multiBaseUrl;
    private SharedPreferences preferences;
    private String secretKey;

    public String getcApiKey() {
        if (this.cApiKey == null) {
            this.cApiKey = this.preferences.getString("cApiKey", "");
        }
        return this.cApiKey;
    }

    public GlobalStaticConfig setcApiKey(String str) {
        this.cApiKey = str;
        this.preferences.edit().putString("cApiKey", str).apply();
        return this;
    }

    public String getcSecretKey() {
        if (this.cSecretKey == null) {
            this.cSecretKey = this.preferences.getString("cSecretKey", "");
        }
        return this.cSecretKey;
    }

    public GlobalStaticConfig setcSecretKey(String str) {
        this.cSecretKey = str;
        this.preferences.edit().putString("cSecretKey", str).apply();
        return this;
    }

    private GlobalStaticConfig() {
        this.multiBaseUrl = new HashMap();
        this.preferences = App.getInstance().getSharedPreferences("xports_config", 0);
    }

    public static GlobalStaticConfig getInstance() {
        return Holder.config;
    }

    public String getApiKey() {
        if (this.apiKey == null) {
            this.apiKey = this.preferences.getString("apiKey", "");
        }
        return this.apiKey;
    }

    public GlobalStaticConfig setApiKey(String str) {
        this.apiKey = str;
        this.preferences.edit().putString("apiKey", str).apply();
        return this;
    }

    public String getSecretKey() {
        if (this.secretKey == null) {
            this.secretKey = this.preferences.getString("secretKey", "");
        }
        return this.secretKey;
    }

    public GlobalStaticConfig setSecretKey(String str) {
        this.secretKey = str;
        this.preferences.edit().putString("secretKey", str).apply();
        return this;
    }

    public String getBaseUrl() {
        if (this.baseUrl == null) {
            this.baseUrl = this.preferences.getString("baseUrl", "");
        }
        return this.preferences.getString("baseUrl", "");
    }

    public GlobalStaticConfig setBaseUrl(String str) {
        this.baseUrl = str;
        this.preferences.edit().putString("baseUrl", str).apply();
        return this;
    }

    public Map<String, String> getMultiBaseUrl() {
        return this.multiBaseUrl;
    }

    public GlobalStaticConfig addMultiBaseUrl(String str, String str2) {
        this.multiBaseUrl.put(str, str2);
        return this;
    }

    public boolean isDebug() {
        return this.isDebug;
    }

    public GlobalStaticConfig setDebug(boolean z) {
        this.isDebug = z;
        return this;
    }

    private static class Holder {
        /* access modifiers changed from: private */
        public static GlobalStaticConfig config = new GlobalStaticConfig();

        private Holder() {
        }
    }
}
