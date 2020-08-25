package cn.xports.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.Router;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qdplugin.R;
import com.alipay.sdk.cons.c;
import com.blankj.utilcode.util.ActivityUtils;
import java.util.HashMap;

public class AgreementBar extends LinearLayout {
    private CheckBox checkBox;
    private TextView tvAgreement;
    private TextView tvTip;

    public AgreementBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public AgreementBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AgreementBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflate(context, R.layout.v_agreement, this);
        this.tvAgreement = (TextView) findViewById(R.id.tv_agreement_name);
        this.tvTip = (TextView) findViewById(R.id.tv_tip);
        this.checkBox = (CheckBox) findViewById(R.id.checkbox);
    }

    public AgreementBar setAgreementName(String str) {
        this.tvAgreement.setText(str);
        return this;
    }

    public AgreementBar setAgreementClick(View.OnClickListener onClickListener) {
        this.tvAgreement.setOnClickListener(onClickListener);
        return this;
    }

    public AgreementBar setTip(String str) {
        this.tvTip.setText(str);
        return this;
    }

    public AgreementBar setCheckListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        return this;
    }

    public boolean isCheck() {
        return this.checkBox.isChecked();
    }

    public AgreementBar setCheck(boolean z) {
        this.checkBox.setChecked(z);
        return this;
    }

    public AgreementBar initNameAndDetail(final String str, String str2) {
        setAgreementName("《" + str + "》");
        SPUtil.Companion.getINSTANCE().save("agreement", str2).apply();
        setAgreementClick(new View.OnClickListener() {
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put(c.e, str);
                ActivityUtils.startActivity(Router.getIntent(Router.AGREEMENT, hashMap));
            }
        });
        return this;
    }

    public TextView getTvAgreement() {
        return this.tvAgreement;
    }

    public TextView getTvTip() {
        return this.tvTip;
    }

    public CheckBox getCheckBox() {
        return this.checkBox;
    }
}
