package cn.xports.qd2.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.RxBus;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.CircleAuditApplyAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import com.alipay.sdk.packet.d;
import com.google.gson.Gson;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class CircleApplyAuditFragment extends Fragment {
    /* access modifiers changed from: private */
    public View agree;
    private CircleAuditApplyAdapter auditApplyAdapter;
    /* access modifiers changed from: private */
    public List<DataMap> dataMapList = new ArrayList();
    /* access modifiers changed from: private */
    public View ignore;
    SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        public void onItemClick(SwipeMenuBridge swipeMenuBridge) {
            swipeMenuBridge.closeMenu();
            swipeMenuBridge.getDirection();
            int adapterPosition = swipeMenuBridge.getAdapterPosition();
            swipeMenuBridge.getPosition();
            AuditCircleApplyBean auditCircleApplyBean = new AuditCircleApplyBean();
            auditCircleApplyBean.id = ((DataMap) CircleApplyAuditFragment.this.dataMapList.get(adapterPosition)).getInteger("id").toString();
            auditCircleApplyBean.auditAccountId = SPUtil.Companion.getINSTANCE().getStringValue("coAccountId");
            auditCircleApplyBean.auditState = "2";
            auditCircleApplyBean.auditMessage = "";
            ArrayList arrayList = new ArrayList();
            arrayList.add(auditCircleApplyBean);
            CircleApplyAuditFragment.this.auditApply(arrayList);
            CircleApplyAuditFragment.this.dataMapList.remove(adapterPosition);
        }
    };
    private RelativeLayout rlEmpty;
    private RelativeLayout rl_bottom;
    private SwipeMenuRecyclerView smrvAudit;
    private View view;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.fragment_circle_apply_audit, (ViewGroup) null);
            initView();
            initData();
        }
        return this.view;
    }

    private void initView() {
        this.smrvAudit = this.view.findViewById(R.id.smrv_audit);
        this.rlEmpty = (RelativeLayout) this.view.findViewById(R.id.rl_circle_empty);
        this.rl_bottom = (RelativeLayout) this.view.findViewById(R.id.rl_bottom);
        this.ignore = this.view.findViewById(R.id.tv_all_ignore);
        this.agree = this.view.findViewById(R.id.tv_all_agree);
        ((TextView) this.view.findViewById(R.id.tv_empty_des)).setText("暂无申请消息");
        this.smrvAudit.setSwipeMenuCreator(new SwipeMenuCreator() {
            public void onCreateMenu(SwipeMenu swipeMenu, SwipeMenu swipeMenu2, int i) {
                swipeMenu2.addMenuItem(new SwipeMenuItem(CircleApplyAuditFragment.this.getActivity()).setBackground(R.drawable.sel_red).setText("忽略").setTextColor(-1).setTextSize(18).setWidth(CircleApplyAuditFragment.this.getResources().getDimensionPixelOffset(R.dimen.recycle_menu_width)).setHeight(-1));
            }
        });
        this.smrvAudit.setSwipeMenuItemClickListener(this.mMenuItemClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.auditApplyAdapter = new CircleAuditApplyAdapter(this.dataMapList, getContext());
        this.smrvAudit.setLayoutManager(linearLayoutManager);
        this.smrvAudit.setAdapter(this.auditApplyAdapter);
        this.auditApplyAdapter.setClickListener(new CircleAuditApplyAdapter.OnClickListener() {
            public void onAgreeClick(int i) {
                AuditCircleApplyBean auditCircleApplyBean = new AuditCircleApplyBean();
                auditCircleApplyBean.id = ((DataMap) CircleApplyAuditFragment.this.dataMapList.get(i)).getInteger("id").toString();
                auditCircleApplyBean.auditAccountId = SPUtil.Companion.getINSTANCE().getStringValue("coAccountId");
                auditCircleApplyBean.auditState = "1";
                auditCircleApplyBean.auditMessage = "";
                ArrayList arrayList = new ArrayList();
                arrayList.add(auditCircleApplyBean);
                CircleApplyAuditFragment.this.auditApply(arrayList);
                CircleApplyAuditFragment.this.dataMapList.remove(i);
                RxBus rxBus = RxBus.get();
                rxBus.post("AUDIT_" + CircleApplyAuditFragment.this.dataMapList.size());
            }
        });
        this.agree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CircleApplyAuditFragment.this.dataMapList.isEmpty()) {
                    CircleApplyAuditFragment.this.agree.setEnabled(false);
                    CircleApplyAuditFragment.this.allOperation("1");
                }
            }
        });
        this.ignore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CircleApplyAuditFragment.this.dataMapList.isEmpty()) {
                    CircleApplyAuditFragment.this.ignore.setEnabled(false);
                    CircleApplyAuditFragment.this.allOperation("2");
                }
            }
        });
    }

    private void initData() {
        getAuditMsg();
    }

    /* access modifiers changed from: private */
    public void getAuditMsg() {
        ApiUtil.getApi2().getWaitAuditApplyMsg(100, 1).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap((IView) getActivity(), true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleApplyAuditFragment.this.refreshMsgUi(dataMap);
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
            this.dataMapList.clear();
            this.dataMapList.addAll(dataList);
            this.auditApplyAdapter.notifyDataSetChanged();
            this.rl_bottom.setVisibility(0);
            ((CircleInteractMsgActivity) getActivity()).setCount(1, dataMap.getDataMap(d.k).getDataMap(K.extra).getIntValue("unreadMessage").intValue());
        }
    }

    /* access modifiers changed from: private */
    public void auditApply(final List<AuditCircleApplyBean> list) {
        ApiUtil.getApi2().auditJoinCircleApply(new Gson().toJson(list)).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap((IView) getActivity(), true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleApplyAuditFragment.this.refreshAuditUi(list.size() == CircleApplyAuditFragment.this.dataMapList.size());
                CircleApplyAuditFragment.this.ignore.setEnabled(true);
                CircleApplyAuditFragment.this.agree.setEnabled(true);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                CircleApplyAuditFragment.this.getAuditMsg();
                CircleApplyAuditFragment.this.ignore.setEnabled(true);
                CircleApplyAuditFragment.this.agree.setEnabled(true);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshAuditUi(boolean z) {
        if (z) {
            this.dataMapList.clear();
            RxBus.get().post("AUDIT_ALL");
            ((CircleInteractMsgActivity) getActivity()).setCount(1, 0);
        }
        this.auditApplyAdapter.notifyDataSetChanged();
        if (this.dataMapList.size() == 0) {
            this.rlEmpty.setVisibility(0);
            this.rl_bottom.setVisibility(8);
            return;
        }
        this.rl_bottom.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void allOperation(String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.dataMapList.size(); i++) {
            AuditCircleApplyBean auditCircleApplyBean = new AuditCircleApplyBean();
            auditCircleApplyBean.id = this.dataMapList.get(i).getInteger("id").toString();
            auditCircleApplyBean.auditAccountId = SPUtil.Companion.getINSTANCE().getStringValue("coAccountId");
            auditCircleApplyBean.auditState = str;
            auditCircleApplyBean.auditMessage = "";
            arrayList.add(auditCircleApplyBean);
        }
        auditApply(arrayList);
    }
}
