package cn.xports.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import cn.xports.base.Constant;
import cn.xports.qdplugin.R;

public class PaySelectView extends LinearLayout {
    private View llAlipay;
    private View llWechat;
    /* access modifiers changed from: private */
    public String payMode;
    /* access modifiers changed from: private */
    public RadioButton rbAlipay;
    /* access modifiers changed from: private */
    public RadioButton rbWechat;
    /* access modifiers changed from: private */
    public PaySelectListener selectListener;

    public interface PaySelectListener {
        void onPaySelect(String str);
    }

    public PaySelectView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PaySelectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PaySelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflate(context, R.layout.view_pay_select, this);
        this.llAlipay = findViewById(R.id.llAlipay);
        this.llWechat = findViewById(R.id.llWechat);
        this.rbWechat = (RadioButton) findViewById(R.id.rbWechat);
        this.rbAlipay = (RadioButton) findViewById(R.id.rbAlipay);
        showPayModes();
        this.llAlipay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String unused = PaySelectView.this.payMode = Constant.ALI_PAY;
                PaySelectView.this.rbWechat.setChecked(false);
                PaySelectView.this.rbAlipay.setChecked(true);
                if (PaySelectView.this.selectListener != null) {
                    PaySelectView.this.selectListener.onPaySelect(PaySelectView.this.payMode);
                }
            }
        });
        this.llWechat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String unused = PaySelectView.this.payMode = Constant.WECHAT_PAY;
                PaySelectView.this.rbWechat.setChecked(true);
                PaySelectView.this.rbAlipay.setChecked(false);
                if (PaySelectView.this.selectListener != null) {
                    PaySelectView.this.selectListener.onPaySelect(PaySelectView.this.payMode);
                }
            }
        });
    }

    public String getPayMode() {
        return this.payMode;
    }

    private void showPayModes() {
        if (!TextUtils.isEmpty(Constant.PAYMODES)) {
            if (!Constant.PAYMODES.contains(Constant.ALI_PAY)) {
                this.llAlipay.setVisibility(8);
            } else if (!Constant.PAYMODES.contains(Constant.WECHAT_PAY)) {
                this.llWechat.setVisibility(8);
            }
        }
    }

    public void showDefaultSelect() {
        if (TextUtils.isEmpty(Constant.PAYMODES)) {
            this.payMode = Constant.ALI_PAY;
            this.rbAlipay.setChecked(true);
            return;
        }
        if (!Constant.PAYMODES.contains(Constant.ALI_PAY)) {
            this.payMode = Constant.WECHAT_PAY;
            this.rbWechat.setChecked(true);
        } else if (!Constant.PAYMODES.contains(Constant.WECHAT_PAY)) {
            this.payMode = Constant.WECHAT_PAY;
            this.rbAlipay.setChecked(true);
        } else {
            this.payMode = Constant.ALI_PAY;
            this.rbAlipay.setChecked(true);
        }
        invalidate();
    }

    public boolean isHasSelect() {
        return this.rbWechat.isChecked() || this.rbAlipay.isChecked();
    }

    public boolean isWechatSelect() {
        return this.rbWechat.isChecked();
    }

    public boolean isAliPaySelect() {
        return this.rbAlipay.isChecked();
    }

    public void setNoSelect() {
        if (isWechatSelect()) {
            this.rbWechat.setChecked(false);
        }
        if (isAliPaySelect()) {
            this.rbAlipay.setChecked(false);
        }
        this.payMode = "";
    }

    public PaySelectView setSelectListener(PaySelectListener paySelectListener) {
        this.selectListener = paySelectListener;
        return this;
    }
}
