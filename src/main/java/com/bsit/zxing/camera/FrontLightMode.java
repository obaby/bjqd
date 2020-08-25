package com.bsit.zxing.camera;

import android.content.SharedPreferences;
import com.bsit.zxing.config.Config;

public enum FrontLightMode {
    ON,
    AUTO,
    OFF;

    private static FrontLightMode parse(String str) {
        return str == null ? OFF : valueOf(str);
    }

    public static FrontLightMode readPref(SharedPreferences sharedPreferences) {
        return parse(sharedPreferences.getString(Config.KEY_FRONT_LIGHT_MODE, (String) null));
    }
}
