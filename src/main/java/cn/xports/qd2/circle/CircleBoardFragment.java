package cn.xports.qd2.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
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
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class CircleBoardFragment extends Fragment {
    private RecommendCircleAdapter adapter;
    private String boardId;
    private List<DiscoverCircleBean> dataList = new ArrayList();
    /* access modifiers changed from: private */
    public int index;
    /* access modifiers changed from: private */
    public boolean isHasNextPage;
    private boolean isRefresh;
    private RelativeLayout rlEmpty;
    private RecyclerView rv_discover;
    /* access modifiers changed from: private */
    public SmartRefreshLayout smartRefreshLayout;
    private View view;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.fragment_discover_circle, (ViewGroup) null);
            this.rv_discover = (RecyclerView) this.view.findViewById(R.id.rv_discover_circle);
            this.smartRefreshLayout = this.view.findViewById(R.id.srl_discover_circle);
            this.rlEmpty = (RelativeLayout) this.view.findViewById(R.id.rl_circle_empty);
            ((TextView) this.view.findViewById(R.id.tv_empty_des)).setText("暂无圈子");
            initData();
        }
        return this.view;
    }

    private void initData() {
        this.boardId = getArguments().getString("boardId");
        getData(1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.adapter = new RecommendCircleAdapter(this.dataList, getContext());
        this.rv_discover.setLayoutManager(linearLayoutManager);
        this.rv_discover.setAdapter(this.adapter);
        this.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        this.smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        this.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                CircleBoardFragment.this.getData(1);
            }
        });
        this.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (CircleBoardFragment.this.isHasNextPage) {
                    CircleBoardFragment.this.getData(CircleBoardFragment.this.index);
                } else {
                    CircleBoardFragment.this.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void getData(int i) {
        if (i == 1) {
            this.isRefresh = true;
        } else {
            this.isRefresh = false;
        }
        ApiUtil.getApi2().getBoardCircle(this.boardId, "20", Integer.valueOf(i)).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap((IView) getActivity(), true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleBoardFragment.this.refreshUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
        this.index = i + 1;
    }

    /* access modifiers changed from: private */
    public void refreshUi(DataMap dataMap) {
        if (this.isRefresh) {
            this.dataList.clear();
        }
        ArrayList<DataMap> dataList2 = dataMap.getDataMap(d.k).getDataList("list");
        this.isHasNextPage = dataMap.getDataMap(d.k).getBoolean("hasNextPage").booleanValue();
        for (int i = 0; i < dataList2.size(); i++) {
            DiscoverCircleBean discoverCircleBean = new DiscoverCircleBean();
            discoverCircleBean.imgUrl = dataList2.get(i).getString("avatar");
            discoverCircleBean.circleName = dataList2.get(i).getString(c.e);
            discoverCircleBean.postsNum = dataList2.get(i).getInteger("postNum") + "";
            discoverCircleBean.memberNum = dataList2.get(i).getInteger("memberNum") + "";
            discoverCircleBean.circleId = dataList2.get(i).getInteger("id") + "";
            this.dataList.add(discoverCircleBean);
        }
        if (this.isRefresh) {
            this.smartRefreshLayout.finishRefresh();
        } else {
            this.smartRefreshLayout.finishLoadMore();
        }
        if (this.dataList.size() > 0) {
            this.rlEmpty.setVisibility(8);
            this.adapter.notifyDataSetChanged();
        } else {
            this.rlEmpty.setVisibility(0);
        }
        Log.e(">>>", "dataList " + this.dataList.size());
    }
}
