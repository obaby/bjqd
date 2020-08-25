package com.bsit.coband.home.menumg;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import coband.bsit.com.integral.R;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.bsit.coband.home.menumg.adapter.RecentMenuAdapter;
import com.bsit.coband.home.menumg.edit.EditMenuActivity;
import com.bsit.coband.net.bean.SubModule;
import com.bsit.coband.utils.SpaceItemDecoration;
import com.convenient.qd.core.base.eventbus.Event;
import com.convenient.qd.core.base.eventbus.EventBusUtils;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.bean.MenuTab;
import com.convenient.qd.core.user.UserDBHelper;
import com.convenient.qd.core.utils.SaveUtils;
import com.convenient.qd.core.utils.StringUtil;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;

@Route(path = "/menu/MenuActivity")
public class MenuActivity extends BaseActivity<IMenuAtView, MenuAtPresenter> implements IMenuAtView {
    private RecentMenuAdapter mAllMenuAdapter;
    private List<SubModule> mAllMenuTabList;
    /* access modifiers changed from: private */
    public List<MenuTab> mAllmenuList = new ArrayList();
    @BindView(2131296419)
    AppBarLayout mAppBarlayout;
    @BindView(2131296889)
    CoordinatorLayout mCoorlayout;
    /* access modifiers changed from: private */
    public GridLayoutManager mGridManager;
    /* access modifiers changed from: private */
    public boolean mIsScrolled = false;
    @BindView(2131298249)
    RelativeLayout mLlEmptyView;
    private RecentMenuAdapter mRecentMenuAdapter;
    /* access modifiers changed from: private */
    public List<MenuTab> mRecentmenuList = new ArrayList();
    /* access modifiers changed from: private */
    public List<Integer> mRecerPosition;
    @BindView(2131299075)
    RecyclerView mRecyclerAllmenu;
    @BindView(2131299077)
    RecyclerView mRecyclerRecentmenu;
    @BindView(2131299594)
    TabLayout mTablayout;
    @BindView(2131299712)
    TextView mToolbarTitle;
    @BindView(2131300135)
    TextView mTvFinish;

    /* access modifiers changed from: protected */
    public int initLayout() {
        return 2131428252;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.bsit.coband.home.menumg.MenuActivity, java.lang.Object] */
    /* access modifiers changed from: protected */
    public void initView() {
        this.mToolbarTitle.setText("全部应用");
        this.mTvFinish.setText("编辑");
        this.mTvFinish.setVisibility(0);
        EventBusUtils.register(this);
        this.mRecentMenuAdapter = new RecentMenuAdapter(this.mRecentmenuList, new 1(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new 2(this));
        this.mRecyclerRecentmenu.setLayoutManager(gridLayoutManager);
        this.mRecyclerRecentmenu.setNestedScrollingEnabled(false);
        this.mRecyclerRecentmenu.addItemDecoration(new SpaceItemDecoration(4, 6));
        this.mRecyclerRecentmenu.setAdapter(this.mRecentMenuAdapter);
        this.mAllMenuAdapter = new RecentMenuAdapter(this.mAllmenuList, new 3(this));
        this.mGridManager = new GridLayoutManager(this, 4);
        this.mGridManager.setSpanSizeLookup(new 4(this));
        this.mRecyclerAllmenu.setLayoutManager(this.mGridManager);
        this.mRecyclerAllmenu.setNestedScrollingEnabled(true);
        this.mRecyclerAllmenu.addItemDecoration(new SpaceItemDecoration(4, 6));
        this.mRecyclerAllmenu.setAdapter(this.mAllMenuAdapter);
        this.mRecyclerAllmenu.addOnScrollListener(new 5(this));
    }

    /* access modifiers changed from: protected */
    public void initData() {
        MenuActivity.super.initData();
        String string = SaveUtils.getString("iconList");
        if (!string.equals("")) {
            try {
                List list = (List) new Gson().fromJson(string, new 6(this).getType());
                if (StringUtil.noEmptyList(list)) {
                    list.remove(list.size() - 1);
                    if (this.mRecentmenuList.size() > 0) {
                        this.mRecentmenuList.clear();
                    }
                    int i = 0;
                    while (true) {
                        if (i >= list.size()) {
                            break;
                        } else if (((MenuTab) list.get(i)).getUneditable() == 1) {
                            list.remove(i);
                            break;
                        } else {
                            i++;
                        }
                    }
                    this.mRecentmenuList.addAll(list);
                    this.mRecentMenuAdapter.notifyDataSetChanged();
                }
            } catch (Exception unused) {
            }
        }
        this.mPresenter.doGetMenuMoreData();
    }

    @Subscribe
    public void onMyRefreshEvent(Event event) {
        if (event.getCode() == 2001) {
            String string = SaveUtils.getString("iconList");
            if (!string.equals("")) {
                try {
                    List list = (List) new Gson().fromJson(string, new 7(this).getType());
                    if (StringUtil.noEmptyList(list)) {
                        list.remove(list.size() - 1);
                        if (this.mRecentmenuList.size() > 0) {
                            this.mRecentmenuList.clear();
                        }
                        int i = 0;
                        while (true) {
                            if (i >= list.size()) {
                                break;
                            } else if (((MenuTab) list.get(i)).getUneditable() == 1) {
                                list.remove(i);
                                break;
                            } else {
                                i++;
                            }
                        }
                        this.mRecentmenuList.addAll(list);
                        this.mRecentMenuAdapter.notifyDataSetChanged();
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initImmersionBar() {
        MenuActivity.super.initImmersionBar();
        setStatusBar(R.id.base_toorbar);
    }

    /* access modifiers changed from: protected */
    public MenuAtPresenter createPresenter() {
        return new MenuAtPresenter(this, this);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.bsit.coband.home.menumg.MenuActivity] */
    public void showProgressDialog() {
        LoadingDiaLogUtils.showLoadingDialog(this);
    }

    public void hideProgressDialog() {
        LoadingDiaLogUtils.dismisDialog();
    }

    public void showError(String str) {
        if (!StringUtil.noEmptyList(this.mAllMenuTabList)) {
            this.mCoorlayout.setVisibility(8);
            this.mLlEmptyView.setVisibility(0);
            if (!StringUtil.isEmptyStr(str)) {
                ToastUtils.showShort(str);
            }
        } else if (!StringUtil.isEmptyStr(str)) {
            ToastUtils.showShort(str);
        }
    }

    @OnClick({2131299709, 2131300135, 2131298249})
    public void onViewClicked(View view) {
        if (view.getId() == 2131299709) {
            finish();
        } else if (view.getId() == 2131300135) {
            if (!StringUtil.noEmptyList(this.mAllmenuList)) {
                Toast.makeText(this.activity, "请刷新数据", 0).show();
            } else if (UserDBHelper.getInstance().getUserInfo() == null) {
                gotoLogin("menu");
            } else {
                Intent intent = new Intent(this.activity, EditMenuActivity.class);
                intent.putExtra("SubModuleList", (Serializable) this.mAllMenuTabList);
                startActivityForResult(intent, 1);
                overridePendingTransition(0, 0);
            }
        } else if (view.getId() == 2131298249) {
            this.mCoorlayout.setVisibility(8);
            this.mLlEmptyView.setVisibility(8);
            this.mPresenter.doGetMenuMoreData();
        }
    }

    public void finish() {
        MenuActivity.super.finish();
        overridePendingTransition(0, 0);
    }

    public void showAllMenuData(List<SubModule> list) {
        int i;
        if (StringUtil.noEmptyList(list)) {
            this.mCoorlayout.setVisibility(0);
            this.mLlEmptyView.setVisibility(8);
            this.mAllMenuTabList = list;
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                i = 1;
                if (i2 >= list.size()) {
                    break;
                }
                SubModule subModule = list.get(i2);
                if (StringUtil.noEmptyList(subModule.getLinkList())) {
                    linkedList2.add(Integer.valueOf(i3));
                    linkedList.add(subModule.getSubModuleName());
                    if (i2 != 0) {
                        MenuTab menuTab = new MenuTab();
                        menuTab.setAppName(subModule.getSubModuleName());
                        menuTab.setPlatType(1);
                        arrayList.add(menuTab);
                        i3++;
                    }
                    i3 += subModule.getLinkList().size();
                    arrayList.addAll(subModule.getLinkList());
                }
                i2++;
            }
            MenuTab menuTab2 = new MenuTab();
            menuTab2.setPlatType(2);
            arrayList.add(menuTab2);
            if (this.mAllmenuList.size() > 0) {
                this.mAllmenuList.clear();
            }
            this.mAllmenuList.addAll(arrayList);
            this.mAllMenuAdapter.notifyDataSetChanged();
            this.mRecerPosition = linkedList2;
            if (linkedList.size() > 0) {
                TabLayout tabLayout = this.mTablayout;
                if (linkedList.size() >= 5) {
                    i = 0;
                }
                tabLayout.setTabMode(i);
                for (int i4 = 0; i4 < linkedList.size(); i4++) {
                    this.mTablayout.addTab(this.mTablayout.newTab().setText((CharSequence) linkedList.get(i4)));
                }
                this.mTablayout.addOnTabSelectedListener(new 8(this));
                return;
            }
            return;
        }
        this.mCoorlayout.setVisibility(8);
        this.mLlEmptyView.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        EventBusUtils.unregister(this);
        MenuActivity.super.onDestroy();
    }
}
