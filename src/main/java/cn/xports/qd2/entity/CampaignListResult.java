package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.CampaignInfo;
import java.io.Serializable;
import java.util.List;

public class CampaignListResult extends Response implements Serializable {
    private List<CampaignInfo> campaignList;
    private int totalPage;

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int i) {
        this.totalPage = i;
    }

    public List<CampaignInfo> getCampaignList() {
        return this.campaignList;
    }

    public void setCampaignList(List<CampaignInfo> list) {
        this.campaignList = list;
    }
}
