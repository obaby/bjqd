package cn.xports.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseBottomDialog extends Dialog {
    protected OnItemClickListener clickListener;
    protected Context context;

    public interface OnItemClickListener {
        void onItemClick(int i, Object... objArr);
    }

    /* access modifiers changed from: protected */
    public abstract void onCreate();

    public BaseBottomDialog(@NonNull Context context2) {
        super(context2);
        this.context = context2;
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        Window window = getWindow();
        window.requestFeature(1);
        onCreate();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        window.setBackgroundDrawableResource(17170445);
    }

    public BaseBottomDialog setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
        return this;
    }

    /* access modifiers changed from: protected */
    public <T> void performClick(int i, Object... objArr) {
        if (this.clickListener != null) {
            this.clickListener.onItemClick(i, objArr);
        }
    }
}
