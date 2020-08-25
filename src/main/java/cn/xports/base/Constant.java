package cn.xports.base;

import cn.xports.qdplugin.R;
import java.util.HashMap;
import java.util.Map;

public class Constant {
    public static final String ALI_PAY = "22";
    public static String APPID = "";
    public static final String APPLICATION_ID = "cn.xports.app.qingdao.android";
    public static final int CHANNEL_ID = 7;
    public static final String COUPON_PAY = "10";
    public static final String DEPOSIT_PAY = "12";
    public static final String ECARD_PAY = "11";
    public static String PAYMODES = "";
    public static final String PERIOD_PAY = "periodCard";
    public static final String TIMES_PAY = "timesCard";
    public static final String WECHAT_PAY = "17";
    private static Map<String, Integer> venueAdditionalService = new HashMap();

    static {
        venueAdditionalService.put("equipment_rental", Integer.valueOf(R.drawable.ic_equipment_rental));
        venueAdditionalService.put("shower", Integer.valueOf(R.drawable.ic_shower));
        venueAdditionalService.put("cabinet_rental", Integer.valueOf(R.drawable.ic_cabinet_rental));
        venueAdditionalService.put("air_conditioner", Integer.valueOf(R.drawable.ic_air_conditioner));
        venueAdditionalService.put("retail", Integer.valueOf(R.drawable.ic_retail));
        venueAdditionalService.put("wifi", Integer.valueOf(R.drawable.ic_wifi));
        venueAdditionalService.put("parking", Integer.valueOf(R.drawable.ic_parking));
    }

    public static int getAdditionalService(String str) {
        if (venueAdditionalService.containsKey(str)) {
            return venueAdditionalService.get(str).intValue();
        }
        return R.drawable.bg_default;
    }
}
