package cn.xports.qd2.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import com.alipay.sdk.packet.d;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class CircleMsgInteractFragment extends Fragment {
    private RelativeLayout rlEmpty;
    private RecyclerView rvMsgInteract;
    private View view;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.fragment_circle_msg_interact, (ViewGroup) null);
            initView();
            getMsgData(1);
        }
        return this.view;
    }

    private void initView() {
        this.rvMsgInteract = (RecyclerView) this.view.findViewById(R.id.rv_interact_msg);
        this.rlEmpty = (RelativeLayout) this.view.findViewById(R.id.rl_circle_empty);
        ((TextView) this.view.findViewById(R.id.tv_empty_des)).setText("暂无互动消息");
    }

    private void getMsgData(int i) {
        ApiUtil.getApi2().getInteractMsgNews(100, 1).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap((IView) getActivity(), true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                if (CircleMsgInteractFragment.this.getActivity() != null && !CircleMsgInteractFragment.this.getActivity().isFinishing()) {
                    CircleMsgInteractFragment.this.refreshMsgUi(dataMap);
                }
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshMsgUi(DataMap dataMap) {
        if (dataMap == null) {
            this.rlEmpty.setVisibility(0);
        } else if (dataMap.getDataMap(d.k) == null) {
            this.rlEmpty.setVisibility(0);
        } else {
            ArrayList<DataMap> dataList = dataMap.getDataMap(d.k).getDataList("list");
            if (dataList == null || dataList.size() == 0) {
                this.rlEmpty.setVisibility(0);
                return;
            }
            this.rlEmpty.setVisibility(8);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            MsgInteractAdapter msgInteractAdapter = new MsgInteractAdapter(dataList, getContext());
            this.rvMsgInteract.setLayoutManager(linearLayoutManager);
            this.rvMsgInteract.setAdapter(msgInteractAdapter);
            ((CircleInteractMsgActivity) getActivity()).setCount(0, dataMap.getDataMap(d.k).getDataMap(K.extra).getIntValue("unreadMessage").intValue());
        }
    }
}
