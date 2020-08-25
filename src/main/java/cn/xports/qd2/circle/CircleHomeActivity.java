package cn.xports.qd2.circle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.JoinedCircleAdapter;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.d;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CircleHomeActivity extends MyBaseActivity implements CircleLikeView {
    public static final String CIRCLE_CHANGE_ACTION = "circle_change_action";
    public static final int ISSUE_POSTS_REQUEST_CODE = 66;
    public static final int ISSUE_POSTS_RESULT_CODE = 100;
    /* access modifiers changed from: private */
    public boolean canCreateCircle = false;
    /* access modifiers changed from: private */
    public CircleHomePostsAdapter circlePostsAdapter;
    /* access modifiers changed from: private */
    public DataMap cominData = new DataMap();
    private View emptyJoinCircle;
    /* access modifiers changed from: private */
    public int index;
    /* access modifiers changed from: private */
    public boolean isHasChange;
    /* access modifiers changed from: private */
    public boolean isHasNextPage;
    /* access modifiers changed from: private */
    public boolean isJoinCircle;
    /* access modifiers changed from: private */
    public boolean isRefresh;
    private ImageView ivAddPosts;
    private JoinedCircleAdapter joinedCircleAdapter = new JoinedCircleAdapter(this.joinedCircles);
    private List<DataMap> joinedCircles = new ArrayList();
    /* access modifiers changed from: private */
    public CircleLikePresenter likePresenter;
    /* access modifiers changed from: private */
    public LinearLayout llPostsBottom;
    /* access modifiers changed from: private */
    public List<DataMap> postsDataList = new ArrayList();
    private CircleChangeReceiver receiver;
    private RoundImageView rivCircle1;
    private RoundImageView rivCircle2;
    private RoundImageView rivCircle3;
    private RelativeLayout rlMyJoinCircle;
    private RecyclerView rvEmptyRecommendCircle;
    private RecyclerView rvJoinedCircles;
    private RecyclerView rvPosts;
    /* access modifiers changed from: private */
    public SmartRefreshLayout smartRefreshLayout;
    private TextView tvCircle1;
    private TextView tvCircle2;
    private TextView tvCircle3;
    private TextView tvRemindMsgCount;

    static {
        StubApp.interface11(3727);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(@Nullable Bundle bundle);

    public void optionFail(@NotNull String str) {
    }

    private final void initView() {
        this.tvRemindMsgCount = (TextView) findViewById(R.id.tv_msg_remind_count);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomeActivity.this.finish();
            }
        });
        findViewById(R.id.iv_add_circle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomeActivity.this.startActivity(new Intent(CircleHomeActivity.this, CreateCircleActivity.class));
            }
        });
        findViewById(R.id.rl_my_circle_center).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomeActivity.this.startActivity(new Intent(CircleHomeActivity.this, CircleMyCenterActivity.class));
            }
        });
        ((TextView) findViewById(R.id.tv_more_circle)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomeActivity.this.startActivity(new Intent(CircleHomeActivity.this, MyCircleListActivity.class));
            }
        });
        this.rvJoinedCircles = (RecyclerView) findViewById(R.id.rv_joined_circles);
        this.rvJoinedCircles.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.rvJoinedCircles.setAdapter(this.joinedCircleAdapter);
        this.joinedCircleAdapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<DataMap>() {
            public void onItemClick(DataMap dataMap, int i, int i2) {
                CircleHomeActivity.this.startCircleDetailActivity(dataMap.getString("id"));
                if (dataMap.getIntValue("postNew").intValue() > 0) {
                    CircleHomeActivity.this.updateReadTime(dataMap.getString("id"));
                }
            }
        });
        this.rvPosts = (RecyclerView) findViewById(R.id.rv_circle_posts);
        this.rvPosts.getItemAnimator().setAddDuration(0);
        this.llPostsBottom = (LinearLayout) findViewById(R.id.ll_posts_bottom);
        this.smartRefreshLayout = findViewById(R.id.srl_circle);
        this.rlMyJoinCircle = (RelativeLayout) findViewById(R.id.rl_my_join_circle);
        this.emptyJoinCircle = findViewById(R.id.sv_empty);
        this.ivAddPosts = (ImageView) findViewById(R.id.iv_add_posts);
        this.ivAddPosts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomeActivity.this.issuePosts();
            }
        });
        findViewById(R.id.rl_discover_circle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomeActivity.this.startActivity(new Intent(CircleHomeActivity.this, DiscoverCircleActivity.class).putExtra("createCircle", CircleHomeActivity.this.canCreateCircle));
            }
        });
        this.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        this.smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        this.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (CircleHomeActivity.this.isJoinCircle) {
                    boolean unused = CircleHomeActivity.this.isRefresh = true;
                    CircleHomeActivity.this.getMyJoinCircle();
                    CircleHomeActivity.this.getRemindMsg();
                    CircleHomeActivity.this.llPostsBottom.setVisibility(8);
                }
            }
        });
        this.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CircleHomeActivity.this.isJoinCircle) {
                    if (CircleHomeActivity.this.isHasNextPage) {
                        CircleHomeActivity.this.getCirclePosts(CircleHomeActivity.this.index);
                        return;
                    }
                    CircleHomeActivity.this.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    if (CircleHomeActivity.this.postsDataList.size() > 0) {
                        CircleHomeActivity.this.llPostsBottom.setVisibility(0);
                    }
                }
            }
        });
        RxBus.get().toFlowable(String.class).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                if (!"EXIT_CIRCLE".equals(str) || CircleHomeActivity.this.smartRefreshLayout == null) {
                    if ("DELETE_POST".equals(str)) {
                        CircleHomeActivity.this.postsDataList.remove(CircleHomeActivity.this.cominData);
                    } else if ("DELETE_COMMENT".equals(str)) {
                        CircleHomeActivity.this.cominData.set("commentNum", Integer.valueOf(CircleHomeActivity.this.cominData.getIntValue("commentNum").intValue() - 1));
                    } else if ("ADD_COMMENT".equals(str)) {
                        CircleHomeActivity.this.cominData.set("commentNum", Integer.valueOf(CircleHomeActivity.this.cominData.getIntValue("commentNum").intValue() + 1));
                    } else if ("ADD_LIKE".equals(str)) {
                        CircleHomeActivity.this.cominData.set("likeNum", Integer.valueOf(CircleHomeActivity.this.cominData.getIntValue("likeNum").intValue() + 1)).set("likeList", "[{}]");
                    } else if ("DELETE_LIKE".equals(str)) {
                        CircleHomeActivity.this.cominData.set("likeNum", Integer.valueOf(CircleHomeActivity.this.cominData.getIntValue("likeNum").intValue() - 1)).set("likeList", "[]");
                    }
                    CircleHomeActivity.this.circlePostsAdapter.notifyDataSetChanged();
                    return;
                }
                CircleHomeActivity.this.smartRefreshLayout.autoRefresh();
            }
        });
    }

    /* access modifiers changed from: private */
    public void issuePosts() {
        startActivityForResult(new Intent(this, IssuePostsActivity.class), 66);
    }

    /* access modifiers changed from: private */
    public final void getMyJoinCircle() {
        ApiUtil.getApi2().getMyJoinCircleInfo(1, 999).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleHomeActivity.this.refreshUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public final void refreshUi(DataMap dataMap) {
        ArrayList<DataMap> dataList = dataMap.getDataMap(d.k).getDataList("list");
        if (dataList == null || dataList.size() == 0) {
            findViewById(R.id.rl_circle_empty).setVisibility(8);
            this.emptyJoinCircle.setVisibility(0);
            this.rlMyJoinCircle.setVisibility(8);
            this.ivAddPosts.setVisibility(8);
            this.isJoinCircle = false;
            showEmptyCircle();
        } else {
            this.emptyJoinCircle.setVisibility(8);
            this.rlMyJoinCircle.setVisibility(0);
            this.ivAddPosts.setVisibility(0);
            this.joinedCircles.clear();
            if (dataList.size() > 3) {
                this.joinedCircles.addAll(dataList.subList(0, 3));
            } else {
                this.joinedCircles.addAll(dataList);
            }
            this.joinedCircleAdapter.notifyDataSetChanged();
            this.isJoinCircle = true;
            getCirclePosts(1);
        }
        boolean equals = "1".equals(dataMap.getDataMap(d.k).getDataMap(K.extra).getString("createCircle"));
        this.canCreateCircle = equals;
        if (equals) {
            findViewById(R.id.iv_add_circle).setVisibility(0);
            findViewById(R.id.tv_create_circle).setVisibility(0);
            return;
        }
        findViewById(R.id.iv_add_circle).setVisibility(8);
        findViewById(R.id.tv_create_circle).setVisibility(8);
    }

    /* access modifiers changed from: private */
    public final void getCirclePosts(int i) {
        if (i == 1) {
            this.isRefresh = true;
        } else {
            this.isRefresh = false;
        }
        ApiUtil.getApi2().getMyJoinCirclePosts(Integer.valueOf(i), 15).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                if (!CircleHomeActivity.this.isFinishing() && !CircleHomeActivity.this.isFinishing()) {
                    CircleHomeActivity.this.refreshPostsUi(dataMap);
                }
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                if (!CircleHomeActivity.this.isFinishing()) {
                    if (CircleHomeActivity.this.isRefresh) {
                        boolean unused = CircleHomeActivity.this.isRefresh = false;
                        CircleHomeActivity.this.smartRefreshLayout.finishRefresh();
                        return;
                    }
                    CircleHomeActivity.this.smartRefreshLayout.finishLoadMore();
                }
            }
        });
        this.index = i + 1;
    }

    /* access modifiers changed from: private */
    public final void refreshPostsUi(DataMap dataMap) {
        this.isHasNextPage = dataMap.getDataMap(d.k).getBoolean("hasNextPage").booleanValue();
        ArrayList<DataMap> dataList = dataMap.getDataMap(d.k).getDataList("list");
        if (this.isRefresh) {
            this.postsDataList.clear();
        }
        this.postsDataList.addAll(dataList);
        this.circlePostsAdapter.notifyDataSetChanged();
        if (this.isRefresh) {
            this.isRefresh = false;
            this.smartRefreshLayout.finishRefresh();
        } else {
            this.smartRefreshLayout.finishLoadMore();
        }
        if (this.postsDataList != null && this.postsDataList.size() != 0) {
            findViewById(R.id.rl_circle_empty).setVisibility(8);
        } else if (this.joinedCircles.isEmpty() || !this.isJoinCircle) {
            findViewById(R.id.rl_circle_empty).setVisibility(8);
        } else {
            ((TextView) findViewById(R.id.tv_empty_des)).setText("暂无动态");
            findViewById(R.id.rl_circle_empty).setVisibility(0);
        }
    }

    public void initData() {
        initReceiver();
    }

    /* access modifiers changed from: private */
    public void startCircleDetailActivity(String str) {
        Intent intent = new Intent(this, CircleDetailActivity.class);
        intent.putExtra(CircleDetailActivity.CIRCLE_ID, str);
        startActivity(intent);
    }

    private void showEmptyCircle() {
        ((TextView) findViewById(R.id.tv_discover_circles)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomeActivity.this.startActivity(new Intent(CircleHomeActivity.this, DiscoverCircleActivity.class).putExtra("createCircle", CircleHomeActivity.this.canCreateCircle));
            }
        });
        ((TextView) findViewById(R.id.tv_create_circle)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomeActivity.this.startActivity(new Intent(CircleHomeActivity.this, CreateCircleActivity.class));
            }
        });
        this.rvEmptyRecommendCircle = (RecyclerView) findViewById(R.id.rv_recommend);
        ApiUtil.getApi2().getRecommendList("", "100", Integer.valueOf(this.index)).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleHomeActivity.this.refreshHotUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshHotUi(DataMap dataMap) {
        ArrayList<DataMap> dataList = dataMap.getDataMap(d.k).getDataList("list");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < dataList.size(); i++) {
            ArrayList<DataMap> dataList2 = dataList.get(i).getDataList("itemList");
            String string = dataList.get(i).getString("subtitle");
            if (dataList2 != null && dataList2.size() > 0) {
                for (int i2 = 0; i2 < dataList2.size(); i2++) {
                    DiscoverCircleBean discoverCircleBean = new DiscoverCircleBean();
                    discoverCircleBean.tag = string;
                    discoverCircleBean.imgUrl = dataList2.get(i2).getString("avatar");
                    discoverCircleBean.circleName = dataList2.get(i2).getString(c.e);
                    discoverCircleBean.postsNum = dataList2.get(i2).getInteger("postNum") + "";
                    discoverCircleBean.memberNum = dataList2.get(i2).getInteger("memberNum") + "";
                    discoverCircleBean.circleId = dataList2.get(i2).getInteger("id") + "";
                    arrayList.add(discoverCircleBean);
                }
            }
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        HotCircleAdapter hotCircleAdapter = new HotCircleAdapter(arrayList, this);
        this.rvEmptyRecommendCircle.setLayoutManager(gridLayoutManager);
        this.rvEmptyRecommendCircle.setAdapter(hotCircleAdapter);
    }

    public void optionSuccess(int i) {
        if (i == 1) {
            RxBus.get().post("ADD_LIKE");
        } else {
            RxBus.get().post("DELETE_LIKE");
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @android.support.annotation.Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 100) {
            this.isRefresh = true;
            getCirclePosts(1);
            this.llPostsBottom.setVisibility(8);
        }
    }

    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CIRCLE_CHANGE_ACTION);
        this.receiver = new CircleChangeReceiver();
        registerReceiver(this.receiver, intentFilter);
    }

    private class CircleChangeReceiver extends BroadcastReceiver {
        private CircleChangeReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            boolean unused = CircleHomeActivity.this.isHasChange = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.receiver != null) {
            unregisterReceiver(this.receiver);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.isHasChange) {
            getMyJoinCircle();
            this.isHasChange = false;
        }
        getRemindMsg();
    }

    /* access modifiers changed from: private */
    public void getRemindMsg() {
        ApiUtil.getApi2().getCircleMyCenterInfo().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, false) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleHomeActivity.this.refreshMyCenterUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshMyCenterUi(DataMap dataMap) {
        int intValue = dataMap.getDataMap(d.k).getIntValue("unreadMessage").intValue();
        if (intValue > 0) {
            this.tvRemindMsgCount.setVisibility(0);
            TextView textView = this.tvRemindMsgCount;
            textView.setText(intValue + "");
            return;
        }
        this.tvRemindMsgCount.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void updateReadTime(String str) {
        ApiUtil.getApi2().updateRead("2", str).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, false) {
            public void onError(@NonNull DataMap dataMap) {
            }

            public void onSuccess(@NonNull DataMap dataMap) {
                boolean unused = CircleHomeActivity.this.isHasChange = true;
            }
        });
    }
}
