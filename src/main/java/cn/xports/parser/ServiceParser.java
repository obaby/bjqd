package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.Service;
import java.io.Serializable;
import java.util.List;

public class ServiceParser extends Response implements Serializable {
    private List<Service> serviceList;
    private String sysdate;

    public String getSysdate() {
        return this.sysdate;
    }

    public void setSysdate(String str) {
        this.sysdate = str;
    }

    public List<Service> getServiceList() {
        return this.serviceList;
    }

    public void setServiceList(List<Service> list) {
        this.serviceList = list;
    }
}
