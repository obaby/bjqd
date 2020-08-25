package ah;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import com.bsit.zxing.config.Config;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import com.linewell.licence.ui.zxing.ZxingActivity;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public final class c extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public static final String f93a = "barcode_bitmap";

    /* renamed from: b  reason: collision with root package name */
    public static final String f94b = "barcode_scaled_factor";

    /* renamed from: c  reason: collision with root package name */
    private final ZxingActivity f95c;
    private final Map<DecodeHintType, Object> d = new EnumMap(DecodeHintType.class);
    private Handler e;
    private final CountDownLatch f = new CountDownLatch(1);

    public c(ZxingActivity zxingActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, ResultPointCallback resultPointCallback) {
        this.f95c = zxingActivity;
        if (map != null) {
            this.d.putAll(map);
        }
        if (collection == null || collection.isEmpty()) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(zxingActivity);
            collection = EnumSet.noneOf(BarcodeFormat.class);
            if (defaultSharedPreferences.getBoolean(Config.KEY_DECODE_1D_PRODUCT, true)) {
                collection.addAll(a.f87a);
            }
            if (defaultSharedPreferences.getBoolean(Config.KEY_DECODE_1D_INDUSTRIAL, true)) {
                collection.addAll(a.f88b);
            }
            if (defaultSharedPreferences.getBoolean(Config.KEY_DECODE_QR, true)) {
                collection.addAll(a.f89c);
            }
            if (defaultSharedPreferences.getBoolean(Config.KEY_DECODE_DATA_MATRIX, true)) {
                collection.addAll(a.d);
            }
            if (defaultSharedPreferences.getBoolean(Config.KEY_DECODE_AZTEC, false)) {
                collection.addAll(a.e);
            }
            if (defaultSharedPreferences.getBoolean(Config.KEY_DECODE_PDF417, false)) {
                collection.addAll(a.f);
            }
        }
        this.d.put(DecodeHintType.POSSIBLE_FORMATS, collection);
        if (str != null) {
            this.d.put(DecodeHintType.CHARACTER_SET, str);
        }
        this.d.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, resultPointCallback);
        Log.i("DecodeThread", "Hints: " + this.d);
    }

    public Handler a() {
        try {
            this.f.await();
        } catch (InterruptedException unused) {
        }
        return this.e;
    }

    public void run() {
        Looper.prepare();
        this.e = new b(this.f95c, this.d);
        this.f.countDown();
        Looper.loop();
    }
}
