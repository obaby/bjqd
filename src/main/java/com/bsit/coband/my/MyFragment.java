package com.bsit.coband.my;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import anet.channel.strategy.dispatch.DispatchConstants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import coband.bsit.com.integral.R;
import com.bsit.coband.MainActivity;
import com.bsit.coband.net.api.AppModule;
import com.bsit.coband.qdScore.bean.QueryResult;
import com.convenient.qd.core.base.arouter.ARouterUtils;
import com.convenient.qd.core.base.eventbus.EventBusUtils;
import com.convenient.qd.core.base.mvp.BaseFragment;
import com.convenient.qd.core.bean.UserInfo;
import com.convenient.qd.core.user.UserDBHelper;
import com.convenient.qd.core.utils.ImageLoader;
import com.convenient.qd.core.utils.UMEventUtil;
import com.convenient.qd.module.user.api.UserModule;
import com.gyf.immersionbar.ImmersionBar;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Objects;

public class MyFragment extends BaseFragment<IMyAtView, MyAtPresenter> implements IMyAtView {
    private MyBroadcastReceiver broadcastReceiver;
    private Context context;
    @BindView(2131297447)
    ImageView imageAuth;
    @BindView(2131297483)
    CircleImageView imgPersonHead;
    /* access modifiers changed from: private */
    public boolean isPromisUser;
    @BindView(2131297784)
    ImageView iv_promis;
    protected ImmersionBar mImmersionBar;
    @BindView(2131297816)
    CircleImageView mIvSmsr;
    @BindView(2131298632)
    RelativeLayout mToolbar_title;
    @BindView(2131298613)
    LinearLayout my_item_quan;
    /* access modifiers changed from: private */
    public QueryResult queryResult;
    @BindView(2131298628)
    RecyclerView recyclerView;
    @BindView(2131300119)
    TextView tvFace;
    @BindView(2131300310)
    TextView tvName;
    Unbinder unbinder;
    private UserInfo userInfo;

    public void hideProgressDialog() {
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return 2131428285;
    }

    public void showError(String str) {
    }

    public void showProgressDialog() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = MyFragment.super.onCreateView(layoutInflater, viewGroup, bundle);
        this.unbinder = ButterKnife.bind((Object) this, onCreateView);
        return onCreateView;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.bsit.coband.my.MyFragment, android.support.v4.app.Fragment] */
    public static Fragment newInstance() {
        return new MyFragment();
    }

    /* access modifiers changed from: protected */
    public MyAtPresenter createPresenter() {
        return new MyAtPresenter((MainActivity) getActivity(), (MainActivity) getActivity());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.bsit.coband.my.MyFragment, com.convenient.qd.core.base.mvp.BaseFragment, android.support.v4.app.Fragment] */
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        MyFragment.super.onViewCreated(view, bundle);
        if (Build.VERSION.SDK_INT > 21) {
            this.mImmersionBar = ImmersionBar.with(this);
            this.mImmersionBar.init();
            this.mImmersionBar.statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.black).init();
        }
    }

    public void initView(View view) {
        this.context = getActivity();
        if (Build.VERSION.SDK_INT > 21) {
            ImmersionBar.setTitleBar(getActivity(), new View[]{this.mToolbar_title});
        }
        EventBusUtils.register(this);
        loginInitView();
        this.userInfo = UserDBHelper.getInstance().getUserInfo();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mainShowDialog");
        this.broadcastReceiver = new MyBroadcastReceiver(this);
        LocalBroadcastManager.getInstance(this.context).registerReceiver(this.broadcastReceiver, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        MyFragment.super.initData();
        this.mPresenter.getItemList();
        this.mPresenter.getUnReadMsg();
        queryUserCredit();
    }

    public void queryUserCredit() {
        AppModule.getInstance().queryUserCredit(new 1(this));
    }

    /* access modifiers changed from: private */
    public void loginInitView() {
        ((FragmentActivity) Objects.requireNonNull(getActivity())).runOnUiThread(new Runnable() {
            public final void run() {
                MyFragment.lambda$loginInitView$1(MyFragment.this);
            }
        });
    }

    public static /* synthetic */ void lambda$loginInitView$1(MyFragment myFragment) {
        myFragment.userInfo = UserDBHelper.getInstance().getUserInfo();
        myFragment.mIvSmsr.setVisibility(8);
        if (myFragment.userInfo != null) {
            if (myFragment.getActivity() != null) {
                myFragment.getActivity().runOnUiThread(new Runnable() {
                    public final void run() {
                        MyFragment.lambda$null$0(MyFragment.this);
                    }
                });
            } else {
                myFragment.tvName.setText(myFragment.userInfo.getNickName());
                myFragment.tvFace.setText(2131690076);
                myFragment.imageAuth.setVisibility(8);
                ImageLoader.loadImg(myFragment.context, myFragment.userInfo.getUserAvatar(), 2131558670, myFragment.imgPersonHead);
            }
            if (myFragment.isPromisUser) {
                myFragment.iv_promis.setVisibility(0);
            } else {
                myFragment.iv_promis.setVisibility(8);
            }
        } else {
            myFragment.tvName.setText(2131690074);
            myFragment.tvFace.setText(2131690076);
            myFragment.imageAuth.setVisibility(8);
            ImageLoader.loadImg(myFragment.context, "", 2131558670, myFragment.imgPersonHead);
            myFragment.iv_promis.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$null$0(MyFragment myFragment) {
        myFragment.tvName.setText(myFragment.userInfo.getNickName());
        if (myFragment.userInfo.getNameAuthFlag() == 1) {
            if (myFragment.userInfo.getFaceAuthFlag() == 1) {
                myFragment.imageAuth.setVisibility(8);
                myFragment.tvFace.setText(2131690069);
                myFragment.mIvSmsr.setVisibility(0);
            } else {
                myFragment.imageAuth.setVisibility(0);
                myFragment.tvFace.setText(2131690072);
                ImageLoader.loadImg(myFragment.context, "", 2131558654, myFragment.imageAuth);
            }
            myFragment.getIdNum();
        } else {
            myFragment.imageAuth.setVisibility(0);
            myFragment.tvFace.setText(2131690068);
            ImageLoader.loadImg(myFragment.context, "", 2131558653, myFragment.imageAuth);
        }
        ImageLoader.loadImg(myFragment.context, myFragment.userInfo.getUserAvatar(), 2131558670, myFragment.imgPersonHead);
    }

    private void getIdNum() {
        UserModule.getInstance().GetUserIDNO(new 2(this));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        loginInitView();
        queryUserCredit();
     */
    @org.greenrobot.eventbus.Subscribe
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMyRefreshEvent(com.convenient.qd.core.base.eventbus.Event r5) {
        /*
            r4 = this;
            int r5 = r5.getCode()
            r0 = 5005(0x138d, float:7.013E-42)
            if (r5 == r0) goto L_0x0043
            r0 = 6001(0x1771, float:8.409E-42)
            if (r5 == r0) goto L_0x0037
            r0 = 10001(0x2711, float:1.4014E-41)
            if (r5 == r0) goto L_0x003e
            switch(r5) {
                case 1001: goto L_0x002f;
                case 1002: goto L_0x002f;
                case 1003: goto L_0x002f;
                default: goto L_0x0013;
            }
        L_0x0013:
            switch(r5) {
                case 5001: goto L_0x00c0;
                case 5002: goto L_0x0018;
                case 5003: goto L_0x002f;
                default: goto L_0x0016;
            }
        L_0x0016:
            goto L_0x00da
        L_0x0018:
            com.convenient.qd.core.user.UserDBHelper r5 = com.convenient.qd.core.user.UserDBHelper.getInstance()
            com.convenient.qd.core.bean.UserInfo r5 = r5.getUserInfo()
            r4.userInfo = r5
            android.widget.TextView r5 = r4.tvName
            com.convenient.qd.core.bean.UserInfo r0 = r4.userInfo
            java.lang.String r0 = r0.getNickName()
            r5.setText(r0)
            goto L_0x00da
        L_0x002f:
            r4.loginInitView()
            r4.queryUserCredit()
            goto L_0x00da
        L_0x0037:
            com.convenient.qd.core.base.mvp.BasePresenter r5 = r4.mPresenter
            com.bsit.coband.my.MyAtPresenter r5 = (com.bsit.coband.my.MyAtPresenter) r5
            r5.getUnReadMsg()
        L_0x003e:
            r4.queryUserCredit()
            goto L_0x00da
        L_0x0043:
            com.convenient.qd.core.user.UserDBHelper r5 = com.convenient.qd.core.user.UserDBHelper.getInstance()
            int r5 = r5.getNameAuthFlag()
            r0 = 1
            if (r5 != r0) goto L_0x00c0
            com.convenient.qd.core.user.UserDBHelper r5 = com.convenient.qd.core.user.UserDBHelper.getInstance()
            com.convenient.qd.core.bean.UserIdNo r5 = r5.getUserIdNo()
            if (r5 == 0) goto L_0x00c0
            com.convenient.qd.core.user.UserDBHelper r5 = com.convenient.qd.core.user.UserDBHelper.getInstance()
            com.convenient.qd.core.bean.UserInfo r5 = r5.getUserInfo()
            r4.userInfo = r5
            com.linewell.common.utils.GovServiceLoginParams r5 = new com.linewell.common.utils.GovServiceLoginParams
            r5.<init>()
            com.convenient.qd.core.bean.UserInfo r1 = r4.userInfo
            java.lang.String r1 = r1.getUserId()
            r5.setUserId(r1)
            com.convenient.qd.core.bean.UserInfo r1 = r4.userInfo
            java.lang.String r1 = r1.getMobile()
            r5.setPhone(r1)
            com.convenient.qd.core.user.UserDBHelper r1 = com.convenient.qd.core.user.UserDBHelper.getInstance()
            com.convenient.qd.core.bean.UserIdNo r1 = r1.getUserIdNo()
            java.lang.String r1 = r1.getIdNo()
            r5.setCardNo(r1)
            java.lang.String r1 = "1"
            r5.setCardType(r1)
            com.convenient.qd.core.user.UserDBHelper r1 = com.convenient.qd.core.user.UserDBHelper.getInstance()
            com.convenient.qd.core.bean.UserIdNo r1 = r1.getUserIdNo()
            java.lang.String r1 = r1.getRealName()
            r5.setRealName(r1)
            java.lang.String r1 = ""
            r5.setSex(r1)
            java.lang.String r1 = ""
            r5.setUserFaceCertPhoto(r1)
            com.convenient.qd.core.bean.UserInfo r1 = r4.userInfo
            int r1 = r1.getFaceAuthFlag()
            if (r1 != r0) goto L_0x00af
            goto L_0x00b0
        L_0x00af:
            r0 = 0
        L_0x00b0:
            r5.setUserFaceFlag(r0)
            com.linewell.common.utils.ServiceHelper r0 = com.linewell.common.utils.ServiceHelper.getInstance()
            android.app.Activity r1 = r4.mActivity
            java.lang.String r2 = com.convenient.qd.module.gov.GOVConstant.serviceId
            com.linewell.common.dto.AccessServiceDTO r3 = com.convenient.qd.module.gov.GOVConstant.accessServiceDTO
            r0.updateAuthInfo(r1, r5, r2, r3)
        L_0x00c0:
            com.convenient.qd.core.user.UserDBHelper r5 = com.convenient.qd.core.user.UserDBHelper.getInstance()
            com.convenient.qd.core.bean.UserInfo r5 = r5.getUserInfo()
            r4.userInfo = r5
            android.content.Context r5 = r4.context
            com.convenient.qd.core.bean.UserInfo r0 = r4.userInfo
            java.lang.String r0 = r0.getUserAvatar()
            r1 = 2131558670(0x7f0d010e, float:1.8742662E38)
            de.hdodenhof.circleimageview.CircleImageView r2 = r4.imgPersonHead
            com.convenient.qd.core.utils.ImageLoader.loadImg(r5, r0, r1, r2)
        L_0x00da:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.coband.my.MyFragment.onMyRefreshEvent(com.convenient.qd.core.base.eventbus.Event):void");
    }

    @OnClick({2131298630, 2131297483, 2131296611, 2131297447, 2131298612, 2131298617, 2131298633, 2131298615, 2131298613})
    public void onViewClicked(View view) {
        if (this.userInfo != null) {
            switch (view.getId()) {
                case 2131296611:
                case 2131297483:
                    ARouterUtils.navigation("/module_user/edit/UserEditActivity");
                    return;
                case 2131297447:
                    ARouterUtils.navigation("/face/IdentityActivity");
                    return;
                case 2131298612:
                    UMEventUtil.onEventObject(this.mActivity, "2001001", "");
                    ARouterUtils.navigation("/module_user/order/UserOrderActivity");
                    return;
                case 2131298613:
                    UMEventUtil.onEventObject(this.mActivity, "1602002", "");
                    goToQDScore();
                    return;
                case 2131298615:
                    UMEventUtil.onEventObject(this.mActivity, "2002001", "");
                    ARouterUtils.navigation("/point/PointHomeActivity");
                    return;
                case 2131298617:
                    ARouterUtils.navigation("/point/PointExchangeActivity");
                    return;
                case 2131298630:
                    UMEventUtil.onEventObject(this.mActivity, "2008012", "");
                    ARouterUtils.navigation("/module_user/setting/UserSettingActivity");
                    return;
                case 2131298633:
                    UMEventUtil.onEventObject(this.mActivity, "2002008", "");
                    ARouterUtils.navigation("/point/PointCalendarActivity");
                    return;
                default:
                    return;
            }
        } else {
            gotoLogin("mine");
        }
    }

    public void goToQDScore() {
        AppModule.getInstance().queryUserCredit(new 3(this));
    }

    public void onDestroyView() {
        MyFragment.super.onDestroyView();
        EventBusUtils.unregister(this);
        this.unbinder.unbind();
        LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.broadcastReceiver);
    }

    public void onPainSign(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("merchantId", str3);
        bundle.putString("refUserId", this.userInfo.getUserId());
        bundle.putString(DispatchConstants.SIGN, str);
    }

    public RecyclerView getItemListView() {
        return this.recyclerView;
    }

    public void setUserVisibleHint(boolean z) {
        MyFragment.super.setUserVisibleHint(z);
        if (z) {
            this.mPresenter.getUnReadMsg();
        }
    }
}
