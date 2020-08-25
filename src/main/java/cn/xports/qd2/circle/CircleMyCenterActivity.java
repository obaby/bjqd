package cn.xports.qd2.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.sportCoaching.DateShowUtils;
import com.alipay.sdk.packet.d;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

public class CircleMyCenterActivity extends MyBaseActivity implements View.OnClickListener, CircleLikeView {
    private String circleId = "";
    private String circleName = "";
    /* access modifiers changed from: private */
    public CircleHomePostsAdapter circlePostsAdapter;
    /* access modifiers changed from: private */
    public DataMap cominData = new DataMap();
    /* access modifiers changed from: private */
    public int index;
    /* access modifiers changed from: private */
    public boolean isHasNextPage;
    /* access modifiers changed from: private */
    public boolean isRefresh;
    private boolean isSelf = true;
    private ImageView ivHead;
    private ImageView ivMsgRemind;
    /* access modifiers changed from: private */
    public CircleLikePresenter likePresenter;
    /* access modifiers changed from: private */
    public LinearLayout llPostsBottom;
    private String otherAccountId = "";
    /* access modifiers changed from: private */
    public List<DataMap> postsDataList = new ArrayList();
    private RelativeLayout rlEmpty;
    private View rlMsg;
    private RecyclerView rvMyCenter;
    /* access modifiers changed from: private */
    public SmartRefreshLayout smartRefreshLayout;
    private TextView tvCircleName;
    private TextView tvDynamicCount;
    private TextView tvJoinDay;
    private TextView tvLikeCount;
    private TextView tvMsgCount;
    private TextView tvName;

    static {
        StubApp.interface11(3770);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void optionFail(@NotNull String str) {
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_empty_des)).setText("这里空空如也");
        this.ivMsgRemind = (ImageView) findViewById(R.id.iv_red_remind_msg);
        this.ivHead = (ImageView) findViewById(R.id.iv_head);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvJoinDay = (TextView) findViewById(R.id.tv_join_day);
        this.tvCircleName = (TextView) findViewById(R.id.tv_circle_name);
        this.tvDynamicCount = (TextView) findViewById(R.id.tv_dynamic_count);
        this.tvLikeCount = (TextView) findViewById(R.id.tv_like_count);
        this.tvMsgCount = (TextView) findViewById(R.id.tv_msg_count);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_circle_dynamic);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.rl_like);
        this.rlMsg = findViewById(R.id.rl_msg);
        this.smartRefreshLayout = findViewById(R.id.srl_circle_my_center);
        this.rvMyCenter = (RecyclerView) findViewById(R.id.rv_circle_my_center);
        this.rlEmpty = (RelativeLayout) findViewById(R.id.rl_circle_empty);
        this.llPostsBottom = (LinearLayout) findViewById(R.id.ll_recycler_bottom);
        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(this);
        this.rlMsg.setOnClickListener(this);
        this.circlePostsAdapter = new CircleHomePostsAdapter(this.postsDataList, this);
        this.circlePostsAdapter.setInCircle(!this.isSelf).setInOtherCenter(!this.isSelf);
        int i = 0;
        this.rvMyCenter.setNestedScrollingEnabled(false);
        this.rvMyCenter.setLayoutManager(new LinearLayoutManager(this));
        this.rvMyCenter.setAdapter(this.circlePostsAdapter);
        this.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        this.smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        this.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                CircleMyCenterActivity.this.getCirclePosts(1);
                CircleMyCenterActivity.this.llPostsBottom.setVisibility(8);
            }
        });
        this.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CircleMyCenterActivity.this.isHasNextPage) {
                    CircleMyCenterActivity.this.getCirclePosts(CircleMyCenterActivity.this.index);
                    return;
                }
                CircleMyCenterActivity.this.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                CircleMyCenterActivity.this.llPostsBottom.setVisibility(0);
            }
        });
        this.circlePostsAdapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<DataMap>() {
            public void onItemClick(DataMap dataMap, int i, int i2) {
                DataMap unused = CircleMyCenterActivity.this.cominData = dataMap;
                if (i2 == 0) {
                    CircleMyCenterActivity.this.startActivity(new Intent(CircleMyCenterActivity.this, PostDetailActivity.class).putExtra("joinState", "2").putExtra("postId", dataMap.getString("postId")));
                } else if (dataMap.getBooleanValue("hasLike")) {
                    CircleMyCenterActivity.this.likePresenter.deleteLike(dataMap.getString("postId"));
                } else {
                    CircleMyCenterActivity.this.likePresenter.addLike(dataMap.getString("postId"));
                }
            }
        });
        RxBus.get().toFlowable(String.class).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                if ("DELETE_POST".equals(str)) {
                    CircleMyCenterActivity.this.postsDataList.remove(CircleMyCenterActivity.this.cominData);
                } else if ("DELETE_COMMENT".equals(str)) {
                    CircleMyCenterActivity.this.cominData.set("commentNum", Integer.valueOf(CircleMyCenterActivity.this.cominData.getIntValue("commentNum").intValue() - 1));
                } else if ("ADD_COMMENT".equals(str)) {
                    CircleMyCenterActivity.this.cominData.set("commentNum", Integer.valueOf(CircleMyCenterActivity.this.cominData.getIntValue("commentNum").intValue() + 1));
                } else if ("ADD_LIKE".equals(str)) {
                    CircleMyCenterActivity.this.cominData.set("likeNum", Integer.valueOf(CircleMyCenterActivity.this.cominData.getIntValue("likeNum").intValue() + 1)).set("likeList", "[{}]");
                } else if ("DELETE_LIKE".equals(str)) {
                    CircleMyCenterActivity.this.cominData.set("likeNum", Integer.valueOf(CircleMyCenterActivity.this.cominData.getIntValue("likeNum").intValue() - 1)).set("likeList", "[]");
                }
                CircleMyCenterActivity.this.circlePostsAdapter.notifyDataSetChanged();
            }
        });
        this.tvCircleName.setText(this.circleName);
        findViewById(R.id.rl_circle_name).setVisibility((this.isSelf || TextUtils.isEmpty(this.circleName)) ? 8 : 0);
        View view = this.rlMsg;
        if (!this.isSelf) {
            i = 8;
        }
        view.setVisibility(i);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.rl_msg) {
            startActivityForResult(new Intent(this, CircleInteractMsgActivity.class), 33);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private void initData() {
        if (this.isSelf) {
            getMyCenterInfo();
        } else {
            getOtherCenter();
        }
        getCirclePosts(1);
    }

    private void getMyCenterInfo() {
        ApiUtil.getApi2().getCircleMyCenterInfo().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleMyCenterActivity.this.refreshMyCenterUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    private void getOtherCenter() {
        ApiUtil.getApi2().getOtherCenter(this.circleId, this.otherAccountId).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this) {
            public void onSuccess(@NonNull DataMap dataMap) {
                CircleMyCenterActivity.this.refreshMyCenterUi(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshMyCenterUi(DataMap dataMap) {
        String str;
        String string = dataMap.getDataMap(d.k).getString("accountName");
        int i = 0;
        if (!this.isSelf && string.length() > 7) {
            string = string.substring(0, 5) + "...";
        }
        this.tvName.setText(string);
        this.tvLikeCount.setText(dataMap.getDataMap(d.k).getString("likeNum"));
        this.tvDynamicCount.setText(dataMap.getDataMap(d.k).getString("postNum"));
        this.tvMsgCount.setText(dataMap.getDataMap(d.k).getString("unreadMessage"));
        GlideUtil.loadRoundImage(this, dataMap.getDataMap(d.k).getString("accountAvatar"), R.drawable.ic_circle_default_head).into(this.ivHead);
        boolean z = true;
        if (1 != dataMap.getIntValue("quitTag").intValue()) {
            z = false;
        }
        if (z) {
            this.tvJoinDay.setVisibility(0);
            this.tvJoinDay.setText("已退出");
        } else if (dataMap.getDataMap(d.k).getString("joinTime").equals("")) {
            this.tvJoinDay.setVisibility(8);
        } else {
            TextView textView = this.tvJoinDay;
            if (this.isSelf) {
                i = 8;
            }
            textView.setVisibility(i);
            String postFriendlyTime = DateShowUtils.getPostFriendlyTime(dataMap.getDataMap(d.k).getString("joinTime"));
            if (postFriendlyTime.contains("刚") || postFriendlyTime.contains("前")) {
                str = "已加入圈子" + postFriendlyTime.replace("前", "");
            } else {
                str = postFriendlyTime + "加入圈子";
            }
            this.tvJoinDay.setText(str);
        }
        this.ivMsgRemind.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public final void getCirclePosts(int i) {
        Observable<ResponseBody> observable;
        if (i == 1) {
            this.isRefresh = true;
        } else {
            this.isRefresh = false;
        }
        if (this.isSelf) {
            observable = ApiUtil.getApi2().getMyPosts("1", Integer.valueOf(i), 20);
        } else {
            observable = ApiUtil.getApi2().getPostsByAccount(this.circleId, this.otherAccountId, 20, i);
        }
        observable.compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleMyCenterActivity.this.refreshPostsUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                if (CircleMyCenterActivity.this.isRefresh) {
                    boolean unused = CircleMyCenterActivity.this.isRefresh = false;
                    CircleMyCenterActivity.this.smartRefreshLayout.finishRefresh();
                    return;
                }
                CircleMyCenterActivity.this.smartRefreshLayout.finishLoadMore();
            }
        });
        this.index = i + 1;
    }

    /* access modifiers changed from: private */
    public void refreshPostsUi(DataMap dataMap) {
        this.isHasNextPage = dataMap.getDataMap(d.k).getBoolean("hasNextPage").booleanValue();
        ArrayList<DataMap> dataList = dataMap.getDataMap(d.k).getDataList("list");
        if (this.isRefresh) {
            this.postsDataList.clear();
        }
        this.postsDataList.addAll(dataList);
        this.circlePostsAdapter.notifyDataSetChanged();
        if (this.isRefresh) {
            this.smartRefreshLayout.finishRefresh();
        } else {
            this.smartRefreshLayout.finishLoadMore();
        }
        if (!this.isHasNextPage) {
            this.smartRefreshLayout.finishLoadMoreWithNoMoreData();
            this.llPostsBottom.setVisibility(0);
        }
        if (this.postsDataList.size() > 0) {
            this.rlEmpty.setVisibility(8);
            return;
        }
        this.rlEmpty.setVisibility(0);
        this.llPostsBottom.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 33) {
            initData();
            this.ivMsgRemind.setVisibility(8);
        }
    }

    public void optionSuccess(int i) {
        if (i == 1) {
            RxBus.get().post("ADD_LIKE");
        } else {
            RxBus.get().post("DELETE_LIKE");
        }
    }
}
