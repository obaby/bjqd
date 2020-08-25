package cn.xports.soda.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import cn.xports.export.EventHandler;
import cn.xports.pay.event.WeChatPayEvent;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private static String TAG = "MicroMsg.WXEntryActivity";
    private IWXAPI api;

    static {
        StubApp.interface11(4316);
    }

    public native void onCreate(Bundle bundle);

    public void onReq(BaseReq baseReq) {
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    public void onResp(BaseResp baseResp) {
        String str = TAG;
        Log.e(str, baseResp.errCode + "");
        if (baseResp.getType() == 5) {
            String str2 = TAG;
            Log.e(str2, baseResp.errCode + "");
            WeChatPayEvent weChatPayEvent = new WeChatPayEvent();
            switch (baseResp.errCode) {
                case -2:
                    weChatPayEvent.setSuccess(false);
                    weChatPayEvent.setMessage("用户取消支付");
                    EventHandler.getInstance().setPayResult("用户取消支付");
                    break;
                case -1:
                    weChatPayEvent.setSuccess(false);
                    weChatPayEvent.setMessage("支付错误");
                    EventHandler.getInstance().setPayResult("支付错误");
                    break;
                case 0:
                    weChatPayEvent.setSuccess(true);
                    weChatPayEvent.setMessage("支付成功");
                    EventHandler.getInstance().setPayResult("1");
                    break;
            }
            finish();
        }
    }
}
