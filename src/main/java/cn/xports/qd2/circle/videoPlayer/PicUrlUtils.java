package cn.xports.qd2.circle.videoPlayer;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import cn.xports.baselib.util.SPUtil;

public class PicUrlUtils {
    public static String getPath(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith(HttpConstant.HTTP)) {
            return str;
        }
        String stringValue = SPUtil.Companion.getINSTANCE().getStringValue("ossUrl");
        if (TextUtils.isEmpty(stringValue)) {
            stringValue = "http://aisports.oss-cn-hangzhou.aliyuncs.com/";
        }
        return stringValue + str;
    }
}
