package cn.xports.qd2.credit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.entity.CardPointInfo;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.SelectIntegralDialog;
import cn.xports.qd2.entity.CardData;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import com.google.gson.Gson;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.List;

public class CouponExchangeDetailActivity extends BaseBussActivity<IPresenter> {
    public static String EXCHANGE_ID = "exchange_id";
    public static String EXCHANGE_TYPE = "exchange_type";
    public static String RESULT_MSG = "result_msg";
    public static String RESULT_STATE = "result_state";
    /* access modifiers changed from: private */
    public List<CardPointInfo> cardPointInfoList = new ArrayList();
    private String exchangeId;
    private int exchangeNum;
    private int exhangeType;
    private SelectIntegralDialog integralDialog;
    private ImageView ivProduct;
    private String objectId;
    private TextView tvCouponName;
    private TextView tvCouponTitle;
    private TextView tvIntegral;
    private TextView tvUsePlace;

    static {
        StubApp.interface11(3948);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_coupon_exchange_detail;
    }

    /* access modifiers changed from: protected */
    public String getChildTitle() {
        return this.exhangeType == 1 ? "优惠券详情" : "礼品详情";
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.ivProduct = (ImageView) findViewById(R.id.iv_product);
        this.tvCouponName = (TextView) findViewById(R.id.tv_coupon_name);
        this.tvCouponTitle = (TextView) findViewById(R.id.tv_coupon_title);
        this.tvIntegral = (TextView) findViewById(R.id.tv_integral);
        this.tvUsePlace = (TextView) findViewById(R.id.tv_use_place);
        findViewById(R.id.tv_confirm_exchange).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CouponExchangeDetailActivity.this.showSelectDialog();
            }
        });
        if (this.exhangeType == 1) {
            this.tvCouponName.setVisibility(0);
        } else {
            this.ivProduct.setVisibility(0);
        }
        this.exhangeType = getIntent().getIntExtra(EXCHANGE_TYPE, 1);
        this.exchangeId = getStringExtra(EXCHANGE_ID);
        ApiUtil.getApi2().getExchangeDetail(this.exchangeId).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NonNull DataMap dataMap) {
                CouponExchangeDetailActivity.this.refreshUi(dataMap);
            }

            public void onError(@NonNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
        ApiUtil.getApi2().getPointCardList().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this) {
            public void onSuccess(@NonNull DataMap dataMap) {
                CardData cardData = (CardData) new Gson().fromJson(dataMap.toJson(), CardData.class);
                if (cardData != null && cardData.cardList.size() > 0 && cardData.cardList.get(0).cardInfo.size() > 0) {
                    CouponExchangeDetailActivity.this.cardPointInfoList.clear();
                    CouponExchangeDetailActivity.this.cardPointInfoList.addAll(cardData.cardList.get(0).cardInfo);
                }
                CouponExchangeDetailActivity.this.cardPointInfoList.clear();
                CouponExchangeDetailActivity.this.cardPointInfoList.addAll(cardData.cardList.get(0).cardInfo);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showSelectDialog() {
        this.integralDialog = new SelectIntegralDialog(this);
        this.integralDialog.setData(this.cardPointInfoList);
        this.integralDialog.show();
        this.integralDialog.setExchangeClickListener(new SelectIntegralDialog.ExchangeClickListener() {
            public void clickListener(int i) {
                CouponExchangeDetailActivity.this.exchangeGoodsOperation(i);
            }
        });
    }

    /* access modifiers changed from: private */
    public void exchangeGoodsOperation(int i) {
        ApiUtil.getApi2().exchangeGoods(this.objectId, this.cardPointInfoList.get(i).ecardNo, (long) this.exchangeNum).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this) {
            public void onSuccess(@NonNull DataMap dataMap) {
                CouponExchangeDetailActivity.this.startResultActivity(dataMap.getError(), dataMap.getMessage());
            }

            public void onError(@NonNull DataMap dataMap) {
                CouponExchangeDetailActivity.this.startResultActivity(1, dataMap.getMessage());
            }
        });
    }

    /* access modifiers changed from: private */
    public void startResultActivity(int i, String str) {
        Intent intent = new Intent(this, ExchangePointsResultActivity.class);
        intent.putExtra(RESULT_STATE, i);
        intent.putExtra(RESULT_MSG, str);
        startActivityForResult(intent, 80);
        if (this.integralDialog != null && this.integralDialog.isShowing()) {
            this.integralDialog.dismiss();
        }
    }

    public void initData() {
        this.exhangeType = getIntent().getIntExtra(EXCHANGE_TYPE, 1);
        this.exchangeId = getStringExtra(EXCHANGE_ID);
    }

    /* access modifiers changed from: private */
    public void refreshUi(DataMap dataMap) {
        if (this.exhangeType == 1) {
            this.tvCouponName.setText(dataMap.getDataMap("exchangeDetail").getString(K.couponName));
        } else {
            GlideUtil.loadImage(this, dataMap.getDataMap("exchangeDetail").getString("photo"), R.drawable.bg_default_square).into(this.ivProduct);
        }
        this.tvCouponTitle.setText(dataMap.getDataMap("exchangeDetail").getString(K.couponName));
        TextView textView = this.tvIntegral;
        textView.setText(dataMap.getDataMap("exchangeDetail").getString("exchangeNum") + "积分");
        this.tvUsePlace.setText(Html.fromHtml(dataMap.getDataMap("exchangeDetail").getString(K.remark)));
        this.objectId = dataMap.getDataMap("exchangeDetail").getString("objectId");
        this.exchangeNum = dataMap.getDataMap("exchangeDetail").getInteger("objectNum").intValue();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 100) {
            finish();
        }
    }
}
