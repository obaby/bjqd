package cn.xports.qd2.circle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.base.GlobalDialog;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import cn.xports.qd2.circle.videoPlayer.PicUrlUtils;
import cn.xports.qd2.dialog.ApplyJoinCircleDialog;
import cn.xports.qd2.dialog.MyToastUtils;
import cn.xports.qd2.dialog.NoticeDialog;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.d;
import com.bumptech.glide.Glide;
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

public class CircleDetailActivity extends MyBaseActivity implements View.OnClickListener, CircleLikeView {
    public static final String CIRCLE_ID = "circle_id";
    private String circleId;
    private String circleName;
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
    private ImageView ivIssuePosts;
    private ImageView ivSettings;
    /* access modifiers changed from: private */
    public String joinState;
    /* access modifiers changed from: private */
    public CircleLikePresenter likePresenter;
    /* access modifiers changed from: private */
    public LinearLayout llPostsBottom;
    private boolean needCheck = false;
    private NestedScrollView nestedScrollView;
    /* access modifiers changed from: private */
    public List<DataMap> postsDataList = new ArrayList();
    private ImageView rivHead;
    private RelativeLayout rlEmpty;
    private String role = "";
    private RecyclerView rvPosts;
    /* access modifiers changed from: private */
    public SmartRefreshLayout smartRefreshLayout;
    private TextView tvCircleMemberCount;
    private TextView tvCircleName;
    private TextView tvCircleNotice;
    private TextView tvCirclePostsCount;
    /* access modifiers changed from: private */
    public TextView tvJoinState;

    static {
        StubApp.interface11(3707);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(@Nullable Bundle bundle);

    public void optionFail(@NotNull String str) {
    }

    private void initView() {
        this.rlEmpty = (RelativeLayout) findViewById(R.id.rl_circle_empty);
        ((TextView) findViewById(R.id.tv_empty_des)).setText("暂无动态");
        this.ivIssuePosts = (ImageView) findViewById(R.id.iv_add_posts);
        this.ivSettings = (ImageView) findViewById(R.id.iv_settings);
        this.rivHead = (ImageView) findViewById(R.id.riv_head);
        this.tvCircleName = (TextView) findViewById(R.id.tv_circle_name);
        this.tvJoinState = (TextView) findViewById(R.id.tv_join_state);
        this.tvCircleNotice = (TextView) findViewById(R.id.tv_circle_notice);
        this.tvCirclePostsCount = (TextView) findViewById(R.id.tv_circle_posts_count);
        this.tvCircleMemberCount = (TextView) findViewById(R.id.tv_circle_member_count);
        this.rvPosts = (RecyclerView) findViewById(R.id.rv_circle_detail_posts);
        this.llPostsBottom = (LinearLayout) findViewById(R.id.ll_recycler_bottom);
        this.smartRefreshLayout = findViewById(R.id.srl_circle_posts);
        this.tvJoinState.setOnClickListener(this);
        this.ivSettings.setOnClickListener(this);
        this.ivIssuePosts.setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.ll_circle_notice).setOnClickListener(this);
        this.nestedScrollView = (NestedScrollView) findViewById(R.id.nsv_circle_detail);
        this.circlePostsAdapter = new CircleHomePostsAdapter(this.postsDataList, this);
        this.circlePostsAdapter.setInCircle(true);
        this.circlePostsAdapter.setHasStableIds(true);
        this.rvPosts.setLayoutManager(new LinearLayoutManager(this));
        this.rvPosts.setNestedScrollingEnabled(false);
        this.rvPosts.setAdapter(this.circlePostsAdapter);
        this.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        this.smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        this.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                CircleDetailActivity.this.getPosts(1);
                CircleDetailActivity.this.llPostsBottom.setVisibility(8);
            }
        });
        this.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CircleDetailActivity.this.isHasNextPage) {
                    CircleDetailActivity.this.getPosts(CircleDetailActivity.this.index);
                    return;
                }
                CircleDetailActivity.this.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                CircleDetailActivity.this.llPostsBottom.setVisibility(0);
            }
        });
        final int color = getResources().getColor(R.color.blue_2e6);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_toolbar);
        ((AppBarLayout) findViewById(R.id.abl_bar)).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int abs = (int) (((float) Math.abs(i)) * (255.0f / ((float) appBarLayout.getTotalScrollRange())));
                relativeLayout.setBackgroundColor(Color.argb(abs, Color.red(color), Color.green(color), Color.blue(color)));
                if (abs <= 0 || abs >= 250) {
                    CircleDetailActivity.this.smartRefreshLayout.setEnabled(true);
                } else {
                    CircleDetailActivity.this.smartRefreshLayout.setEnabled(false);
                }
            }
        });
        this.circlePostsAdapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<DataMap>() {
            public void onItemClick(DataMap dataMap, int i, int i2) {
                DataMap unused = CircleDetailActivity.this.cominData = dataMap;
                if (i2 == 0) {
                    CircleDetailActivity.this.startActivity(new Intent(CircleDetailActivity.this, PostDetailActivity.class).putExtra("joinState", CircleDetailActivity.this.joinState).putExtra("fromCircle", true).putExtra("postId", dataMap.getString("postId")));
                } else if (dataMap.getBooleanValue("hasLike")) {
                    CircleDetailActivity.this.likePresenter.deleteLike(dataMap.getString("postId"));
                } else {
                    CircleDetailActivity.this.likePresenter.addLike(dataMap.getString("postId"));
                }
            }
        });
        RxBus.get().toFlowable(String.class).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                if ("DELETE_POST".equals(str)) {
                    CircleDetailActivity.this.postsDataList.remove(CircleDetailActivity.this.cominData);
                } else if ("DELETE_COMMENT".equals(str)) {
                    CircleDetailActivity.this.cominData.set("commentNum", Integer.valueOf(CircleDetailActivity.this.cominData.getIntValue("commentNum").intValue() - 1));
                } else if ("ADD_COMMENT".equals(str)) {
                    CircleDetailActivity.this.cominData.set("commentNum", Integer.valueOf(CircleDetailActivity.this.cominData.getIntValue("commentNum").intValue() + 1));
                } else if ("ADD_LIKE".equals(str)) {
                    CircleDetailActivity.this.cominData.set("likeNum", Integer.valueOf(CircleDetailActivity.this.cominData.getIntValue("likeNum").intValue() + 1)).set("likeList", "[{}]");
                } else if ("DELETE_LIKE".equals(str)) {
                    CircleDetailActivity.this.cominData.set("likeNum", Integer.valueOf(CircleDetailActivity.this.cominData.getIntValue("likeNum").intValue() - 1)).set("likeList", "[]");
                }
                CircleDetailActivity.this.circlePostsAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.ll_circle_member).setOnClickListener(this);
    }

    private void initData() {
        this.circleId = getIntent().getStringExtra(CIRCLE_ID);
        getCircleDetailInfo();
        getPosts(1);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
        } else if (view.getId() == R.id.ll_circle_notice) {
            showNoticeDialog();
        } else if (view.getId() == R.id.tv_join_state) {
            if (this.needCheck) {
                showJoinApplyDialog();
            } else {
                applyJoinCircle("");
            }
        } else if (view.getId() == R.id.iv_settings) {
            Intent intent = new Intent(this, CircleSettingsActivity.class);
            intent.putExtra(CIRCLE_ID, this.circleId);
            intent.putExtra("role", this.role);
            startActivityForResult(intent, 55);
        } else if (view.getId() == R.id.ll_circle_member) {
            if (TextUtils.isEmpty(this.joinState) || !this.joinState.equals("2")) {
                new GlobalDialog(this, "您还没有加入圈子，加入圈子后才可以查看圈子成员，去申请加入吧").setCancelVisible(false);
            } else {
                startActivity(new Intent(this, CircleMemberActivity.class).putExtra("circleId", this.circleId));
            }
        } else if (view.getId() == R.id.iv_add_posts) {
            Intent intent2 = new Intent(this, IssuePostsActivity.class);
            intent2.putExtra("circleId", this.circleId);
            intent2.putExtra("circleName", this.circleName);
            startActivityForResult(intent2, 66);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.e(">", "requestCode " + i + " resultCode " + i2);
        if (i == 55 && i2 == 100) {
            getCircleDetailInfo();
        } else if (!(i == 55 && i2 == 99) && i == 66 && i2 == 100) {
            this.isRefresh = true;
            getPosts(1);
            this.llPostsBottom.setVisibility(8);
            sendBroadcast(new Intent(CircleHomeActivity.CIRCLE_CHANGE_ACTION));
        }
    }

    /* access modifiers changed from: private */
    public void getCircleDetailInfo() {
        ApiUtil.getApi2().getCircleDetail(this.circleId).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleDetailActivity.this.refreshTitleInfo(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                new GlobalDialog(CircleDetailActivity.this, dataMap.getMessage()).setCancelVisible(false).setButtonClick(new GlobalDialog.OnButtonClick() {
                    public void onClick(int i) {
                        CircleDetailActivity.this.sendBroadcast(new Intent(CircleHomeActivity.CIRCLE_CHANGE_ACTION));
                        CircleDetailActivity.this.finish();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshTitleInfo(DataMap dataMap) {
        if (isFinishing()) {
            Log.e(">>", "isFinishing " + isFinishing());
        } else if (dataMap != null) {
            if (dataMap.getInteger(K.error).intValue() != 0) {
                new GlobalDialog(this, dataMap.getMessage()).setButtonClick(new GlobalDialog.OnButtonClick() {
                    public void onClick(int i) {
                        CircleDetailActivity.this.finish();
                    }
                });
                sendBroadcast(new Intent(CircleHomeActivity.CIRCLE_CHANGE_ACTION));
                return;
            }
            Glide.with((FragmentActivity) this).load(PicUrlUtils.getPath(dataMap.getDataMap(d.k).getString("avatar"))).error(R.drawable.bg_default).centerCrop().into(this.rivHead);
            this.circleName = dataMap.getDataMap(d.k).getString(c.e);
            this.tvCircleName.setText(dataMap.getDataMap(d.k).getString(c.e));
            TextView textView = this.tvCirclePostsCount;
            textView.setText(dataMap.getDataMap(d.k).getString("postNum") + "帖子");
            TextView textView2 = this.tvCircleMemberCount;
            textView2.setText(dataMap.getDataMap(d.k).getString("memberNum") + "成员");
            String string = dataMap.getDataMap(d.k).getString("announcement");
            TextView textView3 = this.tvCircleNotice;
            if (TextUtils.isEmpty(string)) {
                string = "暂无公告";
            }
            textView3.setText(string);
            this.joinState = dataMap.getDataMap(d.k).getString("joinState");
            this.needCheck = "1".equals(dataMap.getDataMap(d.k).getString("auditTag"));
            if (!TextUtils.isEmpty(this.joinState)) {
                if (this.joinState.equals(K.k0)) {
                    this.ivIssuePosts.setVisibility(8);
                    this.tvJoinState.setText(this.needCheck ? "申请加入" : "加入");
                    this.tvJoinState.setEnabled(true);
                    this.ivSettings.setVisibility(8);
                    this.tvJoinState.setVisibility(0);
                } else if (this.joinState.equals("1")) {
                    this.tvJoinState.setText("等待审核");
                    this.tvJoinState.setEnabled(false);
                    this.ivIssuePosts.setVisibility(8);
                    this.ivSettings.setVisibility(8);
                    this.tvJoinState.setVisibility(0);
                } else if (this.joinState.equals("2")) {
                    this.ivIssuePosts.setVisibility(0);
                    this.ivSettings.setVisibility(0);
                }
            }
            this.role = dataMap.getDataMap(d.k).getString("role");
        }
    }

    private void showJoinApplyDialog() {
        ApplyJoinCircleDialog applyJoinCircleDialog = new ApplyJoinCircleDialog(this);
        applyJoinCircleDialog.setApplyClickListener(new ApplyJoinCircleDialog.ApplyClickListener() {
            public void submitApply(String str) {
                if (!TextUtils.isEmpty(str)) {
                    CircleDetailActivity.this.applyJoinCircle(str);
                }
            }
        });
        applyJoinCircleDialog.show();
    }

    /* access modifiers changed from: private */
    public void applyJoinCircle(final String str) {
        this.tvJoinState.setClickable(false);
        ApiUtil.getApi2().applyJoinCircle(this.circleId, str).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                if (TextUtils.isEmpty(str)) {
                    new MyToastUtils().ToastShow(CircleDetailActivity.this, "加入成功", R.drawable.ic_right_white);
                    CircleDetailActivity.this.tvJoinState.setVisibility(8);
                } else {
                    new MyToastUtils().ToastShow(CircleDetailActivity.this, "申请已发送", R.drawable.ic_right_white);
                }
                CircleDetailActivity.this.getCircleDetailInfo();
                CircleDetailActivity.this.sendBroadcast(new Intent(CircleHomeActivity.CIRCLE_CHANGE_ACTION));
                CircleDetailActivity.this.tvJoinState.setClickable(true);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                CircleDetailActivity.this.tvJoinState.setClickable(true);
                Log.e(">>>onError ", dataMap.toJson());
            }
        });
    }

    private void showNoticeDialog() {
        new NoticeDialog(this, this.tvCircleNotice.getText().toString()).show();
    }

    /* access modifiers changed from: private */
    public void getPosts(int i) {
        if (i == 1) {
            this.isRefresh = true;
        } else {
            this.isRefresh = false;
        }
        ApiUtil.getApi2().getCircleDetailPosts(this.circleId, "20", Integer.valueOf(i)).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleDetailActivity.this.refreshPostsUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                if (CircleDetailActivity.this.isRefresh) {
                    boolean unused = CircleDetailActivity.this.isRefresh = false;
                    CircleDetailActivity.this.smartRefreshLayout.finishRefresh();
                    return;
                }
                CircleDetailActivity.this.smartRefreshLayout.finishLoadMore();
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
            this.nestedScrollView.setNestedScrollingEnabled(true);
            this.rlEmpty.setVisibility(8);
            return;
        }
        this.nestedScrollView.setNestedScrollingEnabled(false);
        this.rlEmpty.setVisibility(0);
        this.llPostsBottom.setVisibility(8);
    }

    public void optionSuccess(int i) {
        if (i == 1) {
            RxBus.get().post("ADD_LIKE");
        } else {
            RxBus.get().post("DELETE_LIKE");
        }
    }
}
