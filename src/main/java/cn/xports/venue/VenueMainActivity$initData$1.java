package cn.xports.venue;

import cn.xports.baselib.http.HttpParamExtent;
import cn.xports.baselib.util.SPUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "", "getPublicParams"}, k = 3, mv = {1, 1, 15})
/* compiled from: VenueMainActivity.kt */
final class VenueMainActivity$initData$1 implements HttpParamExtent {
    public static final VenueMainActivity$initData$1 INSTANCE = new VenueMainActivity$initData$1();

    VenueMainActivity$initData$1() {
    }

    @NotNull
    public final Map<String, String> getPublicParams() {
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        String stringValue = SPUtil.Companion.getINSTANCE().getStringValue("centerId");
        if (stringValue == null) {
            stringValue = "";
        }
        linkedHashMap.put("centerId", stringValue);
        linkedHashMap.put("channelId", "7");
        String stringValue2 = SPUtil.Companion.getINSTANCE().getStringValue("appId");
        if (stringValue2 == null) {
            stringValue2 = "";
        }
        linkedHashMap.put("appId", stringValue2);
        String stringValue3 = SPUtil.Companion.getINSTANCE().getStringValue("netUserId");
        if (stringValue3 == null) {
            stringValue3 = "";
        }
        linkedHashMap.put("netUserId", stringValue3);
        String stringValue4 = SPUtil.Companion.getINSTANCE().getStringValue("accessToken");
        if (stringValue4 == null) {
            stringValue4 = "";
        }
        linkedHashMap.put("accessToken", stringValue4);
        String stringValue5 = SPUtil.Companion.getINSTANCE().getStringValue("coAccountId");
        if (stringValue5 == null) {
            stringValue5 = "";
        }
        linkedHashMap.put("accountId", stringValue5);
        String stringValue6 = SPUtil.Companion.getINSTANCE().getStringValue("coAppId");
        if (stringValue6 == null) {
            stringValue6 = "";
        }
        linkedHashMap.put("coAppId", stringValue6);
        return linkedHashMap;
    }
}
