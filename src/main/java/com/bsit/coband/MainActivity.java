package com.bsit.coband;

import android.app.Dialog;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import cn.xports.qd2.entity.K;
import coband.bsit.com.integral.R;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.boruan.qq.zxgylibrary.ui.widgets.CommonDialog;
import com.bsit.coband.adapter.MainFragvpAdapter;
import com.bsit.coband.cardbag.CardbagFragment;
import com.bsit.coband.home.HomeFragment;
import com.bsit.coband.life.LifeFragment;
import com.bsit.coband.my.MyFragment;
import com.bsit.coband.net.api.AppModule;
import com.bsit.coband.net.bean.VUpdateRequest;
import com.bsit.coband.views.CustomViewPager;
import com.convenient.qd.core.base.arouter.ARouterUtils;
import com.convenient.qd.core.base.eventbus.Event;
import com.convenient.qd.core.base.eventbus.EventBusUtils;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.user.UserDBHelper;
import com.convenient.qd.core.utils.NetworkUtils;
import com.convenient.qd.core.utils.UMEventUtil;
import com.convenient.qd.module.qdt.api.QDTModule;
import com.convenient.qd.module.user.api.UserModule;
import com.linewell.common.utils.GovServiceInstance;
import java.util.ArrayList;
import org.greenrobot.eventbus.Subscribe;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity {
    private boolean isLoginOutDialogShow;
    /* access modifiers changed from: private */
    public Dialog mDialog;
    @BindView(2131298398)
    LinearLayout mLltCardyards;
    @BindView(2131298992)
    RadioGroup mRadiogroup;
    @BindView(2131300762)
    CustomViewPager mViewPager;
    private long oldTime = 0;
    /* access modifiers changed from: private */
    public String qrCodeAuth;
    @BindView(2131299008)
    RadioButton rbMy;
    /* access modifiers changed from: private */
    public String warningNotice;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return 2131427475;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EventBusUtils.register(this);
        SPUtils.getInstance().put("isfirst", true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(HomeFragment.newInstance());
        arrayList.add(LifeFragment.newInstance());
        arrayList.add(CardbagFragment.newInstance());
        arrayList.add(MyFragment.newInstance());
        MainFragvpAdapter mainFragvpAdapter = new MainFragvpAdapter(getSupportFragmentManager(), arrayList);
        this.mViewPager.setPagingEnabled(false);
        this.mViewPager.setAdapter(mainFragvpAdapter);
        this.mViewPager.setOffscreenPageLimit(4);
        this.mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                MainActivity.lambda$initView$0(MainActivity.this, radioGroup, i);
            }
        });
        doGetVupdate();
        getIdNum();
    }

    public static /* synthetic */ void lambda$initView$0(MainActivity mainActivity, RadioGroup radioGroup, int i) {
        switch (radioGroup.indexOfChild(radioGroup.findViewById(i))) {
            case 0:
                mainActivity.mViewPager.setCurrentItem(0);
                return;
            case 1:
                mainActivity.mViewPager.setCurrentItem(1);
                return;
            case 3:
                mainActivity.mViewPager.setCurrentItem(2);
                return;
            case 4:
                mainActivity.mViewPager.setCurrentItem(3);
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.bsit.coband.MainActivity] */
    @OnClick({2131298398})
    public void onViewClicked() {
        if (UserDBHelper.getInstance().getUserInfo() == null) {
            gotoLogin("home");
            return;
        }
        UMEventUtil.onEventObject(this, "1001012", "");
        handlerJumpCode();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bsit.coband.MainActivity, android.app.Activity] */
    private void handlerJumpCode() {
        if (K.k0.equals(this.qrCodeAuth)) {
            ARouterUtils.navigation("/qdt/QDHomeActivity");
        } else if ("1".equals(this.qrCodeAuth)) {
            if (UserDBHelper.getInstance().getNameAuthFlag() != 1) {
                new CommonDialog.Builder(this).setTitle("").setMessage(this.warningNotice).setNegativeButton("稍后再试", new 2(this)).setPositiveButton("去实名", new 1(this)).show();
            } else {
                ARouterUtils.navigation("/qdt/QDHomeActivity");
            }
        } else if (!"2".equals(this.qrCodeAuth)) {
            ARouterUtils.navigation("/qdt/QDHomeActivity");
        } else if (UserDBHelper.getInstance().getNameAuthFlag() != 1) {
            new CommonDialog.Builder(this).setTitle("").setMessage(this.warningNotice).setPositiveButton("去实名", new 3(this)).setNegativeButton("取消", (View.OnClickListener) null).show();
        } else {
            ARouterUtils.navigation("/qdt/QDHomeActivity");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainActivity.super.onResume();
        checkIsOpenCode();
        EventBusUtils.sendEvent(new Event(6001));
    }

    private void checkIsOpenCode() {
        QDTModule.getInstance().getIsOpenCodeRide(new 4(this));
    }

    private void getIdNum() {
        UserModule.getInstance().GetUserIDNO(new 5(this));
    }

    public void doGetVupdate() {
        if (NetworkUtils.isNetAvailable(this.activity) && !isFinishing() && !isDestroyed()) {
            AppModule.getInstance().getVupdateData(new 6(this));
        }
    }

    /* access modifiers changed from: private */
    public void activeDialog(int i) {
        AppModule.getInstance().activeDialog(i, new 7(this));
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [android.content.Context, com.bsit.coband.MainActivity] */
    /* access modifiers changed from: private */
    public void onShowUpdate(VUpdateRequest vUpdateRequest) {
        if (this.mDialog == null || !this.mDialog.isShowing()) {
            try {
                View inflate = LayoutInflater.from(getBaseContext()).inflate(2131428778, (ViewGroup) null);
                LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.update_info);
                LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.upadte_progress);
                TextView textView = (TextView) inflate.findViewById(R.id.update_id_cancel);
                Button button = (Button) inflate.findViewById(R.id.update_id_ok);
                ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.update_progress_bar);
                TextView textView2 = (TextView) inflate.findViewById(R.id.update_progress_text);
                TextView textView3 = (TextView) inflate.findViewById(R.id.tv_update_content);
                if (vUpdateRequest != null && !TextUtils.isEmpty(vUpdateRequest.getUpdateContent())) {
                    textView3.setText(vUpdateRequest.getUpdateContent());
                }
                if (vUpdateRequest == null || vUpdateRequest.getForce() == null || !vUpdateRequest.getForce().equals("1")) {
                    textView.setVisibility(0);
                } else {
                    textView.setVisibility(8);
                }
                progressBar.setMax(100);
                this.mDialog = new Dialog(this);
                this.mDialog.requestWindowFeature(1);
                this.mDialog.setContentView(inflate);
                if (this.mDialog.getWindow() != null) {
                    this.mDialog.getWindow().setBackgroundDrawableResource(17170445);
                }
                this.mDialog.setCancelable(false);
                this.mDialog.show();
                textView.setOnClickListener(new View.OnClickListener(vUpdateRequest) {
                    private final /* synthetic */ VUpdateRequest f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        MainActivity.lambda$onShowUpdate$1(MainActivity.this, this.f$1, view);
                    }
                });
                button.setOnClickListener(new 8(this, linearLayout, linearLayout2, vUpdateRequest, progressBar));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static /* synthetic */ void lambda$onShowUpdate$1(MainActivity mainActivity, VUpdateRequest vUpdateRequest, View view) {
        mainActivity.mDialog.cancel();
        if (vUpdateRequest != null && vUpdateRequest.getForce() != null && vUpdateRequest.getForce().equals("2")) {
            mainActivity.mDialog.dismiss();
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.bsit.coband.MainActivity] */
    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.oldTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", 0).show();
            this.oldTime = currentTimeMillis;
            return;
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        MainActivity.super.onDestroy();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.bsit.coband.MainActivity, android.app.Activity] */
    @Subscribe
    public void onMyRefreshEvent(Event event) {
        int code = event.getCode();
        if (code != 5006) {
            switch (code) {
                case PointerIconCompat.TYPE_HAND:
                case PointerIconCompat.TYPE_HELP:
                    GovServiceInstance.loginout(this);
                    return;
                default:
                    return;
            }
        } else {
            doGetVupdate();
        }
    }
}
