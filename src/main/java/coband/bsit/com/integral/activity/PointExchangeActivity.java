package coband.bsit.com.integral.activity;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.adapter.QuanListAdapter;
import coband.bsit.com.integral.widget.ExchangeDialog;
import coband.bsit.com.integral.widget.ExchangeSuccessDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.utils.CommonUtils;

@Route(path = "/point/PointExchangeActivity")
public class PointExchangeActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout back;
    private QuanListAdapter quanListAdapter;
    RecyclerView rc_quan;
    TextView title;

    private void goExchange() {
    }

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return R.layout.integral_activity_point_exchange_layout;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointExchangeActivity] */
    /* access modifiers changed from: protected */
    public void initView() {
        this.title = (TextView) findViewById(R.id.title);
        this.back = (RelativeLayout) findViewById(R.id.back);
        this.rc_quan = (RecyclerView) findViewById(R.id.rc_quan);
        this.title.setText("兑换优惠券");
        this.rc_quan.setLayoutManager(new LinearLayoutManager(this));
        this.quanListAdapter = new QuanListAdapter(this);
        this.rc_quan.setAdapter(this.quanListAdapter);
    }

    /* access modifiers changed from: protected */
    public void initListener() {
        PointExchangeActivity.super.initListener();
        this.back.setOnClickListener(this);
        this.quanListAdapter.setOnExchangeClickListener(new QuanListAdapter.OnExchangeClickListener() {
            public void onExchangeClick() {
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initImmersionBar() {
        PointExchangeActivity.super.initImmersionBar();
        setStatusBar(R.id.title_header);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointExchangeActivity] */
    private void showExchangeSuccessDialog(final ExchangeDialog exchangeDialog) {
        final ExchangeSuccessDialog exchangeSuccessDialog = new ExchangeSuccessDialog(this);
        exchangeSuccessDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            /* JADX WARNING: type inference failed for: r2v1, types: [coband.bsit.com.integral.activity.PointExchangeActivity, android.app.Activity] */
            public void onShow(DialogInterface dialogInterface) {
                CommonUtils.backgroundAlpha(PointExchangeActivity.this, 0.5f);
            }
        });
        exchangeSuccessDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* JADX WARNING: type inference failed for: r2v1, types: [coband.bsit.com.integral.activity.PointExchangeActivity, android.app.Activity] */
            public void onDismiss(DialogInterface dialogInterface) {
                CommonUtils.backgroundAlpha(PointExchangeActivity.this, 1.0f);
            }
        });
        exchangeSuccessDialog.setOnCloseClickListener(new ExchangeSuccessDialog.OnCloseClickListener() {
            public void onCloseClick() {
                exchangeDialog.dismiss();
                exchangeSuccessDialog.dismiss();
            }
        });
        exchangeSuccessDialog.setOnUseBtnClickListener(new ExchangeSuccessDialog.OnUseBtnClickListener() {
            public void onUseBtnClick() {
                exchangeDialog.dismiss();
                exchangeSuccessDialog.dismiss();
                ToastUtils.showShort("去使用");
            }
        });
        exchangeSuccessDialog.show();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        }
    }
}
