package anet.channel;

/* compiled from: Taobao */
public class AwcnConfig {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f109a = false;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f110b = true;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f111c = true;

    public static boolean isAccsSessionCreateForbiddenInBg() {
        return f109a;
    }

    public static void setAccsSessionCreateForbiddenInBg(boolean z) {
        f109a = z;
    }

    public static void setHttpsSniEnable(boolean z) {
        f110b = z;
    }

    public static boolean isHttpsSniEnable() {
        return f110b;
    }

    public static boolean isHorseRaceEnable() {
        return f111c;
    }

    public static void setHorseRaceEnable(boolean z) {
        f111c = z;
    }
}
