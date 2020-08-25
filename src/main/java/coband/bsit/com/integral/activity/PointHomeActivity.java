package coband.bsit.com.integral.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.adapter.PointHomeIconAdapter;
import coband.bsit.com.integral.api.DataManager;
import coband.bsit.com.integral.bean.CouponInfoBean;
import coband.bsit.com.integral.bean.CreditListBean;
import coband.bsit.com.integral.bean.IconBean;
import coband.bsit.com.integral.widget.ExchangeDialog;
import coband.bsit.com.integral.widget.ExchangeSuccessDialog;
import coband.bsit.com.integral.widget.SpaceItemDecoration;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.convenient.qd.core.base.arouter.ARouterUtils;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.bean.BaseResBean;
import com.convenient.qd.core.bean.MenuTab;
import com.convenient.qd.core.net.BaseHttpObserver;
import com.convenient.qd.core.utils.CommonUtils;
import com.convenient.qd.core.utils.ToastUtils;
import com.convenient.qd.core.utils.UMEventUtil;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import io.reactivex.observers.DefaultObserver;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/point/PointHomeActivity")
public class PointHomeActivity extends BaseActivity implements View.OnClickListener {
    private MyBroadcastReceiver broadcastReceiver;
    private Button btn_point_home_dh;
    private LinearLayout btn_point_home_hxd;
    private TextView btn_point_home_more;
    private LinearLayout btn_point_home_rw;
    private LinearLayout btn_point_home_share;
    private LinearLayout btn_point_home_sign;
    private Button btn_point_home_xf;
    /* access modifiers changed from: private */
    public String couponid;
    /* access modifiers changed from: private */
    public int creditid;
    /* access modifiers changed from: private */
    public List<MenuTab> exchangeList;
    private LinearLayout home_back;
    /* access modifiers changed from: private */
    public List<MenuTab> menuTabs = new ArrayList();
    /* access modifiers changed from: private */
    public int point;
    /* access modifiers changed from: private */
    public PointHomeIconAdapter pointHomeIconAdapter;
    private RecyclerView rc_icon;
    private TextView tvHomePoint;
    /* access modifiers changed from: private */
    public TextView tv_home_point;
    private TextView tv_quanName;
    private TextView tv_quanValue;
    private TextView tv_scoreDetail;
    private View view_top;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
    public void initView() {
        this.home_back = (LinearLayout) findViewById(R.id.home_back);
        this.view_top = findViewById(R.id.view_top);
        this.btn_point_home_sign = (LinearLayout) findViewById(R.id.btn_point_home_sign);
        this.btn_point_home_rw = (LinearLayout) findViewById(R.id.btn_point_home_rw);
        this.btn_point_home_share = (LinearLayout) findViewById(R.id.btn_point_home_share);
        this.btn_point_home_hxd = (LinearLayout) findViewById(R.id.btn_point_home_hxd);
        this.tv_scoreDetail = (TextView) findViewById(R.id.tv_scoreDetail);
        this.tv_home_point = (TextView) findViewById(R.id.tv_home_point);
        this.btn_point_home_more = (TextView) findViewById(R.id.btn_point_home_more);
        this.btn_point_home_dh = (Button) findViewById(R.id.btn_point_home_dh);
        this.btn_point_home_xf = (Button) findViewById(R.id.btn_point_home_xf);
        this.tv_quanName = (TextView) findViewById(R.id.tv_quanName);
        this.tv_quanValue = (TextView) findViewById(R.id.tv_quanValue);
        this.rc_icon = (RecyclerView) findViewById(R.id.rc_icon);
        if (this.mImmersionBar != null) {
            this.mImmersionBar.titleBarMarginTop(R.id.home_title).init();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("refresh_point");
        this.broadcastReceiver = new MyBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.broadcastReceiver, intentFilter);
        this.rc_icon.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.rc_icon.addItemDecoration(new SpaceItemDecoration(CommonUtils.dp2px(this, 35)));
        this.pointHomeIconAdapter = new PointHomeIconAdapter(this, this.menuTabs);
        this.rc_icon.setAdapter(this.pointHomeIconAdapter);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        PointHomeActivity.super.initData();
        getCreditPoint(true);
        getPointIcon();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
    /* access modifiers changed from: private */
    public void getCreditPoint(boolean z) {
        if (z) {
            LoadingDiaLogUtils.showLoadingDialog(this);
        }
        DataManager.getInstance().getCredits(new DefaultObserver<CreditListBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
            public void onNext(CreditListBean creditListBean) {
                LoadingDiaLogUtils.dismisDialog();
                if (creditListBean != null) {
                    if (creditListBean.getCode() == 200) {
                        int unused = PointHomeActivity.this.point = creditListBean.getResult().getCredit();
                        TextView access$100 = PointHomeActivity.this.tv_home_point;
                        access$100.setText("" + creditListBean.getResult().getCredit());
                        return;
                    }
                    ToastUtils.showShort(PointHomeActivity.this, creditListBean.getMessage());
                }
            }

            public void onError(Throwable th) {
                LoadingDiaLogUtils.dismisDialog();
            }

            public void onComplete() {
                LoadingDiaLogUtils.dismisDialog();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initListener() {
        PointHomeActivity.super.initListener();
        this.home_back.setOnClickListener(this);
        this.btn_point_home_sign.setOnClickListener(this);
        this.tv_scoreDetail.setOnClickListener(this);
        this.btn_point_home_rw.setOnClickListener(this);
        this.btn_point_home_share.setOnClickListener(this);
        this.btn_point_home_hxd.setOnClickListener(this);
        this.btn_point_home_more.setOnClickListener(this);
        this.btn_point_home_xf.setOnClickListener(this);
        this.btn_point_home_dh.setOnClickListener(this);
        this.pointHomeIconAdapter.setOnMenuClickListener(new PointHomeIconAdapter.OnMenuClickListener() {
            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
            public void onMenuClick(MenuTab menuTab) {
                ARouterUtils.navigation(menuTab, PointHomeActivity.this);
            }
        });
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return R.layout.integral_activity_point_home;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.home_back) {
            finish();
        } else if (id == R.id.tv_scoreDetail) {
            UMEventUtil.onEventObject(this, "2002003", "");
            startActivity(PointScoreDetailActivity.class);
        } else if (id == R.id.btn_point_home_sign) {
            UMEventUtil.onEventObject(this, "2002002", "");
            startActivity(PointCalendarActivity.class);
        } else if (id == R.id.btn_point_home_rw) {
            UMEventUtil.onEventObject(this, "2002004", "");
            startActivity(PointTaskActivity.class);
        } else if (id == R.id.btn_point_home_share) {
            UMEventUtil.onEventObject(this, "2007002", "");
            startActivity(PointInviteActivity.class);
        } else if (id == R.id.btn_point_home_hxd) {
            UMEventUtil.onEventObject(this, "2002007", "");
            LoadingDiaLogUtils.showLoadingDialog(this);
            DataManager.getInstance().couponInfo(new BaseHttpObserver<BaseResBean<List<CouponInfoBean>>>() {
                public void onSuccess(BaseResBean<List<CouponInfoBean>> baseResBean) {
                    if (baseResBean != null) {
                        int unused = PointHomeActivity.this.creditid = ((CouponInfoBean) ((List) baseResBean.getResult()).get(0)).getCreditid();
                        String unused2 = PointHomeActivity.this.couponid = ((CouponInfoBean) ((List) baseResBean.getResult()).get(0)).getCouponid();
                        PointHomeActivity.this.showCouponDialog();
                    }
                }

                /* JADX WARNING: type inference failed for: r1v1, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
                public void onFailure(int i, String str) {
                    LoadingDiaLogUtils.dismisDialog();
                    ToastUtils.showShort(PointHomeActivity.this, str);
                }
            });
        } else if (id == R.id.btn_point_home_more || id == R.id.btn_point_home_xf) {
            UMEventUtil.onEventObject(this, "2002006", "");
            ARouterUtils.navigation("/menu/MenuActivity");
        } else if (id == R.id.btn_point_home_dh) {
            UMEventUtil.onEventObject(this, "2002005", "");
            ARouterUtils.navigation("/pay/RefuseSortActivity");
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
    /* access modifiers changed from: private */
    public void showCouponDialog() {
        final ExchangeDialog exchangeDialog = new ExchangeDialog(this);
        exchangeDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            /* JADX WARNING: type inference failed for: r2v1, types: [coband.bsit.com.integral.activity.PointHomeActivity, android.app.Activity] */
            public void onShow(DialogInterface dialogInterface) {
                CommonUtils.backgroundAlpha(PointHomeActivity.this, 0.5f);
            }
        });
        exchangeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* JADX WARNING: type inference failed for: r2v1, types: [coband.bsit.com.integral.activity.PointHomeActivity, android.app.Activity] */
            public void onDismiss(DialogInterface dialogInterface) {
                CommonUtils.backgroundAlpha(PointHomeActivity.this, 1.0f);
            }
        });
        exchangeDialog.setOnConfirmClickListener(new ExchangeDialog.OnConfirmClickListener() {
            public void onConFirmClick() {
                exchangeDialog.dismiss();
                PointHomeActivity.this.exchangeCoupon(exchangeDialog);
            }
        });
        exchangeDialog.show();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
    /* access modifiers changed from: private */
    public void exchangeCoupon(final ExchangeDialog exchangeDialog) {
        LoadingDiaLogUtils.showLoadingDialog(this);
        DataManager.getInstance().exchangeCoupon(this.creditid, this.couponid, new BaseHttpObserver<Object>() {
            /* JADX WARNING: type inference failed for: r3v1, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
            public void onSuccess(Object obj) {
                LoadingDiaLogUtils.dismisDialog();
                LocalBroadcastManager.getInstance(PointHomeActivity.this).sendBroadcast(new Intent("refresh_point"));
                PointHomeActivity.this.showExchangeSuccessDialog(exchangeDialog);
            }

            /* JADX WARNING: type inference failed for: r1v2, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
            public void onFailure(int i, String str) {
                exchangeDialog.dismiss();
                LoadingDiaLogUtils.dismisDialog();
                ToastUtils.showShort(PointHomeActivity.this, str);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
    /* access modifiers changed from: private */
    public void showExchangeSuccessDialog(final ExchangeDialog exchangeDialog) {
        final ExchangeSuccessDialog exchangeSuccessDialog = new ExchangeSuccessDialog(this);
        exchangeSuccessDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            /* JADX WARNING: type inference failed for: r2v1, types: [coband.bsit.com.integral.activity.PointHomeActivity, android.app.Activity] */
            public void onShow(DialogInterface dialogInterface) {
                CommonUtils.backgroundAlpha(PointHomeActivity.this, 0.5f);
            }
        });
        exchangeSuccessDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* JADX WARNING: type inference failed for: r2v1, types: [coband.bsit.com.integral.activity.PointHomeActivity, android.app.Activity] */
            public void onDismiss(DialogInterface dialogInterface) {
                CommonUtils.backgroundAlpha(PointHomeActivity.this, 1.0f);
            }
        });
        exchangeSuccessDialog.setOnCloseClickListener(new ExchangeSuccessDialog.OnCloseClickListener() {
            public void onCloseClick() {
                exchangeDialog.dismiss();
                exchangeSuccessDialog.dismiss();
            }
        });
        exchangeSuccessDialog.setOnUseBtnClickListener(new ExchangeSuccessDialog.OnUseBtnClickListener() {
            /* JADX WARNING: type inference failed for: r1v1, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
            public void onUseBtnClick() {
                if (PointHomeActivity.this.exchangeList != null && PointHomeActivity.this.exchangeList.size() > 0) {
                    exchangeDialog.dismiss();
                    exchangeSuccessDialog.dismiss();
                    ARouterUtils.navigation((MenuTab) PointHomeActivity.this.exchangeList.get(0), PointHomeActivity.this);
                    PointHomeActivity.this.finish();
                }
            }
        });
        exchangeSuccessDialog.show();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity, com.convenient.qd.core.base.mvp.BaseActivity] */
    /* access modifiers changed from: protected */
    public void onDestroy() {
        PointHomeActivity.super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.broadcastReceiver);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        MyBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            PointHomeActivity.this.getCreditPoint(false);
        }
    }

    private void getPointIcon() {
        DataManager.getInstance().getPointIcon(new BaseHttpObserver<BaseResBean<IconBean>>() {
            public void onSuccess(BaseResBean<IconBean> baseResBean) {
                if (baseResBean != null) {
                    PointHomeActivity.this.menuTabs.clear();
                    List<MenuTab> inviteList = ((IconBean) baseResBean.getResult()).getInviteList();
                    List unused = PointHomeActivity.this.exchangeList = ((IconBean) baseResBean.getResult()).getExchangeList();
                    PointHomeActivity.this.menuTabs.addAll(inviteList);
                    PointHomeActivity.this.pointHomeIconAdapter.notifyDataSetChanged();
                }
            }

            /* JADX WARNING: type inference failed for: r1v1, types: [android.content.Context, coband.bsit.com.integral.activity.PointHomeActivity] */
            public void onFailure(int i, String str) {
                ToastUtils.showShort(PointHomeActivity.this, str);
            }
        });
    }
}
