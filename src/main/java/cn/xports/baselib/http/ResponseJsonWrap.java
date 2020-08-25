package cn.xports.baselib.http;

import android.widget.Toast;
import cn.xports.baselib.App;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import okhttp3.ResponseBody;
import org.json.JSONObject;

public abstract class ResponseJsonWrap extends ProcessObserver<ResponseBody> {
    public abstract void onSuccess(JSONObject jSONObject) throws Exception;

    public ResponseJsonWrap(String str) {
        super(str);
    }

    public ResponseJsonWrap(String str, boolean z) {
        super(str, z);
    }

    public ResponseJsonWrap(IView iView) {
        super(iView);
    }

    public ResponseJsonWrap(IView iView, boolean z) {
        super(iView, z);
    }

    public void next(ResponseBody responseBody) {
        try {
            JSONObject jSONObject = new JSONObject(responseBody.string());
            if (jSONObject.getInt(K.error) != 0) {
                onError(jSONObject);
            } else {
                onSuccess(jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onError(JSONObject jSONObject) throws Exception {
        Toast.makeText(App.getInstance(), jSONObject.getString(K.message), 1).show();
    }
}
