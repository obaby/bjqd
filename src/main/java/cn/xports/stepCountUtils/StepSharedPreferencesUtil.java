package cn.xports.stepCountUtils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

public class StepSharedPreferencesUtil {
    public static final String FILE_NAME = "step_shared_data";

    public static void setParam(Context context, String str, Object obj) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        if (obj instanceof String) {
            edit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else {
            edit.putString(str, obj.toString());
        }
        edit.apply();
    }

    public static Object getParam(Context context, String str, Object obj) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
            if (obj instanceof String) {
                return sharedPreferences.getString(str, (String) obj);
            }
            if (obj instanceof Integer) {
                return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
            }
            if (obj instanceof Boolean) {
                return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
            }
            if (obj instanceof Float) {
                return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
            }
            if (obj instanceof Long) {
                return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void remove(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.remove(str);
        edit.apply();
    }

    public static void clear(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.clear();
        edit.apply();
    }

    public static boolean contains(Context context, String str) {
        return context.getSharedPreferences(FILE_NAME, 0).contains(str);
    }

    public static Map<String, ?> getAll(Context context) {
        return context.getSharedPreferences(FILE_NAME, 0).getAll();
    }
}
