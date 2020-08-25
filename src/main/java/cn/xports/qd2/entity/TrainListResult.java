package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.PageInfo;
import java.io.Serializable;

public class TrainListResult extends Response implements Serializable {
    private PageInfo<TrainItem> pageInfo;

    public PageInfo<TrainItem> getPageInfo() {
        return this.pageInfo;
    }

    public TrainListResult setPageInfo(PageInfo<TrainItem> pageInfo2) {
        this.pageInfo = pageInfo2;
        return this;
    }
}
