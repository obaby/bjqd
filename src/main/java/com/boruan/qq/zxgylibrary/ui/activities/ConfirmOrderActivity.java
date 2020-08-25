package com.boruan.qq.zxgylibrary.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.wheelpicker.BuildConfig;
import cn.xports.qd2.entity.K;
import com.alipay.sdk.util.e;
import com.boruan.qq.zxgylibrary.R;
import com.boruan.qq.zxgylibrary.base.BaseActivity;
import com.boruan.qq.zxgylibrary.constants.ConstantInfo;
import com.boruan.qq.zxgylibrary.service.manager.GXDrugModule;
import com.boruan.qq.zxgylibrary.service.model.DownOrderBean;
import com.boruan.qq.zxgylibrary.service.model.PayInfoResponse;
import com.boruan.qq.zxgylibrary.service.model.SettleDefaultAddressBean;
import com.boruan.qq.zxgylibrary.service.model.ShopCarDataBean;
import com.boruan.qq.zxgylibrary.service.presenter.ConfirmOrderPresenter;
import com.boruan.qq.zxgylibrary.service.view.OrderView;
import com.boruan.qq.zxgylibrary.ui.adapters.GoodsListAdapter;
import com.boruan.qq.zxgylibrary.ui.widgets.DrugPayDialog;
import com.boruan.qq.zxgylibrary.utils.CButtonUtils;
import com.boruan.qq.zxgylibrary.utils.CustomDialogOne;
import com.boruan.qq.zxgylibrary.utils.EventMessage;
import com.chinaums.pppay.unify.UnifyPayPlugin;
import com.chinaums.pppay.unify.UnifyPayRequest;
import com.convenient.qd.core.base.arouter.ARouterUtils;
import com.convenient.qd.core.constant.Constant;
import com.convenient.qd.core.utils.CommonUtils;
import com.convenient.qd.core.utils.ToastUtils;
import com.convenient.qd.core.utils.UMEventUtil;
import com.convenient.qd.module.user.api.UserModule;
import com.convenient.qd.module.user.widget.PwdInputDialog;
import com.stub.StubApp;
import com.unionpay.UPPayAssistEx;
import java.math.BigDecimal;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ConfirmOrderActivity extends BaseActivity implements OrderView {
    public static int PAY_FAILED_RESULT_CODE = 102;
    public static int PAY_PWD_REQUEST_CODE = 10000;
    public static int PAY_REQUEST_CODE = 100;
    public static int PAY_SUCCESS_RESULT_CODE = 101;
    private int addressId = -1;
    /* access modifiers changed from: private */
    public ConfirmOrderPresenter confirmOrderPresenter;
    private CustomDialogOne customDialogOne;
    private int deliveryFee;
    private int distance;
    private DrugPayDialog drugPayDialog;
    @BindView(2131493137)
    EditText edt_name;
    @BindView(2131493138)
    EditText edt_phone;
    private String fullAddress;
    private int fullReducePrice;
    private GoodsListAdapter goodsListAdapter;
    @BindView(2131493171)
    RecyclerView goodsRecycle;
    @BindView(2131493172)
    TextView goodsSmallAmount;
    /* access modifiers changed from: private */
    public Boolean isPayPwd;
    private boolean isRideOrSelf = true;
    private Boolean isShowYBPay = false;
    Boolean isSupportYBPay = false;
    private Boolean isYBPay;
    /* access modifiers changed from: private */
    public Boolean isYBSign = false;
    @BindView(2131493322)
    LinearLayout llAddressInfo;
    @SuppressLint({"HandlerLeak"})
    private Handler mHandler = new 9(this);
    List<ShopCarDataBean.ResultBean> mShopCarDataBean;
    private double payMoney;
    /* access modifiers changed from: private */
    public String payPwd;
    /* access modifiers changed from: private */
    public String payType;
    private PwdInputDialog pwdInputDialog;
    @BindView(2131493602)
    TextView rbRider;
    @BindView(2131493604)
    TextView rbSelf;
    @BindView(2131493622)
    LinearLayout rgRiderSelf;
    @BindView(2131493636)
    RelativeLayout rlClickAddress;
    @BindView(2131493639)
    RelativeLayout rlDeliveryMoney;
    @BindView(2131493642)
    RelativeLayout rlFullReduce;
    @BindView(2131493643)
    RelativeLayout rl_name;
    @BindView(2131493645)
    RelativeLayout rl_phone;
    private String shopAddress;
    private int shopGoodsMoney;
    /* access modifiers changed from: private */
    public String shopId;
    /* access modifiers changed from: private */
    public String tradeId;
    @BindView(2131493814)
    TextView tvDeliveryMoney;
    @BindView(2131493815)
    TextView tvDeliveryTime;
    @BindView(2131493824)
    TextView tvFullReduceMoney;
    @BindView(2131493834)
    TextView tvInputRemark;
    @BindView(2131493848)
    TextView tvMoney;
    @BindView(2131493874)
    TextView tvPayType;
    @BindView(2131493880)
    TextView tvPromptAddress;
    @BindView(2131493890)
    TextView tvSelectInvoice;
    @BindView(2131493891)
    TextView tvSelfTime;
    @BindView(2131493894)
    TextView tvShopAddress;
    @BindView(2131493895)
    TextView tvShopName;
    @BindView(2131493908)
    TextView tvUserAddress;
    @BindView(2131493910)
    TextView tvUserInfo;
    private String userInfo;
    @BindView(2131493956)
    View vLineFullReduce;
    /* access modifiers changed from: private */
    public String ybMoney;

    static {
        StubApp.interface11(5486);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
            getWindow().setStatusBarColor(0);
        }
        return R.layout.drug_activity_drug_confirm_order;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (ConstantInfo.isCurrentDelete) {
            this.llAddressInfo.setVisibility(8);
            this.tvPromptAddress.setVisibility(0);
            ConstantInfo.isCurrentDelete = false;
        }
        getInsuranceBalance();
        pwdCheck();
    }

    private void pwdCheck() {
        UserModule.getInstance().pwdCheck(new 1(this));
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        if (ConstantInfo.weChatPayBack) {
            this.confirmOrderPresenter.getOrderDetailInfo(this.tradeId);
            ConstantInfo.weChatPayBack = false;
        }
    }

    /* access modifiers changed from: protected */
    public void init(Bundle bundle) {
        registerEvent();
        if (getIntent() != null) {
            this.shopId = getIntent().getStringExtra("shopId");
        }
        this.pwdInputDialog = new PwdInputDialog(this);
        this.payType = "";
        this.customDialogOne = new CustomDialogOne(this, R.style.CustomDialogOne);
        this.goodsRecycle.setLayoutManager(new 2(this, this, 1, false));
        this.goodsListAdapter = new GoodsListAdapter(this);
        this.goodsRecycle.setAdapter(this.goodsListAdapter);
        this.confirmOrderPresenter = new ConfirmOrderPresenter(this);
        this.confirmOrderPresenter.onCreate();
        this.confirmOrderPresenter.attachView(this);
        this.confirmOrderPresenter.getDefaultAddress(this.shopId);
        this.confirmOrderPresenter.getShopCarData(this.shopId, this.addressId);
    }

    private void getInsuranceBalance() {
        if (TextUtils.isEmpty(ConstantInfo.ybBalance) || ConstantInfo.ybBalance.equals("-1")) {
            this.isYBSign = false;
        } else {
            this.isYBSign = true;
            this.ybMoney = ConstantInfo.ybBalance;
        }
        GXDrugModule.getInstance().getInsuranceBalance(new 3(this));
    }

    /* access modifiers changed from: private */
    public void orderSubmit() {
        String charSequence = this.tvInputRemark.getText().toString();
        if (this.isRideOrSelf) {
            if (this.llAddressInfo.getVisibility() == 8) {
                promptSelectAddress();
            } else if (this.mShopCarDataBean == null || this.mShopCarDataBean.size() == 0) {
                Toast.makeText(this, "您还没有选择药品！", 0).show();
            } else {
                this.confirmOrderPresenter.userDownOrder(0, 1, this.distance, this.deliveryFee, this.addressId, (this.shopGoodsMoney + this.deliveryFee) - this.fullReducePrice, this.shopId, this.mShopCarDataBean, charSequence, "", "");
            }
        } else if (TextUtils.isEmpty(this.edt_phone.getText().toString())) {
            Toast.makeText(this, "请输入取货人手机号", 0).show();
        } else if (!CommonUtils.isChinaPhoneLegal(this.edt_phone.getText().toString())) {
            Toast.makeText(this, "请输入正确的取货人手机号", 0).show();
        } else if (TextUtils.isEmpty(this.edt_name.getText().toString())) {
            Toast.makeText(this, "请输入取货人姓名", 0).show();
        } else if (this.mShopCarDataBean == null || this.mShopCarDataBean.size() == 0) {
            Toast.makeText(this, "您还没有选择药品！", 0).show();
        } else {
            this.confirmOrderPresenter.userDownOrder(0, 2, 0, 0, 0, this.shopGoodsMoney - this.fullReducePrice, this.shopId, this.mShopCarDataBean, charSequence, this.edt_phone.getText().toString(), this.edt_name.getText().toString());
        }
    }

    @OnClick({2131493225, 2131493802, 2131493890, 2131493602, 2131493604, 2131493636, 2131493815, 2131493644, 2131493834})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.tv_commit_order) {
            UMEventUtil.onOrderEventObject(this, "2001007", Constant.sceneId);
            if (CButtonUtils.isFastDoubleClick(R.id.tv_commit_order)) {
                Toast.makeText(this, "请不要过快点击！", 0).show();
            } else if (this.isRideOrSelf && this.addressId <= 0) {
                ToastUtils.showShort("请选择收货地址");
            } else if (TextUtils.isEmpty(this.payType)) {
                ToastUtils.showShort("请选择支付方式");
            } else if (this.isPayPwd == null) {
                ToastUtils.showShort("信息异常请重新下单");
                finish();
            } else if (this.payType.equals("YBPAY") && !this.isPayPwd.booleanValue()) {
                ARouterUtils.navigation("/module_user/UserPwdShiRenActivity");
            } else if (this.payType.equals("YBPAY")) {
                showPwdDialog();
            } else {
                orderSubmit();
            }
        } else if (id == R.id.rb_rider) {
            this.drugPayDialog = null;
            this.payType = "";
            this.tvPayType.setTextColor(Color.parseColor("#757575"));
            this.tvPayType.setText("请选择");
            double d = (double) ((this.shopGoodsMoney + this.deliveryFee) - this.fullReducePrice);
            Double.isNaN(d);
            this.payMoney = new BigDecimal(d / 100.0d).setScale(2, 4).doubleValue();
            TextView textView = this.goodsSmallAmount;
            textView.setText("" + this.payMoney);
            TextView textView2 = this.tvMoney;
            textView2.setText("￥" + this.payMoney);
            this.isRideOrSelf = true;
            this.rbRider.setBackgroundResource(R.drawable.drug_shape_confirm_order_true);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.rbRider.getLayoutParams();
            layoutParams.height = getPxFromDp(45.0f);
            this.rbRider.setLayoutParams(layoutParams);
            this.rbSelf.setBackgroundResource(R.drawable.drug_shape_confirm_order_false);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.rbSelf.getLayoutParams();
            layoutParams2.height = getPxFromDp(40.0f);
            this.rbSelf.setLayoutParams(layoutParams2);
            this.rlClickAddress.setVisibility(0);
            this.rlDeliveryMoney.setVisibility(0);
            this.tvSelfTime.setText("立即送出");
            this.tvShopAddress.setVisibility(8);
            this.tvDeliveryTime.setText("约20:01送达");
            this.rl_name.setVisibility(8);
            this.rl_phone.setVisibility(8);
        } else if (id == R.id.rb_self) {
            this.drugPayDialog = null;
            this.payType = "";
            this.tvPayType.setTextColor(Color.parseColor("#757575"));
            this.tvPayType.setText("请选择");
            double d2 = (double) (this.shopGoodsMoney - this.fullReducePrice);
            Double.isNaN(d2);
            this.payMoney = new BigDecimal(d2 / 100.0d).setScale(2, 4).doubleValue();
            TextView textView3 = this.goodsSmallAmount;
            textView3.setText("" + this.payMoney);
            TextView textView4 = this.tvMoney;
            textView4.setText("￥" + this.payMoney);
            this.isRideOrSelf = false;
            this.rbRider.setBackgroundResource(R.drawable.drug_shape_confirm_order_false);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.rbRider.getLayoutParams();
            layoutParams3.height = getPxFromDp(40.0f);
            this.rbRider.setLayoutParams(layoutParams3);
            this.rbSelf.setBackgroundResource(R.drawable.drug_shape_confirm_order_true);
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.rbSelf.getLayoutParams();
            layoutParams4.height = getPxFromDp(45.0f);
            this.rbSelf.setLayoutParams(layoutParams4);
            this.rlClickAddress.setVisibility(8);
            this.rlDeliveryMoney.setVisibility(8);
            this.tvSelfTime.setText("自取时间");
            this.tvShopAddress.setVisibility(0);
            this.tvDeliveryTime.setText("早8:00-晚9:00");
            this.rl_name.setVisibility(0);
            this.rl_phone.setVisibility(0);
        } else if (id == R.id.rl_click_address) {
            Intent intent = new Intent(this, AddressListActivity.class);
            intent.putExtra("shopId", this.shopId);
            startActivityForResult(intent, 10);
        } else if (id != R.id.tv_delivery_time) {
            if (id == R.id.rl_pay_type) {
                if (!this.isYBSign.booleanValue()) {
                    GXDrugModule.getInstance().getInsuranceBalance(new 4(this));
                }
                selectPayType();
            } else if (id == R.id.tv_input_remark) {
                startActivityForResult(new Intent(this, OrderRemarkActivity.class), 10);
            } else {
                int i = R.id.tv_select_invoice;
            }
        }
    }

    public void promptSelectAddress() {
        PopupWindow popupWindow = new PopupWindow();
        View inflate = LayoutInflater.from(this).inflate(R.layout.drug_pop_prompt_address, (ViewGroup) null);
        popupWindow.setContentView(inflate);
        ((Button) inflate.findViewById(R.id.btn_know)).setOnClickListener(new 5(this, popupWindow));
        popupWindow.setWidth(ConstantInfo.screenWidth - getPxFromDp(80.0f));
        popupWindow.setHeight(-2);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.style_down_to_up_animation);
        backgroundAlpha(0.3f);
        popupWindow.setFocusable(false);
        popupWindow.showAtLocation(findViewById(R.id.rl_confirm), 17, 0, 0);
        popupWindow.setOnDismissListener(new poponDismissListener(this));
    }

    public void selectPayType() {
        String str;
        Boolean bool;
        Long l;
        String str2 = "";
        if (this.isYBPay == null) {
            ToastUtils.showShort("信息异常请重新下单");
            finish();
        } else if (!this.isRideOrSelf || this.addressId > 0) {
            Boolean bool2 = this.isYBPay;
            if (!bool2.booleanValue()) {
                str2 = "本商户未开通医保余额支付，暂不支持";
            } else if (this.isRideOrSelf && this.deliveryFee > 0) {
                bool2 = false;
                str2 = "配送费不为0，暂不支持医保余额支付";
            }
            if (this.isYBSign.booleanValue()) {
                int i = (this.shopGoodsMoney + this.deliveryFee) - this.fullReducePrice;
                if (TextUtils.isEmpty(this.ybMoney) || Long.valueOf(this.ybMoney).longValue() <= 0) {
                    this.ybMoney = K.k0;
                    bool2 = false;
                    str2 = "医保余额不足";
                }
                Long valueOf = Long.valueOf(this.ybMoney);
                if (valueOf.longValue() < ((long) i)) {
                    bool2 = false;
                    this.ybMoney = K.k0;
                    str2 = "医保余额不足";
                }
                str = str2;
                bool = bool2;
                l = valueOf;
            } else {
                str = str2;
                bool = bool2;
                l = 0L;
            }
            if (this.drugPayDialog == null) {
                this.drugPayDialog = new DrugPayDialog(this, l, this.isYBSign, bool, str);
                this.drugPayDialog.setPayPwd(this.isPayPwd);
                this.drugPayDialog.setShowYBPay(this.isShowYBPay);
            } else {
                this.drugPayDialog.setDes(str);
                this.drugPayDialog.setMoney(l);
                this.drugPayDialog.setYBPay(bool);
                this.drugPayDialog.setYBSign(this.isYBSign);
                this.drugPayDialog.setPayPwd(this.isPayPwd);
                this.drugPayDialog.setPayType(this.payType);
                this.drugPayDialog.setShowYBPay(this.isShowYBPay);
                this.drugPayDialog.setState();
            }
            this.drugPayDialog.show();
            this.drugPayDialog.setOnPayClickListener(new 6(this));
        } else {
            ToastUtils.showShort("请选择收货地址");
        }
    }

    public int getPxFromDp(float f) {
        return (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    public void backgroundAlpha(float f) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = f;
        getWindow().setAttributes(attributes);
    }

    public void showProgress() {
        this.customDialogOne.show();
    }

    public void hideProgress() {
        this.customDialogOne.dismiss();
    }

    public void getDefaultAddressSuccess(SettleDefaultAddressBean.ResultBean resultBean) {
        if (resultBean != null) {
            try {
                if (resultBean.getFulladdress().contains(resultBean.getAddress())) {
                    this.llAddressInfo.setVisibility(8);
                    this.tvPromptAddress.setVisibility(0);
                    return;
                }
                this.llAddressInfo.setVisibility(0);
                this.tvPromptAddress.setVisibility(8);
                this.addressId = resultBean.getId();
                ConstantInfo.mAddressId = this.addressId;
                this.distance = resultBean.getDistance();
                this.tvUserAddress.setText(resultBean.getFulladdress());
                TextView textView = this.tvUserInfo;
                textView.setText(resultBean.getContactperson() + resultBean.getContactphone());
                this.confirmOrderPresenter.getShopCarData(this.shopId, this.addressId);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(K.error, e.getMessage());
            }
        } else {
            this.addressId = 0;
            this.confirmOrderPresenter.getShopCarData(this.shopId, this.addressId);
            this.llAddressInfo.setVisibility(8);
            this.tvPromptAddress.setVisibility(0);
        }
    }

    public void getDefaultAddressFailed(String str) {
        this.llAddressInfo.setVisibility(8);
        this.tvPromptAddress.setVisibility(0);
        this.addressId = 0;
        this.confirmOrderPresenter.getShopCarData(this.shopId, this.addressId);
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, 0).show();
        }
    }

    public void getShopCarDataSuccess(List<ShopCarDataBean.ResultBean> list) {
        if (list.size() == 0) {
            ToastUtils.showShort("暂无商品信息");
            finish();
            return;
        }
        this.mShopCarDataBean = list;
        boolean z = false;
        this.fullReducePrice = list.get(0).getFullReducePrice();
        if (this.fullReducePrice == 0) {
            this.rlFullReduce.setVisibility(8);
            this.vLineFullReduce.setVisibility(8);
        } else {
            this.rlFullReduce.setVisibility(0);
            this.vLineFullReduce.setVisibility(0);
            double d = (double) this.fullReducePrice;
            Double.isNaN(d);
            double doubleValue = new BigDecimal(d / 100.0d).setScale(2, 4).doubleValue();
            TextView textView = this.tvFullReduceMoney;
            textView.setText("-￥" + doubleValue);
        }
        this.shopAddress = list.get(0).getShopAddress();
        this.tvShopAddress.setText(this.shopAddress);
        this.tvShopName.setText(list.get(0).getShopName());
        this.goodsListAdapter.setData(list.get(0).getShopCartSkuDTOList());
        this.shopGoodsMoney = list.get(0).getTotalPrice();
        this.deliveryFee = list.get(0).getDeliveryFee();
        double d2 = (double) this.deliveryFee;
        Double.isNaN(d2);
        double doubleValue2 = new BigDecimal(d2 / 100.0d).setScale(2, 4).doubleValue();
        TextView textView2 = this.tvDeliveryMoney;
        textView2.setText("￥" + doubleValue2);
        double d3 = (double) ((this.shopGoodsMoney + this.deliveryFee) - this.fullReducePrice);
        Double.isNaN(d3);
        this.payMoney = new BigDecimal(d3 / 100.0d).setScale(2, 4).doubleValue();
        TextView textView3 = this.goodsSmallAmount;
        textView3.setText("" + this.payMoney);
        TextView textView4 = this.tvMoney;
        textView4.setText("￥" + this.payMoney);
        this.isYBPay = Boolean.valueOf(list.get(0).getMedicalPayFlag() == 1);
        if (list.get(0).getShowMedicalPayFlag() != 0) {
            z = true;
        }
        this.isShowYBPay = Boolean.valueOf(z);
    }

    public void getShopCarDataFailed(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, 0).show();
        }
    }

    public void userDownOrderSuccess(DownOrderBean.ResultBean resultBean) {
        this.tradeId = resultBean.getOrderId();
        this.confirmOrderPresenter.getPayParam(resultBean.getOrderId(), this.payType, this.payPwd);
    }

    private void showPwdDialog() {
        this.pwdInputDialog = new PwdInputDialog(this);
        this.pwdInputDialog.show();
        this.pwdInputDialog.setOnPwdValidateListener(new 7(this));
    }

    public void userDownOrderFailed(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, 0).show();
        }
    }

    public void userPayNoticeSuccess(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public void userPayNoticeFailed(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, 0).show();
        }
    }

    public void getPayParamsSuccess(PayInfoResponse.ResultBean resultBean) {
        if (this.payType.equals("ALIPAY_PSCAN")) {
            String queryParameter = Uri.parse(resultBean.getUrl()).getQueryParameter("imageUrl");
            Intent intent = new Intent(this, GYAlipayWebActivity.class);
            intent.putExtra("url", queryParameter);
            intent.putExtra("tradeId", this.tradeId);
            startActivityForResult(intent, PAY_REQUEST_CODE);
        } else if (this.payType.equals("WXPAY_APP")) {
            UnifyPayPlugin instance = UnifyPayPlugin.getInstance(this);
            UnifyPayRequest unifyPayRequest = new UnifyPayRequest();
            unifyPayRequest.payChannel = "01";
            unifyPayRequest.payData = resultBean.getUrl();
            instance.setListener(new 8(this));
            instance.sendPayRequest(unifyPayRequest);
            ConstantInfo.weChatPayBack = true;
        } else if (this.payType.equals("YBPAY")) {
            this.confirmOrderPresenter.getOrderDetailInfo(this.tradeId);
        } else if (this.payType.equals("CLOUDPAY")) {
            String url = resultBean.getUrl();
            if (!TextUtils.isEmpty(url)) {
                Log.i("xxxx", url);
                UPPayAssistEx.startPay(this, (String) null, (String) null, url, "00");
            }
        }
    }

    public void getPayParamsFailed(String str) {
        ToastUtils.showShort(str);
        setResult(BuildConfig.VERSION_CODE);
        finish();
    }

    public void getOrderDetailSuccess(int i) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        if (i == 21 || i == 30) {
            intent.putExtra("orderStatus", i + "");
        } else {
            intent.putExtra("orderStatus", "99");
        }
        intent.putExtra("orderId", this.tradeId);
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
        if (i == 10 && i2 == 13) {
            this.addressId = intent.getIntExtra("addressId", 0);
            ConstantInfo.mAddressId = this.addressId;
            if (this.addressId == 0 || this.addressId == -1) {
                this.confirmOrderPresenter.getDefaultAddress(this.shopId);
                return;
            }
            this.llAddressInfo.setVisibility(0);
            this.tvPromptAddress.setVisibility(8);
            ConstantInfo.mAddressId = this.addressId;
            this.fullAddress = intent.getStringExtra("fullAddress");
            this.userInfo = intent.getStringExtra("userInfo");
            this.distance = intent.getIntExtra("distance", 0);
            this.tvUserAddress.setText(this.fullAddress);
            this.tvUserInfo.setText(this.userInfo);
        } else if (i == 10 && i2 == 14) {
            this.tvInputRemark.setText(intent.getStringExtra(K.remark));
        } else if (i == 10 && i2 == 15) {
            this.tvSelectInvoice.setText(intent.getStringExtra("invoice"));
        } else if (i == PAY_REQUEST_CODE && i2 == PAY_SUCCESS_RESULT_CODE) {
            intent.getIntExtra("orderStatus", 0);
            Toast.makeText(this, "支付成功！", 0).show();
            Intent intent2 = new Intent(this, OrderDetailActivity.class);
            intent2.putExtra("orderStatus", "99");
            intent2.putExtra("orderId", this.tradeId);
            startActivity(intent2);
            setResult(BuildConfig.VERSION_CODE);
            finish();
        } else if (i == PAY_REQUEST_CODE && i2 == PAY_FAILED_RESULT_CODE) {
            int intExtra = intent.getIntExtra("orderStatus", 0);
            Intent intent3 = new Intent(this, OrderDetailActivity.class);
            intent3.putExtra("orderStatus", intExtra + "");
            intent3.putExtra("orderId", this.tradeId);
            startActivity(intent3);
            setResult(BuildConfig.VERSION_CODE);
            finish();
        } else if (i != PAY_PWD_REQUEST_CODE && this.payType.equals("CLOUDPAY") && intent != null) {
            String str = "";
            String string = intent.getExtras().getString("pay_result");
            if (string.equalsIgnoreCase("success")) {
                str = "支付成功！";
                this.confirmOrderPresenter.getOrderDetailInfo(this.tradeId);
            } else if (string.equalsIgnoreCase("fail")) {
                str = "支付失败！";
            } else if (string.equalsIgnoreCase("cancel")) {
                str = "用户取消了支付";
            }
            this.confirmOrderPresenter.getOrderDetailInfo(this.tradeId);
            ToastUtils.showShortCenter(str);
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
            intent.putExtra("orderId", this.tradeId);
            startActivity(intent);
            setResult(BuildConfig.VERSION_CODE);
            finish();
        } else if (eventMessage.getState() == EventMessage.EventState.WECHAT_PAY_ERROR) {
            Intent intent2 = new Intent(this, PayCheckStandActivity.class);
            intent2.putExtra("payFailed", e.f729b);
            intent2.putExtra("orderId", this.tradeId);
            intent2.putExtra("money", String.valueOf(this.payMoney));
            intent2.putExtra("shopName", this.mShopCarDataBean.get(0).getShopName());
            startActivity(intent2);
            setResult(BuildConfig.VERSION_CODE);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unRegisterEvent();
    }
}
