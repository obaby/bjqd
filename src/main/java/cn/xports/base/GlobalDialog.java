package cn.xports.base;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.qdplugin.R;

public class GlobalDialog {
    /* access modifiers changed from: private */
    public OnButtonClick buttonClick;
    private Context context;
    /* access modifiers changed from: private */
    public AlertDialog dialog;
    private TextView tvClose;
    private TextView tvSubmit;
    private View vertical;

    public interface OnButtonClick {
        void onClick(int i);
    }

    public GlobalDialog(Context context2, String str) {
        this.context = context2;
        View inflate = View.inflate(context2, R.layout.dialog_global_default, (ViewGroup) null);
        this.tvClose = (TextView) inflate.findViewById(R.id.tv_close);
        this.tvSubmit = (TextView) inflate.findViewById(R.id.tv_submit);
        this.vertical = inflate.findViewById(R.id.v_vertical);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(str);
        this.tvClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (GlobalDialog.this.dialog != null) {
                    GlobalDialog.this.dialog.dismiss();
                }
                if (GlobalDialog.this.buttonClick != null) {
                    GlobalDialog.this.buttonClick.onClick(0);
                }
            }
        });
        this.tvSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (GlobalDialog.this.dialog != null) {
                    GlobalDialog.this.dialog.dismiss();
                }
                if (GlobalDialog.this.buttonClick != null) {
                    GlobalDialog.this.buttonClick.onClick(1);
                }
            }
        });
        this.dialog = new AlertDialog.Builder(context2).setView(inflate).setCancelable(false).create();
        this.dialog.show();
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        this.dialog.getWindow().setBackgroundDrawableResource(17170445);
        attributes.width = DensityUtil.dp2px(268.0f);
        this.dialog.getWindow().setAttributes(attributes);
    }

    public GlobalDialog(Context context2, String str, String str2) {
        this.context = context2;
        View inflate = View.inflate(context2, R.layout.dialog_global_default_2, (ViewGroup) null);
        this.tvClose = (TextView) inflate.findViewById(R.id.tv_close);
        this.tvSubmit = (TextView) inflate.findViewById(R.id.tv_submit);
        this.vertical = inflate.findViewById(R.id.v_vertical);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(str);
        ((TextView) inflate.findViewById(R.id.tv_title)).setText(str2);
        this.tvClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (GlobalDialog.this.dialog != null) {
                    GlobalDialog.this.dialog.dismiss();
                }
                if (GlobalDialog.this.buttonClick != null) {
                    GlobalDialog.this.buttonClick.onClick(0);
                }
            }
        });
        this.tvSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (GlobalDialog.this.dialog != null) {
                    GlobalDialog.this.dialog.dismiss();
                }
                if (GlobalDialog.this.buttonClick != null) {
                    GlobalDialog.this.buttonClick.onClick(1);
                }
            }
        });
        this.dialog = new AlertDialog.Builder(context2).setCancelable(false).setView(inflate).create();
        this.dialog.show();
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        this.dialog.getWindow().setBackgroundDrawableResource(17170445);
        attributes.width = DensityUtil.dp2px(268.0f);
        this.dialog.getWindow().setAttributes(attributes);
    }

    public GlobalDialog setRightText(String str) {
        this.tvSubmit.setText(str);
        return this;
    }

    public GlobalDialog setLeftText(String str) {
        this.tvClose.setText(str);
        return this;
    }

    public GlobalDialog setButtonClick(OnButtonClick onButtonClick) {
        this.buttonClick = onButtonClick;
        return this;
    }

    public GlobalDialog setCancelVisible(boolean z) {
        if (this.tvClose != null) {
            int i = 8;
            this.tvClose.setVisibility(z ? 0 : 8);
            View view = this.vertical;
            if (z) {
                i = 0;
            }
            view.setVisibility(i);
        }
        return this;
    }
}
