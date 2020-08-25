package coband.bsit.com.integral.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import com.convenient.qd.core.utils.CommonUtils;

public class ExchangeDialog extends Dialog {
    /* access modifiers changed from: private */
    public OnConfirmClickListener onConfirmClickListener;
    private TextView tv_cancel;
    private TextView tv_confirm;

    public interface OnConfirmClickListener {
        void onConFirmClick();
    }

    public ExchangeDialog(@NonNull Context context) {
        super(context, R.style.NoFunctionStyle);
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener2) {
        this.onConfirmClickListener = onConfirmClickListener2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.integral_dialog_exchange_layout, (ViewGroup) getWindow().getDecorView(), false);
        setContentView(inflate);
        this.tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
        this.tv_confirm = (TextView) inflate.findViewById(R.id.tv_confirm);
        getWindow().setGravity(17);
        getWindow().setLayout(CommonUtils.getScreenWidth(getContext()) - CommonUtils.dp2px(getContext(), 106), -2);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.tv_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ExchangeDialog.this.dismiss();
            }
        });
        this.tv_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ExchangeDialog.this.onConfirmClickListener != null) {
                    ExchangeDialog.this.onConfirmClickListener.onConFirmClick();
                }
            }
        });
    }
}
