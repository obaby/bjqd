package cn.xports.baselib.util;

import android.content.pm.PackageInfo;
import cn.xports.baselib.App;
import java.util.List;

public class AppCheckUtil {
    public static final String ALIPAY = "com.eg.android.AlipayGphone";
    public static final String BDMAP = "com.baidu.BaiduMap";
    public static final String GDMAP = "com.autonavi.minimap";
    public static final String WECHAT = "com.tencent.mm";

    public static boolean checkAppInstall(String str) {
        List<PackageInfo> installedPackages;
        if (str == null || str.isEmpty() || (installedPackages = App.getInstance().getPackageManager().getInstalledPackages(0)) == null || installedPackages.isEmpty()) {
            return false;
        }
        for (int i = 0; i < installedPackages.size(); i++) {
            if (str.equals(installedPackages.get(i).packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAliPayInstall() {
        return checkAppInstall(ALIPAY);
    }
}
