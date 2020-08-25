package cn.xports.baselib.http;

import android.support.annotation.NonNull;
import android.widget.Toast;
import cn.xports.baselib.App;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.qd2.entity.K;
import java.io.IOException;
import okhttp3.ResponseBody;

public abstract class ResponseMapWrap extends ProcessObserver<ResponseBody> {
    public abstract void onSuccess(@NonNull DataMap dataMap);

    public ResponseMapWrap(String str) {
        super(str);
    }

    public ResponseMapWrap(String str, boolean z) {
        super(str, z);
    }

    public ResponseMapWrap(IView iView) {
        super(iView);
    }

    public ResponseMapWrap(IView iView, boolean z) {
        super(iView, z);
    }

    public void next(ResponseBody responseBody) {
        DataMap dataMap;
        try {
            dataMap = DataMapUtils.fromJson(responseBody.string());
        } catch (IOException e) {
            e.printStackTrace();
            dataMap = null;
        }
        if (dataMap == null) {
            onError(new DataMap().set(K.error, 101111).set(K.message, "数据错误"));
        } else if (dataMap.getError() != 0) {
            onError(dataMap);
        } else {
            onSuccess(dataMap);
        }
    }

    public void onError(ResponseThrowable responseThrowable) {
        if (responseThrowable != null) {
            onError(new DataMap().set(K.error, Integer.valueOf(responseThrowable.getCode())).set(K.message, responseThrowable.getMessage()));
        }
    }

    public void onError(@NonNull DataMap dataMap) {
        Toast.makeText(App.getInstance(), dataMap.getMessage(), 1).show();
    }
}
