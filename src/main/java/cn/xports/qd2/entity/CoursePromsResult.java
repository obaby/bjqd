package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class CoursePromsResult extends Response implements Serializable {
    private List<CoursePromInfo> proms;

    public List<CoursePromInfo> getProms() {
        return this.proms;
    }

    public void setProms(List<CoursePromInfo> list) {
        this.proms = list;
    }
}
