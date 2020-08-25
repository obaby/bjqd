package anet.channel.c;

import anet.channel.util.ALog;
import anetwork.channel.config.IRemoteConfig;
import anetwork.channel.util.RequestConstant;
import com.taobao.orange.OrangeConfig;

/* compiled from: Taobao */
public class a implements IRemoteConfig {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f158a;

    static {
        try {
            Class.forName("com.taobao.orange.OrangeConfig");
            f158a = true;
        } catch (Exception unused) {
            f158a = false;
        }
    }

    public void register() {
        if (!f158a) {
            ALog.w("awcn.OrangeConfigImpl", "no orange sdk", (String) null, new Object[0]);
            return;
        }
        try {
            OrangeConfig.getInstance().registerListener(new String[]{"networkSdk"}, new b(this));
            getConfig("networkSdk", "network_empty_scheme_https_switch", RequestConstant.TRUE);
        } catch (Exception e) {
            ALog.e("awcn.OrangeConfigImpl", "register fail", (String) null, e, new Object[0]);
        }
    }

    public void unRegister() {
        if (!f158a) {
            ALog.w("awcn.OrangeConfigImpl", "no orange sdk", (String) null, new Object[0]);
        } else {
            OrangeConfig.getInstance().unregisterListener(new String[]{"networkSdk"});
        }
    }

    public String getConfig(String... strArr) {
        if (!f158a) {
            ALog.w("awcn.OrangeConfigImpl", "no orange sdk", (String) null, new Object[0]);
            return null;
        }
        try {
            return OrangeConfig.getInstance().getConfig(strArr[0], strArr[1], strArr[2]);
        } catch (Exception e) {
            ALog.e("awcn.OrangeConfigImpl", "get config failed!", (String) null, e, new Object[0]);
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(25:2|3|4|5|6|7|8|9|10|(1:12)|13|14|15|16|17|18|19|20|(1:22)|23|24|25|26|(1:28)|(3:29|30|32)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(27:2|3|4|5|6|7|8|9|10|(1:12)|13|14|15|16|17|18|19|20|(1:22)|23|24|25|26|(1:28)|29|30|32) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x008a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x00a5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x00c0 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x00db */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x00fa */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x010b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0122 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0039 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0054 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x006f */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x007f A[Catch:{ Exception -> 0x008a }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ef A[Catch:{ Exception -> 0x00fa }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x011f A[Catch:{ Exception -> 0x0122 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConfigUpdate(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "networkSdk"
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x013d
            java.lang.String r0 = "awcn.OrangeConfigImpl"
            java.lang.String r1 = "onConfigUpdate"
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = "namespace"
            r5 = 0
            r3[r5] = r4
            r4 = 1
            r3[r4] = r10
            r6 = 0
            anet.channel.util.ALog.i(r0, r1, r6, r3)
            r0 = 3
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0039 }
            r1[r5] = r10     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "network_empty_scheme_https_switch"
            r1[r4] = r3     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "true"
            r1[r2] = r3     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x0039 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0039 }
            boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x0039 }
            anet.channel.strategy.c r3 = anet.channel.strategy.c.a.f278a     // Catch:{ Exception -> 0x0039 }
            r3.a((boolean) r1)     // Catch:{ Exception -> 0x0039 }
        L_0x0039:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0054 }
            r1[r5] = r10     // Catch:{ Exception -> 0x0054 }
            java.lang.String r3 = "network_spdy_enable_switch"
            r1[r4] = r3     // Catch:{ Exception -> 0x0054 }
            java.lang.String r3 = "true"
            r1[r2] = r3     // Catch:{ Exception -> 0x0054 }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x0054 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0054 }
            boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x0054 }
            anetwork.channel.config.NetworkConfigCenter.setSpdyEnabled(r1)     // Catch:{ Exception -> 0x0054 }
        L_0x0054:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x006f }
            r1[r5] = r10     // Catch:{ Exception -> 0x006f }
            java.lang.String r3 = "network_http_cache_switch"
            r1[r4] = r3     // Catch:{ Exception -> 0x006f }
            java.lang.String r3 = "true"
            r1[r2] = r3     // Catch:{ Exception -> 0x006f }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x006f }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x006f }
            boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x006f }
            anetwork.channel.config.NetworkConfigCenter.setHttpCacheEnable(r1)     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x008a }
            r1[r5] = r10     // Catch:{ Exception -> 0x008a }
            java.lang.String r3 = "network_http_cache_flag"
            r1[r4] = r3     // Catch:{ Exception -> 0x008a }
            r1[r2] = r6     // Catch:{ Exception -> 0x008a }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x008a }
            if (r1 == 0) goto L_0x008a
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ Exception -> 0x008a }
            long r7 = r1.longValue()     // Catch:{ Exception -> 0x008a }
            anetwork.channel.config.NetworkConfigCenter.setCacheFlag(r7)     // Catch:{ Exception -> 0x008a }
        L_0x008a:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x00a5 }
            r1[r5] = r10     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r3 = "network_https_sni_enable_switch"
            r1[r4] = r3     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r3 = "true"
            r1[r2] = r3     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x00a5 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00a5 }
            boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00a5 }
            anet.channel.AwcnConfig.setHttpsSniEnable(r1)     // Catch:{ Exception -> 0x00a5 }
        L_0x00a5:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x00c0 }
            r1[r5] = r10     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r3 = "network_accs_session_bg_switch"
            r1[r4] = r3     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r3 = "true"
            r1[r2] = r3     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x00c0 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00c0 }
            boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00c0 }
            anet.channel.AwcnConfig.setAccsSessionCreateForbiddenInBg(r1)     // Catch:{ Exception -> 0x00c0 }
        L_0x00c0:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x00db }
            r1[r5] = r10     // Catch:{ Exception -> 0x00db }
            java.lang.String r3 = "network_request_statistic_sample_rate"
            r1[r4] = r3     // Catch:{ Exception -> 0x00db }
            java.lang.String r3 = "10000"
            r1[r2] = r3     // Catch:{ Exception -> 0x00db }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x00db }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x00db }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x00db }
            anetwork.channel.config.NetworkConfigCenter.setRequestStatisticSampleRate(r1)     // Catch:{ Exception -> 0x00db }
        L_0x00db:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x00fa }
            r1[r5] = r10     // Catch:{ Exception -> 0x00fa }
            java.lang.String r3 = "network_request_forbidden_bg"
            r1[r4] = r3     // Catch:{ Exception -> 0x00fa }
            r1[r2] = r6     // Catch:{ Exception -> 0x00fa }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x00fa }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x00fa }
            if (r3 != 0) goto L_0x00fa
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00fa }
            boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00fa }
            anetwork.channel.config.NetworkConfigCenter.setBgRequestForbidden(r1)     // Catch:{ Exception -> 0x00fa }
        L_0x00fa:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x010b }
            r1[r5] = r10     // Catch:{ Exception -> 0x010b }
            java.lang.String r3 = "network_url_white_list_bg"
            r1[r4] = r3     // Catch:{ Exception -> 0x010b }
            r1[r2] = r6     // Catch:{ Exception -> 0x010b }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x010b }
            anetwork.channel.config.NetworkConfigCenter.updateWhiteListMap(r1)     // Catch:{ Exception -> 0x010b }
        L_0x010b:
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0122 }
            r1[r5] = r10     // Catch:{ Exception -> 0x0122 }
            java.lang.String r3 = "network_amdc_preset_hosts"
            r1[r4] = r3     // Catch:{ Exception -> 0x0122 }
            r1[r2] = r6     // Catch:{ Exception -> 0x0122 }
            java.lang.String r1 = r9.getConfig(r1)     // Catch:{ Exception -> 0x0122 }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0122 }
            if (r3 != 0) goto L_0x0122
            anetwork.channel.config.NetworkConfigCenter.setAmdcPresetHosts(r1)     // Catch:{ Exception -> 0x0122 }
        L_0x0122:
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ Exception -> 0x013d }
            r0[r5] = r10     // Catch:{ Exception -> 0x013d }
            java.lang.String r10 = "network_horse_race_switch"
            r0[r4] = r10     // Catch:{ Exception -> 0x013d }
            java.lang.String r10 = "true"
            r0[r2] = r10     // Catch:{ Exception -> 0x013d }
            java.lang.String r10 = r9.getConfig(r0)     // Catch:{ Exception -> 0x013d }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ Exception -> 0x013d }
            boolean r10 = r10.booleanValue()     // Catch:{ Exception -> 0x013d }
            anet.channel.AwcnConfig.setHorseRaceEnable(r10)     // Catch:{ Exception -> 0x013d }
        L_0x013d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.c.a.onConfigUpdate(java.lang.String):void");
    }
}
