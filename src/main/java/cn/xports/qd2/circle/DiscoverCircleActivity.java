package cn.xports.qd2.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import cn.xports.qd2.util.ApiUtil;
import com.alipay.sdk.packet.d;
import com.androidkun.xtablayout.XTabLayout;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class DiscoverCircleActivity extends MyBaseActivity implements View.OnClickListener {
    private Map<String, String> boardMap = new HashMap();
    private boolean createCircle = false;
    private List<Fragment> fragmentList = new ArrayList();
    private List<String> tabList = new ArrayList();
    private TextView tvRemindMsgCount;
    private ViewPager vpDiscover;
    private XTabLayout xtlDiscover;

    static {
        StubApp.interface11(3791);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void initView() {
        this.xtlDiscover = findViewById(R.id.xtl_discover_circle);
        this.vpDiscover = (ViewPager) findViewById(R.id.vp_discover_circle);
        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_titles)).setText("发现圈子");
        this.tvRemindMsgCount = (TextView) findViewById(R.id.tv_msg_remind_count);
        ((ImageView) findViewById(R.id.iv_add_circle)).setOnClickListener(this);
        ((RelativeLayout) findViewById(R.id.rl_my_circle_center)).setOnClickListener(this);
    }

    private void initData() {
        ApiUtil.getApi2().getAllBoards().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                DiscoverCircleActivity.this.refreshUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshUi(DataMap dataMap) {
        ArrayList<DataMap> dataList = dataMap.getDataList("boards");
        if (dataList != null && dataList.size() != 0) {
            this.boardMap.clear();
            for (int i = 0; i < dataList.size(); i++) {
                this.boardMap.put(dataList.get(i).getString("boardId"), dataList.get(i).getString("boardName"));
            }
            this.fragmentList.clear();
            this.tabList.clear();
            this.fragmentList.add(new DiscoverCircleFragment());
            this.tabList.add("推荐");
            for (int i2 = 0; i2 < dataMap.getDataList("boards").size(); i2++) {
                CircleBoardFragment circleBoardFragment = new CircleBoardFragment();
                Bundle bundle = new Bundle();
                bundle.putString("boardId", dataMap.getDataList("boards").get(i2).getString("boardId"));
                circleBoardFragment.setArguments(bundle);
                this.fragmentList.add(circleBoardFragment);
                this.tabList.add(dataMap.getDataList("boards").get(i2).getString("boardName"));
            }
            this.vpDiscover.setAdapter(new DiscoverCirclePagerAdapter(getSupportFragmentManager(), this.tabList, this.fragmentList));
            this.xtlDiscover.setupWithViewPager(this.vpDiscover);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
        } else if (view.getId() == R.id.iv_add_circle) {
            startActivity(new Intent(this, CreateCircleActivity.class));
        } else if (view.getId() == R.id.rl_my_circle_center) {
            startActivity(new Intent(this, CircleMyCenterActivity.class));
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getRemindMsg();
    }

    private void getRemindMsg() {
        ApiUtil.getApi2().getCircleMyCenterInfo().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, false) {
            public void onSuccess(@NotNull DataMap dataMap) {
                DiscoverCircleActivity.this.refreshMyCenterUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshMyCenterUi(DataMap dataMap) {
        int intValue = dataMap.getDataMap(d.k).getInteger("unreadMessage").intValue();
        if (intValue > 0) {
            this.tvRemindMsgCount.setVisibility(0);
            TextView textView = this.tvRemindMsgCount;
            textView.setText(intValue + "");
        } else {
            this.tvRemindMsgCount.setVisibility(8);
        }
        if (this.createCircle) {
            findViewById(R.id.iv_add_circle).setVisibility(0);
        } else {
            findViewById(R.id.iv_add_circle).setVisibility(8);
        }
    }

    public Map<String, String> getBoardMap() {
        return this.boardMap;
    }
}
