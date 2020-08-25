package com.bsit.coband.qdScore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bsit.coband.net.api.AppModule;
import com.bsit.coband.qdScore.adapter.TicketRecordAdapter;
import com.bsit.coband.qdScore.bean.TicketRecordBody;
import com.bsit.coband.qdScore.bean.TicketResult;
import com.convenient.qd.core.base.eventbus.Event;
import com.convenient.qd.core.base.eventbus.EventBusUtils;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;

public class QuanNoUseFragment extends Fragment {
    /* access modifiers changed from: private */
    public TicketRecordAdapter mAdapter;
    /* access modifiers changed from: private */
    public TicketResult mTicketResult;
    private RecyclerView rc_quanList;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427897, viewGroup, false);
        this.rc_quanList = (RecyclerView) inflate.findViewById(2131299035);
        this.rc_quanList.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventBusUtils.register(this);
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        initRecyclerView();
    }

    public void onResume() {
        super.onResume();
        getTicketRecordList();
    }

    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    private void initRecyclerView() {
        this.mAdapter = new TicketRecordAdapter(2131428157, (List) null);
        this.rc_quanList.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemChildClickListener(new 1(this));
        this.mAdapter.setOnItemClickListener(new 2(this));
    }

    /* access modifiers changed from: private */
    public void getTicketRecordList() {
        LoadingDiaLogUtils.showLoadingDialog(getActivity());
        TicketRecordBody ticketRecordBody = new TicketRecordBody();
        ticketRecordBody.setStatus(getArguments().getInt("status"));
        AppModule.getInstance().ticketRecord(ticketRecordBody, new 3(this));
    }

    private void ticketActivation(String str) {
        LoadingDiaLogUtils.showLoadingDialog(getActivity());
        TicketRecordBody ticketRecordBody = new TicketRecordBody();
        ticketRecordBody.setTicketRecordId(str);
        AppModule.getInstance().ticketActivation(ticketRecordBody, new 4(this));
    }

    @Subscribe
    public void onMyRefreshEvent(Event event) {
        if (event.getCode() == 9001) {
            ticketActivation(String.valueOf(this.mTicketResult.getId()));
        }
    }
}
