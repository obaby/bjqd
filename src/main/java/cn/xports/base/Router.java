package cn.xports.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.blankj.utilcode.util.ActivityUtils;
import java.util.HashMap;
import java.util.Map;

public class Router {
    public static final String ACTION = "cn.xports.qdgx.action";
    public static final String AGREEMENT = "/agreement";
    public static final String CARD_BUY_DETAIL = "/cardBuyDetail";
    public static final String CARD_PACKAGE = "/cardPackage";
    public static final String HOST = "cn.xports.qdgx";
    public static final String LOGIN = "/login";
    public static final String ORDER_PAY = "/orderPay";
    public static final String SCHEME = "xports";
    public static final String SELECT_BOTTOM = "/selectBottom";
    public static final String SELECT_CARD_PAY = "/selectCardPay";

    public static Intent getIntent(String str) {
        Intent intent = new Intent();
        intent.setAction(ACTION);
        intent.setData(Uri.parse("xports://cn.xports.qdgx" + str));
        return intent;
    }

    public static Intent getIntent(String str, Map<String, String> map) {
        Intent intent = new Intent();
        intent.setAction(ACTION);
        StringBuilder sb = new StringBuilder("xports://cn.xports.qdgx" + str);
        if (map != null && map.keySet().size() > 0) {
            if (!sb.toString().endsWith("?")) {
                sb.append("?");
            }
            for (String next : map.keySet()) {
                sb.append("&");
                sb.append(next);
                sb.append("=");
                sb.append(map.get(next));
            }
        }
        intent.setData(Uri.parse(sb.toString()));
        return intent;
    }

    public static Intent startWithLogin(String str, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("path", str);
        return getIntent(LOGIN, map);
    }

    public static void startWithNoAnim(Intent intent) {
        ActivityUtils.startActivity(intent);
        ActivityUtils.getTopActivity().overridePendingTransition(0, 0);
    }

    public static void finishWithNoAnim(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(0, 0);
    }
}
