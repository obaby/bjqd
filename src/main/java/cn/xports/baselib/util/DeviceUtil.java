package cn.xports.baselib.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import anet.channel.strategy.dispatch.DispatchConstants;
import cn.xports.baselib.App;
import com.stub.StubApp;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

public final class DeviceUtil {
    private static final String KEY_UDID = "KEY_UDID";
    private static volatile String udid;

    private DeviceUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void hideSysInput(View view) {
        if (view.getWindowToken() != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }

    public static boolean isDeviceRooted() {
        for (String str : new String[]{"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"}) {
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = 17)
    public static boolean isAdbEnabled() {
        if (Settings.Secure.getInt(App.getInstance().getContentResolver(), "adb_enabled", 0) > 0) {
            return true;
        }
        return false;
    }

    public static String getSDKVersionName() {
        return Build.VERSION.RELEASE;
    }

    public static int getSDKVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID() {
        String string = Settings.Secure.getString(App.getInstance().getContentResolver(), "android_id");
        return (!"9774d56d682e549c".equals(string) && string != null) ? string : "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSerialNumber() {
        /*
            r0 = 0
            java.lang.String r1 = "android.os.SystemProperties"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x007e }
            java.lang.String r2 = "get"
            r3 = 1
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x007e }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r6 = 0
            r4[r6] = r5     // Catch:{ Exception -> 0x007e }
            java.lang.reflect.Method r2 = r1.getMethod(r2, r4)     // Catch:{ Exception -> 0x007e }
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x007e }
            java.lang.String r5 = "gsm.sn1"
            r4[r6] = r5     // Catch:{ Exception -> 0x007e }
            java.lang.Object r4 = r2.invoke(r1, r4)     // Catch:{ Exception -> 0x007e }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x007e }
            java.lang.String r5 = ""
            boolean r5 = r4.equals(r5)     // Catch:{ Exception -> 0x007e }
            if (r5 == 0) goto L_0x0035
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x007e }
            java.lang.String r5 = "ril.serialnumber"
            r4[r6] = r5     // Catch:{ Exception -> 0x007e }
            java.lang.Object r4 = r2.invoke(r1, r4)     // Catch:{ Exception -> 0x007e }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x007e }
        L_0x0035:
            java.lang.String r5 = ""
            boolean r5 = r4.equals(r5)     // Catch:{ Exception -> 0x007e }
            if (r5 == 0) goto L_0x0049
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x007e }
            java.lang.String r5 = "ro.serialno"
            r4[r6] = r5     // Catch:{ Exception -> 0x007e }
            java.lang.Object r4 = r2.invoke(r1, r4)     // Catch:{ Exception -> 0x007e }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x007e }
        L_0x0049:
            java.lang.String r5 = ""
            boolean r5 = r4.equals(r5)     // Catch:{ Exception -> 0x007e }
            if (r5 == 0) goto L_0x005e
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x007e }
            java.lang.String r4 = "sys.serialnumber"
            r3[r6] = r4     // Catch:{ Exception -> 0x007e }
            java.lang.Object r1 = r2.invoke(r1, r3)     // Catch:{ Exception -> 0x007e }
            r4 = r1
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x007e }
        L_0x005e:
            java.lang.String r1 = ""
            boolean r1 = r4.equals(r1)     // Catch:{ Exception -> 0x007e }
            if (r1 == 0) goto L_0x0073
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x007e }
            r2 = 25
            if (r1 <= r2) goto L_0x0071
            java.lang.String r4 = android.os.Build.getSerial()     // Catch:{ Exception -> 0x007e }
            goto L_0x0073
        L_0x0071:
            java.lang.String r4 = android.os.Build.SERIAL     // Catch:{ Exception -> 0x007e }
        L_0x0073:
            java.lang.String r1 = ""
            boolean r1 = r4.equals(r1)     // Catch:{ Exception -> 0x007e }
            if (r1 == 0) goto L_0x007c
            goto L_0x0082
        L_0x007c:
            r0 = r4
            goto L_0x0082
        L_0x007e:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0082:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.baselib.util.DeviceUtil.getSerialNumber():java.lang.String");
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static String getMacAddress() {
        String[] strArr = null;
        String macAddress = getMacAddress(strArr);
        if (!macAddress.equals("") || getWifiEnabled()) {
            return macAddress;
        }
        setWifiEnabled(true);
        setWifiEnabled(false);
        return getMacAddress(strArr);
    }

    private static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) App.getInstance().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    private static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) App.getInstance().getSystemService("wifi");
        if (wifiManager != null) {
            wifiManager.setWifiEnabled(z);
        }
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static String getMacAddress(String... strArr) {
        String macAddressByNetworkInterface = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddressByNetworkInterface, strArr)) {
            return macAddressByNetworkInterface;
        }
        String macAddressByInetAddress = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddressByInetAddress, strArr)) {
            return macAddressByInetAddress;
        }
        String macAddressByWifiInfo = getMacAddressByWifiInfo();
        return isAddressNotInExcepts(macAddressByWifiInfo, strArr) ? macAddressByWifiInfo : "";
    }

    private static boolean isAddressNotInExcepts(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return !"02:00:00:00:00:00".equals(str);
        }
        for (String equals : strArr) {
            if (str.equals(equals)) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private static String getMacAddressByWifiInfo() {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) StubApp.getOrigApplicationContext(App.getInstance().getApplicationContext()).getSystemService("wifi");
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return "02:00:00:00:00:00";
            }
            return connectionInfo.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    private static String getMacAddressByNetworkInterface() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement != null) {
                    if (nextElement.getName().equalsIgnoreCase("wlan0")) {
                        byte[] hardwareAddress = nextElement.getHardwareAddress();
                        if (hardwareAddress != null && hardwareAddress.length > 0) {
                            StringBuilder sb = new StringBuilder();
                            int length = hardwareAddress.length;
                            for (int i = 0; i < length; i++) {
                                sb.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                            }
                            return sb.substring(0, sb.length() - 1);
                        }
                    }
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    private static String getMacAddressByInetAddress() {
        NetworkInterface byInetAddress;
        byte[] hardwareAddress;
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress == null || (byInetAddress = NetworkInterface.getByInetAddress(inetAddress)) == null || (hardwareAddress = byInetAddress.getHardwareAddress()) == null || hardwareAddress.length <= 0) {
                return "02:00:00:00:00:00";
            }
            StringBuilder sb = new StringBuilder();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress() && nextElement2.getHostAddress().indexOf(58) < 0) {
                            return nextElement2;
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        String str = Build.MODEL;
        return str != null ? str.trim().replaceAll("\\s*", "") : "";
    }

    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
            return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
        return new String[]{Build.CPU_ABI};
    }

    public static boolean isTablet() {
        return (App.getInstance().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean isEmulator() {
        String networkOperatorName;
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT)) {
            return true;
        }
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) App.getInstance().getSystemService("phone");
        if (!(telephonyManager == null || (networkOperatorName = telephonyManager.getNetworkOperatorName()) == null)) {
            str = networkOperatorName;
        }
        if (str.toLowerCase().equals(DispatchConstants.ANDROID)) {
            return true;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        return intent.resolveActivity(App.getInstance().getPackageManager()) == null;
    }

    private static String getUdid(String str, String str2) {
        if (str2.equals("")) {
            return str + UUID.randomUUID().toString().replace("-", "");
        }
        return str + UUID.nameUUIDFromBytes(str2.getBytes()).toString().replace("-", "");
    }
}
