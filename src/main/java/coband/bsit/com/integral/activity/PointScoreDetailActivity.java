package coband.bsit.com.integral.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.adapter.PointScoreListAdapter;
import coband.bsit.com.integral.api.DataManager;
import coband.bsit.com.integral.bean.CreditListBean;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.utils.ToastUtils;
import com.convenient.qd.core.widget.EndlessRecyclerOnScrollListener;
import com.convenient.qd.core.widget.LoadMoreWrapper;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import io.reactivex.observers.DefaultObserver;
import java.util.ArrayList;
import java.util.List;

public class PointScoreDetailActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout back;
    /* access modifiers changed from: private */
    public int currentPage = 1;
    RelativeLayout data_empty_view;
    /* access modifiers changed from: private */
    public LoadMoreWrapper loadMoreWrapper;
    private PointScoreListAdapter pointScoreListAdapter;
    RecyclerView rc_score;
    RelativeLayout right_btn3;
    TextView right_text;
    /* access modifiers changed from: private */
    public List<CreditListBean.ResultBean.DataGridBean.RowsBean> rowsBeans = new ArrayList();
    TextView title;
    /* access modifiers changed from: private */
    public int totalCount;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    static /* synthetic */ int access$408(PointScoreDetailActivity pointScoreDetailActivity) {
        int i = pointScoreDetailActivity.currentPage;
        pointScoreDetailActivity.currentPage = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return R.layout.integral_activity_point_score_detail_layout;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointScoreDetailActivity] */
    public void initView() {
        setStatusBar(R.id.title_header);
        this.rc_score = (RecyclerView) findViewById(R.id.rc_score);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (RelativeLayout) findViewById(R.id.back);
        this.data_empty_view = (RelativeLayout) findViewById(R.id.data_empty_view);
        this.right_btn3 = (RelativeLayout) findViewById(R.id.right_btn3);
        this.right_text = (TextView) findViewById(R.id.right_text);
        this.right_btn3.setVisibility(0);
        this.right_text.setText("信豆规则");
        this.right_text.setTextColor(getResources().getColor(R.color.black));
        this.title.setText("信豆明细");
        this.rc_score.setLayoutManager(new LinearLayoutManager(this));
        this.pointScoreListAdapter = new PointScoreListAdapter(this, this.rowsBeans);
        this.loadMoreWrapper = new LoadMoreWrapper(this.pointScoreListAdapter);
        this.rc_score.setAdapter(this.loadMoreWrapper);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointScoreDetailActivity, com.convenient.qd.core.base.mvp.BaseActivity] */
    public void initData() {
        PointScoreDetailActivity.super.initData();
        LoadingDiaLogUtils.showLoadingDialog(this);
        loadData(1);
    }

    /* access modifiers changed from: private */
    public void loadData(final int i) {
        DataManager.getInstance().getCreditsList(i, new DefaultObserver<CreditListBean>() {
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, coband.bsit.com.integral.activity.PointScoreDetailActivity] */
            public void onNext(CreditListBean creditListBean) {
                PointScoreDetailActivity.this.initEmptyView();
                LoadingDiaLogUtils.dismisDialog();
                if (i != 1) {
                    LoadMoreWrapper access$100 = PointScoreDetailActivity.this.loadMoreWrapper;
                    PointScoreDetailActivity.this.loadMoreWrapper.getClass();
                    access$100.setLoadState(2);
                }
                if (creditListBean != null) {
                    if (creditListBean.getCode() != 200) {
                        ToastUtils.showShort(PointScoreDetailActivity.this, creditListBean.getMessage());
                    } else if (creditListBean.getResult() != null && creditListBean.getResult().getDataGrid() != null) {
                        int unused = PointScoreDetailActivity.this.totalCount = creditListBean.getResult().getDataGrid().getTotal();
                        PointScoreDetailActivity.this.rowsBeans.addAll(creditListBean.getResult().getDataGrid().getRows());
                        PointScoreDetailActivity.this.loadMoreWrapper.notifyDataSetChanged();
                        PointScoreDetailActivity.this.initEmptyView();
                    }
                }
            }

            public void onError(Throwable th) {
                LoadingDiaLogUtils.dismisDialog();
                PointScoreDetailActivity.this.initEmptyView();
            }

            public void onComplete() {
                LoadingDiaLogUtils.dismisDialog();
                PointScoreDetailActivity.this.initEmptyView();
            }
        });
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [coband.bsit.com.integral.activity.PointScoreDetailActivity$2, android.support.v7.widget.RecyclerView$OnScrollListener] */
    public void initListener() {
        PointScoreDetailActivity.super.initListener();
        this.back.setOnClickListener(this);
        this.right_text.setOnClickListener(this);
        this.rc_score.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            public void onLoadMore() {
                if (PointScoreDetailActivity.this.rowsBeans.size() < PointScoreDetailActivity.this.totalCount) {
                    LoadMoreWrapper access$100 = PointScoreDetailActivity.this.loadMoreWrapper;
                    PointScoreDetailActivity.this.loadMoreWrapper.getClass();
                    access$100.setLoadState(1);
                    PointScoreDetailActivity.access$408(PointScoreDetailActivity.this);
                    PointScoreDetailActivity.this.loadData(PointScoreDetailActivity.this.currentPage);
                    return;
                }
                com.blankj.utilcode.util.ToastUtils.showShort("没有更多数据了");
            }
        });
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointScoreDetailActivity] */
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back) {
            finish();
        } else if (id == R.id.right_text) {
            PointRulesActivity.launch(this);
        }
    }

    /* access modifiers changed from: private */
    public void initEmptyView() {
        if (this.rowsBeans == null || this.rowsBeans.size() <= 0) {
            this.data_empty_view.setVisibility(0);
            this.rc_score.setVisibility(8);
            return;
        }
        this.data_empty_view.setVisibility(8);
        this.rc_score.setVisibility(0);
    }
}
