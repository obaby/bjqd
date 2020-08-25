package coband.bsit.com.integral.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import com.convenient.qd.core.utils.CommonUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExchangeSuccessDialog extends Dialog {
    private ImageView iv_close;
    /* access modifiers changed from: private */
    public OnCloseClickListener onCloseClickListener;
    /* access modifiers changed from: private */
    public OnUseBtnClickListener onUseBtnClickListener;
    private TextView tv_exchangeTime;
    private TextView tv_use;

    public interface OnCloseClickListener {
        void onCloseClick();
    }

    public interface OnUseBtnClickListener {
        void onUseBtnClick();
    }

    public void setOnCloseClickListener(OnCloseClickListener onCloseClickListener2) {
        this.onCloseClickListener = onCloseClickListener2;
    }

    public void setOnUseBtnClickListener(OnUseBtnClickListener onUseBtnClickListener2) {
        this.onUseBtnClickListener = onUseBtnClickListener2;
    }

    public ExchangeSuccessDialog(@NonNull Context context) {
        super(context, R.style.NoFunctionStyle);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.integral_dialog_exchange_success_layout, (ViewGroup) getWindow().getDecorView(), false);
        setContentView(inflate);
        this.tv_exchangeTime = (TextView) inflate.findViewById(R.id.tv_exchangeTime);
        this.tv_use = (TextView) inflate.findViewById(R.id.tv_use);
        this.iv_close = (ImageView) inflate.findViewById(R.id.iv_close);
        getWindow().setGravity(17);
        getWindow().setLayout(CommonUtils.getScreenWidth(getContext()) - CommonUtils.dp2px(getContext(), 30), -2);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setCanceledOnTouchOutside(false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(System.currentTimeMillis());
        TextView textView = this.tv_exchangeTime;
        textView.setText("500信豆兑换于" + simpleDateFormat.format(date));
        this.iv_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ExchangeSuccessDialog.this.onCloseClickListener != null) {
                    ExchangeSuccessDialog.this.onCloseClickListener.onCloseClick();
                }
            }
        });
        this.tv_use.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ExchangeSuccessDialog.this.onUseBtnClickListener != null) {
                    ExchangeSuccessDialog.this.onUseBtnClickListener.onUseBtnClick();
                }
            }
        });
    }
}
