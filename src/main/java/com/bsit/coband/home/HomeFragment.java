package com.bsit.coband.home;

import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import anylife.scrolltextview.ScrollTextView;
import butterknife.BindView;
import butterknife.OnClick;
import coband.bsit.com.integral.R;
import com.blankj.utilcode.util.ToastUtils;
import com.bsit.coband.MainActivity;
import com.bsit.coband.home.adapter.IconListAdapter;
import com.bsit.coband.home.adapter.ModuleListAdapter;
import com.bsit.coband.net.bean.HomeResponse;
import com.bsit.coband.net.bean.Module;
import com.convenient.qd.core.base.arouter.ARouterUtils;
import com.convenient.qd.core.base.eventbus.Event;
import com.convenient.qd.core.base.eventbus.EventBusUtils;
import com.convenient.qd.core.base.mvp.BaseFragment;
import com.convenient.qd.core.bean.MenuTab;
import com.convenient.qd.core.constant.Constant;
import com.convenient.qd.core.utils.SaveUtils;
import com.convenient.qd.core.utils.StringUtil;
import com.convenient.qd.core.utils.UMEventUtil;
import com.convenient.qd.core.widget.CustomNestedScrollView;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

public class HomeFragment extends BaseFragment<IHomeAtView, HomeAtPresenter> implements IHomeAtView {
    /* access modifiers changed from: private */
    public int imageHeight;
    @BindView(2131297801)
    ImageView iv_sao_black;
    @BindView(2131297802)
    ImageView iv_sao_white;
    @BindView(2131296464)
    Banner mBanner;
    @BindView(2131296706)
    RelativeLayout mCardIconList;
    /* access modifiers changed from: private */
    public List<MenuTab> mIconList = new ArrayList();
    private IconListAdapter mIconListAdapter;
    @BindView(2131296952)
    RelativeLayout mLlemptyView;
    private ModuleListAdapter mMoreListAdapter;
    @BindView(2131299092)
    SwipeRefreshLayout mRefreshlayout;
    @BindView(2131299300)
    RecyclerView mRvIconList;
    @BindView(2131299311)
    RecyclerView mRvMoresv;
    @BindView(2131299368)
    CustomNestedScrollView mScrollView;
    @BindView(2131299712)
    TextView mToolbar_title;
    @BindView(2131300779)
    View mView_line;
    private List<Module> moduleList = new ArrayList();
    @BindView(2131299205)
    RelativeLayout rlHomeNotice;
    @BindView(2131300171)
    ScrollTextView tvHomeBanner;

    /* access modifiers changed from: protected */
    public int initLayout() {
        return 2131427932;
    }

    public void onSingleAd(Module module) {
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.support.v4.app.Fragment, com.bsit.coband.home.HomeFragment] */
    public static Fragment newInstance() {
        return new HomeFragment();
    }

    /* access modifiers changed from: protected */
    public void initData() {
        HomeFragment.super.initData();
        this.mPresenter.toGetHomeCache();
        if (Constant.FIRST_SPLASH) {
            this.mPresenter.toGetHomeData(true);
        }
        this.mPresenter.getNotice();
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        if (Build.VERSION.SDK_INT > 21) {
            ImmersionBar.setTitleBar(getActivity(), new View[]{this.mToolbar_title});
        }
        EventBusUtils.register(this);
        this.mRefreshlayout.setColorSchemeColors(((FragmentActivity) Objects.requireNonNull(getActivity())).getResources().getColor(R.color.refresh));
        this.mRefreshlayout.setOnRefreshListener(new 1(this));
        this.mIconListAdapter = new IconListAdapter(this.mIconList, new 2(this));
        this.mRvIconList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.mRvIconList.setNestedScrollingEnabled(false);
        this.mRvIconList.setAdapter(this.mIconListAdapter);
        this.mMoreListAdapter = new ModuleListAdapter(this.moduleList, new 3(this));
        this.mRvMoresv.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mRvMoresv.setNestedScrollingEnabled(false);
        this.mRvMoresv.setAdapter(this.mMoreListAdapter);
        this.mBanner.getViewTreeObserver().addOnGlobalLayoutListener(new 4(this));
    }

    /* access modifiers changed from: protected */
    public HomeAtPresenter createPresenter() {
        return new HomeAtPresenter((MainActivity) getActivity(), (MainActivity) getActivity());
    }

    public void showProgressDialog() {
        if (this.mIconList.size() == 0 && this.moduleList.size() == 0) {
            LoadingDiaLogUtils.showLoadingDialog(this.mActivity);
        }
    }

    public void hideProgressDialog() {
        if (this.mRefreshlayout != null) {
            this.mRefreshlayout.setRefreshing(false);
        }
        LoadingDiaLogUtils.dismisDialog();
    }

    public void showError(String str) {
        if (this.mRefreshlayout != null) {
            this.mRefreshlayout.setRefreshing(false);
        }
        if (this.mIconList.size() == 0 && this.moduleList.size() == 0) {
            this.mLlemptyView.setVisibility(0);
            this.mToolbar_title.setVisibility(0);
            this.mView_line.setVisibility(0);
        } else if (!StringUtil.isEmptyStr(str)) {
            ToastUtils.showShort(str);
        }
    }

    public void setTextBannerData(String str) {
        if (this.tvHomeBanner != null && !TextUtils.isEmpty(str)) {
            this.rlHomeNotice.setVisibility(0);
            this.tvHomeBanner.setText(str);
        }
    }

    public void onBannerData(List<MenuTab> list) {
        if (list != null && list.size() > 0) {
            this.mBanner.releaseBanner();
            this.mBanner.setVisibility(0);
            this.mBanner.setBannerStyle(1);
            this.mBanner.setIndicatorGravity(6);
            this.mBanner.setImageLoader(new GlideImageLoader(this));
            this.mBanner.setImages(list);
            this.mBanner.setBannerAnimation(Transformer.DepthPage);
            this.mBanner.isAutoPlay(true);
            this.mBanner.setDelayTime(5000);
            this.mBanner.setOnBannerListener(new 5(this, list));
            this.mBanner.start();
        }
    }

    public void onShowHomeData(HomeResponse homeResponse) {
        if (homeResponse != null) {
            onBannerData(homeResponse.getBannerList());
            onIconList(homeResponse.getIconList());
            onMoreSvList(homeResponse.getModuleList());
        } else if (this.mIconList.size() == 0 && this.moduleList.size() == 0) {
            this.mLlemptyView.setVisibility(0);
            this.mToolbar_title.setVisibility(0);
            this.mView_line.setVisibility(0);
        }
    }

    public void onIconList(List<MenuTab> list) {
        list.removeAll(Collections.singleton((Object) null));
        if (StringUtil.noEmptyList(list)) {
            this.mCardIconList.setVisibility(0);
            if (this.mIconList.size() > 0) {
                this.mIconList.clear();
            }
            this.mIconList.addAll(list);
            this.mIconListAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void onMyRefreshEvent(@NotNull Event event) {
        int code = event.getCode();
        if (code != 2001) {
            switch (code) {
                case PointerIconCompat.TYPE_CONTEXT_MENU:
                case PointerIconCompat.TYPE_HAND:
                    this.mPresenter.toGetHomeData(false);
                    return;
                default:
                    return;
            }
        } else {
            String string = SaveUtils.getString("iconList");
            if (!string.equals("")) {
                try {
                    onIconList((List) new Gson().fromJson(string, new 6(this).getType()));
                } catch (Exception unused) {
                }
            }
        }
    }

    public void onMoreSvList(List<Module> list) {
        if (StringUtil.noEmptyList(list)) {
            list.removeAll(Collections.singleton((Object) null));
            this.mRvMoresv.setVisibility(0);
            if (this.moduleList.size() > 0) {
                this.moduleList.clear();
            }
            this.moduleList.addAll(list);
            this.mMoreListAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({2131296952, 2131297473, 2131297801, 2131297802})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.data_empty_view /*2131296952*/:
                this.mLlemptyView.setVisibility(8);
                this.mToolbar_title.setVisibility(8);
                this.mView_line.setVisibility(8);
                this.mPresenter.toGetHomeData(true);
                return;
            case 2131297473:
                this.rlHomeNotice.setVisibility(8);
                this.tvHomeBanner.destroy();
                return;
            case 2131297801:
            case 2131297802:
                UMEventUtil.onEventObject(this.mActivity, "2008013", "");
                jumpQrScan();
                return;
            default:
                return;
        }
    }

    private void jumpQrScan() {
        if (ActivityCompat.checkSelfPermission(this.mActivity, "android.permission.CAMERA") == 0) {
            ARouterUtils.navigation("/app/ScanQrCodeActivity");
        } else {
            new RxPermissions(getActivity()).request(new String[]{"android.permission.CAMERA"}).subscribe(new 7(this));
        }
    }

    public void onResume() {
        HomeFragment.super.onResume();
    }

    public void onDestroy() {
        EventBusUtils.unregister(this);
        HomeFragment.super.onDestroy();
    }
}
