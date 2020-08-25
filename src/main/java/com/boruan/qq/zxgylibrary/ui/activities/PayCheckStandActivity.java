package com.boruan.qq.zxgylibrary.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.wheelpicker.BuildConfig;
import com.alipay.sdk.util.e;
import com.boruan.qq.zxgylibrary.R;
import com.boruan.qq.zxgylibrary.base.BaseActivity;
import com.boruan.qq.zxgylibrary.constants.ConstantInfo;
import com.boruan.qq.zxgylibrary.service.model.DownOrderBean;
import com.boruan.qq.zxgylibrary.service.model.PayInfoResponse;
import com.boruan.qq.zxgylibrary.service.model.SettleDefaultAddressBean;
import com.boruan.qq.zxgylibrary.service.model.ShopCarDataBean;
import com.boruan.qq.zxgylibrary.service.presenter.ConfirmOrderPresenter;
import com.boruan.qq.zxgylibrary.service.view.OrderView;
import com.boruan.qq.zxgylibrary.ui.widgets.CountdownView;
import com.boruan.qq.zxgylibrary.utils.EventMessage;
import com.chinaums.pppay.unify.UnifyPayPlugin;
import com.chinaums.pppay.unify.UnifyPayRequest;
import com.convenient.qd.core.utils.ToastUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PayCheckStandActivity extends BaseActivity implements OrderView {
    @BindView(2131492995)
    Button btnToPay;
    /* access modifiers changed from: private */
    public ConfirmOrderPresenter confirmOrderPresenter;
    CountDownTimer countDownTimer;
    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, "wx45cce7b0849360ad", true);
    /* access modifiers changed from: private */
    public String orderId;
    private String payMoney;
    @BindView(2131493606)
    RadioButton rbWx;
    @BindView(2131493607)
    RadioButton rbZfb;
    @BindView(2131493621)
    RadioGroup rgPayType;
    private String shopName;
    @BindView(2131493867)
    TextView tvPayPharmacyName;
    @BindView(2131493868)
    TextView tvPayPrice;
    @BindView(2131493873)
    CountdownView tvPayTime;
    private String type;

    public void getDefaultAddressFailed(String str) {
    }

    public void getDefaultAddressSuccess(SettleDefaultAddressBean.ResultBean resultBean) {
    }

    public void getShopCarDataFailed(String str) {
    }

    public void getShopCarDataSuccess(List<ShopCarDataBean.ResultBean> list) {
    }

    public void hideProgress() {
    }

    public void showProgress() {
    }

    public void userDownOrderFailed(String str) {
    }

    public void userDownOrderSuccess(DownOrderBean.ResultBean resultBean) {
    }

    public void userPayNoticeFailed(String str) {
    }

    public void userPayNoticeSuccess(String str) {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
            getWindow().setStatusBarColor(0);
        }
        return R.layout.drug_activity_pay_check;
    }

    /* access modifiers changed from: protected */
    public void init(Bundle bundle) {
        registerEvent();
        if (getIntent() != null) {
            this.type = getIntent().getStringExtra("payFailed");
            this.orderId = getIntent().getStringExtra("orderId");
            this.shopName = getIntent().getStringExtra("shopName");
            this.payMoney = getIntent().getStringExtra("money");
            this.tvPayPharmacyName.setText(this.shopName);
            this.tvPayPrice.setText(this.payMoney);
        }
        this.confirmOrderPresenter = new ConfirmOrderPresenter(this);
        this.confirmOrderPresenter.onCreate();
        this.confirmOrderPresenter.attachView(this);
    }

    @OnClick({2131493799, 2131492995})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_close_pay) {
            if (this.countDownTimer != null) {
                this.countDownTimer.onFinish();
                this.countDownTimer.cancel();
            }
            if (this.tvPayTime != null) {
                this.tvPayTime.releaseTime();
                this.tvPayTime = null;
            }
            if (e.f729b.equals(this.type)) {
                Intent intent = new Intent(this, OrderDetailActivity.class);
                intent.putExtra("orderStatus", "1");
                intent.putExtra("orderId", this.orderId);
                startActivity(intent);
            }
            finish();
        } else if (id != R.id.btn_to_pay) {
        } else {
            if (this.rbZfb.isChecked()) {
                this.confirmOrderPresenter.getPayParam(this.orderId, "ALIPAY_PSCAN", "");
            } else if (this.rbWx.isChecked()) {
                this.confirmOrderPresenter.getPayParam(this.orderId, "WXPAY_APP", "");
            }
        }
    }

    public void onBackPressed() {
        if (e.f729b.equals(this.type)) {
            Intent intent = new Intent(this, OrderDetailActivity.class);
            intent.putExtra("orderStatus", "1");
            intent.putExtra("orderId", this.orderId);
            startActivity(intent);
        }
        super.onBackPressed();
    }

    public void getPayParamsSuccess(PayInfoResponse.ResultBean resultBean) {
        if (this.rbZfb.isChecked()) {
            String queryParameter = Uri.parse(resultBean.getUrl()).getQueryParameter("imageUrl");
            Intent intent = new Intent(this, GYAlipayWebActivity.class);
            intent.putExtra("url", queryParameter);
            intent.putExtra("tradeId", this.orderId);
            startActivityForResult(intent, ConfirmOrderActivity.PAY_REQUEST_CODE);
        } else if (this.rbWx.isChecked()) {
            UnifyPayPlugin instance = UnifyPayPlugin.getInstance(this);
            UnifyPayRequest unifyPayRequest = new UnifyPayRequest();
            unifyPayRequest.payChannel = "01";
            unifyPayRequest.payData = resultBean.getUrl();
            instance.setListener(new 1(this));
            instance.sendPayRequest(unifyPayRequest);
            ConstantInfo.weChatPayBack = true;
        }
    }

    public void getPayParamsFailed(String str) {
        ToastUtils.showShort(str);
    }

    public void getOrderDetailSuccess(int i) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        if (i == 21 || i == 30) {
            intent.putExtra("orderStatus", i + "");
        } else {
            intent.putExtra("orderStatus", "99");
        }
        intent.putExtra("orderId", this.orderId);
        startActivity(intent);
        setResult(BuildConfig.VERSION_CODE);
        finish();
    }

    public void getOrderDetailFailed(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == ConfirmOrderActivity.PAY_REQUEST_CODE && i2 == ConfirmOrderActivity.PAY_SUCCESS_RESULT_CODE) {
            Toast.makeText(this, "支付成功！", 0).show();
            Intent intent2 = new Intent(this, OrderDetailActivity.class);
            intent2.putExtra("orderStatus", "99");
            intent2.putExtra("orderId", this.orderId);
            startActivity(intent2);
            finish();
        } else if (i == ConfirmOrderActivity.PAY_REQUEST_CODE && i2 == ConfirmOrderActivity.PAY_FAILED_RESULT_CODE) {
            int intExtra = intent.getIntExtra("orderStatus", 0);
            Intent intent3 = new Intent(this, OrderDetailActivity.class);
            intent3.putExtra("orderStatus", intExtra + "");
            intent3.putExtra("orderId", this.orderId);
            startActivity(intent3);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void registerEvent() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /* access modifiers changed from: protected */
    public void unRegisterEvent() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage eventMessage) {
        if (eventMessage.getState() == EventMessage.EventState.WECHAT_PAY_SUCCESS) {
            Toast.makeText(this, "支付成功！", 0).show();
            Intent intent = new Intent(this, OrderDetailActivity.class);
            intent.putExtra("orderStatus", "2");
            intent.putExtra("orderId", this.orderId);
            startActivity(intent);
            finish();
        } else if (eventMessage.getState() == EventMessage.EventState.WECHAT_PAY_ERROR) {
            Toast.makeText(this, "支付失败，请重试！", 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unRegisterEvent();
    }
}
