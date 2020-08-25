package cn.xports.qd2.circle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import com.androidkun.xtablayout.XTabLayout;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class CircleInteractMsgActivity extends BaseBussActivity<IPresenter> {
    private List<Fragment> fragmentList = new ArrayList();
    private DiscoverCirclePagerAdapter pagerAdapter;
    /* access modifiers changed from: private */
    public List<String> tabList = new ArrayList();
    private ViewPager vpDiscover;
    private XTabLayout xtlDiscover;

    /* access modifiers changed from: protected */
    public String getChildTitle() {
        return "消息";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_circle_interact_msg;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.xtlDiscover = findViewById(R.id.xtl_discover_circle);
        this.vpDiscover = (ViewPager) findViewById(R.id.vp_discover_circle);
        this.tabList.add("互动消息");
        this.tabList.add("申请入圈");
        this.fragmentList.add(new CircleMsgInteractFragment());
        this.fragmentList.add(new CircleApplyAuditFragment());
        this.pagerAdapter = new DiscoverCirclePagerAdapter(getSupportFragmentManager(), this.tabList, this.fragmentList);
        this.vpDiscover.setAdapter(this.pagerAdapter);
        this.xtlDiscover.setupWithViewPager(this.vpDiscover);
        RxDisposableManager.getInstance().add(getTAG(), RxBus.get().toFlowable(String.class).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                if ("AUDIT_ALL".equals(str)) {
                    CircleInteractMsgActivity.this.tabList.set(1, "申请入圈");
                } else if (str != null && str.startsWith("AUDIT_")) {
                    String replace = str.replace("AUDIT_", "");
                    if (replace.equals(K.k0)) {
                        CircleInteractMsgActivity.this.tabList.set(1, "申请入圈");
                        return;
                    }
                    List access$000 = CircleInteractMsgActivity.this.tabList;
                    access$000.set(1, "申请入圈(" + replace + ")");
                }
            }
        }));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        setResult(100);
        super.onDestroy();
    }

    public void setCount(int i, int i2) {
        String str = "(" + i2 + ")";
        if (i2 <= 0) {
            str = "";
        }
        if (i == 0) {
            this.tabList.set(i, "互动消息" + str);
        } else if (i == 1) {
            this.tabList.set(i, "申请入圈" + str);
        }
        this.pagerAdapter.notifyDataSetChanged();
    }
}
