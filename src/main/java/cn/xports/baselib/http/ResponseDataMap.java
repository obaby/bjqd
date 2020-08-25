package cn.xports.baselib.http;

import android.support.annotation.NonNull;
import android.widget.Toast;
import cn.xports.baselib.App;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;

public abstract class ResponseDataMap extends ProcessObserver<DataMap> {
    public abstract void onSuccess(@NonNull DataMap dataMap);

    public ResponseDataMap(String str) {
        super(str);
    }

    public ResponseDataMap(IView iView) {
        super(iView);
    }

    public ResponseDataMap(String str, boolean z) {
        super(str, z);
    }

    public ResponseDataMap(IView iView, boolean z) {
        super(iView, z);
    }

    public void next(DataMap dataMap) {
        onSuccess(dataMap);
    }

    public void onError(ResponseThrowable responseThrowable) {
        if (responseThrowable == null) {
            return;
        }
        if (responseThrowable.getDataMap() != null) {
            onError(responseThrowable.getDataMap());
        } else {
            onError(new DataMap().set(K.error, Integer.valueOf(responseThrowable.getCode())).set(K.message, responseThrowable.getMessage()));
        }
    }

    public void onError(@NonNull DataMap dataMap) {
        Toast.makeText(App.getInstance(), dataMap.getMessage(), 1).show();
    }
}
