package cn.xports.baselib.util;

import android.nfc.tech.MifareClassic;
import cn.xports.baselib.App;
import java.util.HashMap;

public class CardDecodeUtil {
    public static int getDataLength(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("10000000", 12);
        hashMap.put("20000000", 12);
        hashMap.put("61010010", 12);
        if (hashMap.containsKey(str)) {
            return ((Integer) hashMap.get(str)).intValue();
        }
        return 16;
    }

    public static int getDataLength() {
        return getDataLength(App.getInstance().getSharedPreferences("userInfo", 0).getString("centerId", ""));
    }

    public static byte[] getDecodeKey(String str) {
        byte[] hexStr2bytes = ByteUtils.hexStr2bytes("898884495051");
        byte[] hexStr2bytes2 = ByteUtils.hexStr2bytes("0a2b0c4d8219");
        HashMap hashMap = new HashMap();
        hashMap.put("20000000", MifareClassic.KEY_DEFAULT);
        hashMap.put("37020001", hexStr2bytes2);
        if ("".contains("Dev")) {
            hashMap.put("10000000", MifareClassic.KEY_DEFAULT);
        }
        return hashMap.containsKey(str) ? (byte[]) hashMap.get(str) : hexStr2bytes;
    }

    public static byte[] getDecodeKey() {
        return getDecodeKey(App.getInstance().getSharedPreferences("userInfo", 0).getString("centerId", ""));
    }
}
