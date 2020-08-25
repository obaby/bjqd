package cn.xports.baselib.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xports.baselib.R;

public class LoadingDialog {
    private Context context;
    private Dialog dialog;

    public LoadingDialog(Context context2) {
        this.context = context2;
        this.dialog = new Dialog(context2, R.style.dialog);
        this.dialog.setContentView(LayoutInflater.from(context2).inflate(R.layout.dialog_loading, (ViewGroup) null));
        this.dialog.setCanceledOnTouchOutside(false);
    }

    public LoadingDialog setCanceledOnTouchOutside(boolean z) {
        if (this.dialog != null) {
            this.dialog.setCanceledOnTouchOutside(z);
        }
        return this;
    }

    public void show() {
        if (this.dialog != null) {
            this.dialog.show();
        }
    }

    public void dismiss() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }
}
