package cn.xports.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.baselib.mvp.BaseActivity;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.LoadingDialog;
import cn.xports.export.EventHandler;
import cn.xports.qdplugin.R;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.convenient.qd.lib.adaptScreen.IAdaptScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseBussActivity<P extends IPresenter> extends BaseActivity<P> implements IAdaptScreen {
    protected View llNoData;
    protected int loadingCount = 0;
    private LoadingDialog loadingDialog;
    protected View loadingView;
    protected FrameLayout mContainer;
    protected ImageView mIvBack;
    protected ImageView mIvRight;
    protected TextView mTvRight;
    protected TextView mTvTitle;
    protected View rlRoot;
    protected View rlTitleBar;
    protected View topSpace;
    protected TextView tvTip;

    public boolean cancelAdapt() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract String getChildTitle();

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    public int getScreenSize() {
        return 375;
    }

    /* access modifiers changed from: protected */
    public int getThemeId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract void initView();

    public boolean isBaseOnWidth() {
        return true;
    }

    public boolean needResetAdapt() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean useBaseLayout() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.loadingDialog = new LoadingDialog(this);
        setTheme(getThemeId() == 0 ? R.style.XportsTheme : getThemeId());
        super.onCreate(bundle);
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(this, -1);
        if (useBaseLayout()) {
            setContentView(R.layout.activity_buss_base);
            this.rlRoot = findViewById(R.id.rl_base);
            this.mIvBack = (ImageView) findViewById(R.id.iv_back);
            this.mIvRight = (ImageView) findViewById(R.id.iv_right);
            this.mIvBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseBussActivity.this.finish();
                }
            });
            this.topSpace = findViewById(R.id.v_top_space);
            this.rlTitleBar = findViewById(R.id.rl_bar);
            this.mTvTitle = (TextView) findViewById(R.id.tv_title);
            this.mTvRight = (TextView) findViewById(R.id.tv_right);
            this.llNoData = findViewById(R.id.ll_no_data);
            this.loadingView = findViewById(R.id.ll_loading);
            this.tvTip = (TextView) findViewById(R.id.tv_tip);
            this.mTvTitle.setText(getChildTitle() == null ? "" : getChildTitle());
            this.mContainer = (FrameLayout) findViewById(R.id.container);
            View inflate = View.inflate(this, getLayoutId(), (ViewGroup) null);
            if (this.mContainer.getChildCount() > 0) {
                this.mContainer.removeAllViews();
            }
            this.mContainer.addView(inflate);
        } else {
            setContentView(getLayoutId());
        }
        initView();
    }

    public void showMsg(@NotNull String str) {
        ToastUtils.showShort(str);
    }

    /* access modifiers changed from: protected */
    public void useBlackTitle(boolean z) {
        if (this.mIvBack != null) {
            this.mIvBack.setImageResource(z ? R.drawable.ic_back_black : R.drawable.ic_back_white);
        }
        if (this.mTvTitle != null) {
            this.mTvTitle.setTextColor(z ? getResources().getColor(R.color.black_353) : -1);
        }
    }

    public void finish() {
        super.finish();
        hideLoading();
        hideNoData();
    }

    /* access modifiers changed from: protected */
    public void underStatusBar(boolean z, boolean z2) {
        if (this.topSpace != null) {
            this.topSpace.setVisibility(z ? 8 : 0);
        }
        if (this.rlRoot != null) {
            this.rlRoot.setFitsSystemWindows(false);
            BarUtils.setStatusBarLightMode(this, !z2);
            useBlackTitle(!z2);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.rlTitleBar.getLayoutParams();
            layoutParams.setMargins(0, BarUtils.getStatusBarHeight(), 0, 0);
            this.rlTitleBar.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: protected */
    public void setTitleBarColor(int i) {
        if (this.rlTitleBar != null) {
            this.rlTitleBar.setBackgroundColor(i);
        }
    }

    public void showTipDialog(String str) {
        new AlertDialog.Builder(this).setMessage((CharSequence) str).setPositiveButton((CharSequence) "确定", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    public void showNoData(String str) {
        if (this.llNoData != null) {
            this.llNoData.setVisibility(0);
            if (!TextUtils.isEmpty(str)) {
                this.tvTip.setText(str);
            } else {
                this.tvTip.setText("暂无数据");
            }
        }
    }

    public void hideNoData() {
        if (this.llNoData != null && this.llNoData.getVisibility() == 0) {
            this.llNoData.setVisibility(8);
        }
    }

    public void showLoading() {
        this.loadingCount++;
        if (EventHandler.getInstance().getLoadingListener() != null) {
            EventHandler.getInstance().getLoadingListener().showLoading();
        } else if (this.loadingDialog != null) {
            this.loadingDialog.show();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.loadingCount > 0) {
            this.loadingCount = 1;
            hideLoading();
        }
        super.onStop();
    }

    public void hideLoading() {
        this.loadingCount--;
        if (this.loadingCount <= 0) {
            if (EventHandler.getInstance().getLoadingListener() != null) {
                EventHandler.getInstance().getLoadingListener().hideLoading();
            } else if (this.loadingDialog != null) {
                this.loadingDialog.dismiss();
            }
        }
    }

    /* access modifiers changed from: protected */
    public View getEmptyView(String str) {
        View inflate = View.inflate(this, R.layout.empty_view, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_tip)).setText(str);
        return inflate;
    }
}
