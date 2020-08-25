package cn.xports.baselib.util;

import android.content.Intent;
import android.net.Uri;
import cn.xports.baselib.App;

public class GlobalMapUtil {
    public static boolean hasMap() {
        return AppCheckUtil.checkAppInstall(AppCheckUtil.BDMAP) || AppCheckUtil.checkAppInstall(AppCheckUtil.GDMAP) || AppCheckUtil.checkAppInstall("com.tencent.map");
    }

    public static void openMap(Double d, Double d2, String str) {
        if (d == null || d2 == null) {
            ToastUtil.showMsg("该场馆未配置经纬度");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:" + d + "," + d2 + "?q=" + str));
        intent.setFlags(805306368);
        if (intent.resolveActivity(App.getInstance().getPackageManager()) != null) {
            App.getInstance().startActivity(intent);
        } else {
            ToastUtil.showMsg("您的手机好像没有安装地图");
        }
    }

    public static void goToBaiduMap(Double d, Double d2, String str) {
        if (!AppCheckUtil.checkAppInstall(AppCheckUtil.BDMAP)) {
            ToastUtil.showMsg("请先安装百度地图客户端");
            return;
        }
        Intent intent = new Intent();
        intent.setFlags(805306368);
        intent.setData(Uri.parse("baidumap://map/marker?location=" + d + "," + d2 + "&title=" + str + "&src=" + App.getInstance().getPackageName() + "&coord_type=gcj02"));
        App.getInstance().startActivity(intent);
    }

    public static void goToGaodeMap(Double d, Double d2, String str) {
        if (!AppCheckUtil.checkAppInstall(AppCheckUtil.GDMAP)) {
            ToastUtil.showMsg("请先安装高德地图客户端");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("androidamap://viewMap?sourceApplication=amap&lat=" + d + "&lon=" + d2 + "&poiname=" + str + "&dev=0"));
        intent.setFlags(805306368);
        intent.setPackage(AppCheckUtil.GDMAP);
        App.getInstance().startActivity(intent);
    }

    public static void goToQQMap(Double d, Double d2, String str) {
        if (!AppCheckUtil.checkAppInstall("com.tencent.map")) {
            ToastUtil.showMsg("请先安装腾讯地图客户端");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("qqmap://map/marker?marker=coord:" + d + "," + d2 + ";title:" + str));
        intent.setFlags(805306368);
        intent.setPackage("com.tencent.map");
        App.getInstance().startActivity(intent);
    }
}
