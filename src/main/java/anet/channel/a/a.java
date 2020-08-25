package anet.channel.a;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.flow.FlowStat;
import anet.channel.flow.INetworkAnalysis;
import anet.channel.util.ALog;
import com.taobao.analysis.FlowCenter;

/* compiled from: Taobao */
public class a implements INetworkAnalysis {

    /* renamed from: a  reason: collision with root package name */
    private boolean f141a;

    public a() {
        try {
            Class.forName("com.taobao.analysis.FlowCenter");
            this.f141a = true;
        } catch (Exception unused) {
            this.f141a = false;
            ALog.e("DefaultNetworkAnalysis", "no NWNetworkAnalysisSDK sdk", (String) null, new Object[0]);
        }
    }

    public void commitFlow(FlowStat flowStat) {
        if (this.f141a) {
            FlowCenter.getInstance().commitFlow(GlobalAppRuntimeInfo.getContext(), flowStat.refer, flowStat.protocoltype, flowStat.req_identifier, flowStat.upstream, flowStat.downstream);
        }
    }
}
