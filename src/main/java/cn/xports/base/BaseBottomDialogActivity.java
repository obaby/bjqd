package cn.xports.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qdplugin.R;

public abstract class BaseBottomDialogActivity<P extends IPresenter> extends BaseBussActivity<P> {
    protected boolean cancelable = true;
    protected LinearLayout myContainer;
    protected View rootView;

    /* access modifiers changed from: protected */
    public String getChildTitle() {
        return "";
    }

    /* access modifiers changed from: protected */
    public abstract int getDialogLayout();

    /* access modifiers changed from: protected */
    public abstract void initDialogView();

    /* access modifiers changed from: protected */
    public boolean useBaseLayout() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.myContainer = (LinearLayout) findViewById(R.id.ll_container);
        this.rootView = findViewById(R.id.ll_root);
        View inflate = LayoutInflater.from(this).inflate(getDialogLayout(), this.myContainer, false);
        if (this.myContainer.getChildCount() > 0) {
            this.myContainer.removeAllViews();
        }
        this.myContainer.addView(inflate);
        initDialogView();
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_base_bottom_dialog;
    }

    /* access modifiers changed from: protected */
    public int getThemeId() {
        return R.style.TransparentTheme;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public void onBackPressed() {
        if (this.cancelable) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void setCanceledOnTouchOutside(boolean z) {
        if (z) {
            this.rootView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseBottomDialogActivity.this.finish();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void setCancelable(boolean z) {
        this.cancelable = z;
    }
}
