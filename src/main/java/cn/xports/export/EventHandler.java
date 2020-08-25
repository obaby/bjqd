package cn.xports.export;

import android.content.Context;
import android.content.Intent;
import cn.xports.base.Constant;
import cn.xports.base.Router;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.mvp.GlobalStaticConfig;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.baselib.util.SPUtil;
import cn.xports.http.SodaService;
import cn.xports.qdplugin.BuildConfig;
import cn.xports.venue.VenueMainActivity;
import com.blankj.utilcode.util.ActivityUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.Map;
import net.arvin.socialhelper.SocialHelper;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

public class EventHandler {
    public static boolean isDebug = false;
    private static final EventHandler ourInstance = new EventHandler();
    private LoadingListener loadingListener;
    private OnPayClickListener payClickListener;
    private OnPayListener payListener;
    private String serverAddr = "";
    private SocialHelper socialHelper;

    public interface OnPayClickListener {
        void onPayClick(String str);
    }

    private EventHandler() {
    }

    public static EventHandler getInstance() {
        return ourInstance;
    }

    public String getServerAddr() {
        return this.serverAddr;
    }

    public EventHandler setServerAddr(String str) {
        this.serverAddr = str;
        return this;
    }

    public OnPayClickListener getPayClickListener() {
        return this.payClickListener;
    }

    public void setPayClickListener(OnPayClickListener onPayClickListener) {
        this.payClickListener = onPayClickListener;
    }

    public void setPayListener(OnPayListener onPayListener) {
        this.payListener = onPayListener;
    }

    public void setPayResult(String str) {
        if (this.payListener != null) {
            this.payListener.onThirdResult(str);
        }
    }

    public LoadingListener getLoadingListener() {
        return this.loadingListener;
    }

    public void setLoading(LoadingListener loadingListener2) {
        this.loadingListener = loadingListener2;
    }

    public void testPay(String str, int i, Observer<ResponseBody> observer) throws Exception {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("payMode", Constant.ECARD_PAY);
        jSONObject.put("payMoney", i);
        jSONObject.put("realPay", i);
        jSONArray.put(jSONObject);
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).payOrder("E3201000100021394", jSONArray.toString(), str, "123456").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void goToXportsVenue(Context context, String str, String str2) {
        SPUtil.Companion.getINSTANCE().save("phone", str).save("outUid", str2).apply();
        context.startActivity(new Intent(context, VenueMainActivity.class).putExtra("phone", str).putExtra("outUid", str2));
    }

    public void toWithLogin(String str, String str2, String str3, Map<String, String> map) {
        SPUtil.Companion.getINSTANCE().save("phone", str).save("outUid", str2).apply();
        ActivityUtils.startActivity(Router.startWithLogin(str3, map));
    }

    public void toWithLogin(String str, String str2, String str3) {
        SPUtil.Companion.getINSTANCE().save("phone", str).save("outUid", str2).apply();
        ActivityUtils.startActivity(Router.startWithLogin(str3, (Map<String, String>) null));
    }

    public EventHandler configServer(String str, String str2, String str3) {
        GlobalStaticConfig.getInstance().setApiKey(str2).setSecretKey(str3).setcApiKey(BuildConfig.cApiKey).setcSecretKey(BuildConfig.cSecretKey).setBaseUrl(str);
        return this;
    }

    public EventHandler configServer(String str) {
        GlobalStaticConfig.getInstance().setApiKey(BuildConfig.apikey).setSecretKey(BuildConfig.secretKey).setBaseUrl(str);
        return this;
    }

    public EventHandler configDevServer() {
        GlobalStaticConfig.getInstance().setApiKey(BuildConfig.apikey).setSecretKey(BuildConfig.secretKey).setBaseUrl("http://218.244.136.36/");
        return this;
    }

    public EventHandler passUseInfo(String str) {
        SPUtil.Companion.getINSTANCE().save("qdUserInfo", str).apply();
        DataMap fromJson = DataMapUtils.fromJson(str);
        SPUtil.Companion.getINSTANCE().save("qdPhoto", fromJson.getString("photo"));
        SPUtil.Companion.getINSTANCE().save("qdNickName", fromJson.getString("nickName"));
        return this;
    }

    public SocialHelper getSocialHelper() {
        return this.socialHelper;
    }

    public EventHandler setSocialHelper(SocialHelper socialHelper2) {
        this.socialHelper = socialHelper2;
        return this;
    }
}
