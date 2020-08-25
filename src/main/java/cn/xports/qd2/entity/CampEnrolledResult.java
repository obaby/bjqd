package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class CampEnrolledResult extends Response implements Serializable {
    private List<CampEnrollment> campEnrollmentList;
    private int totalPage;

    public int getTotalPage() {
        return this.totalPage;
    }

    public CampEnrolledResult setTotalPage(int i) {
        this.totalPage = i;
        return this;
    }

    public List<CampEnrollment> getCampEnrollmentList() {
        return this.campEnrollmentList;
    }

    public CampEnrolledResult setCampEnrollmentList(List<CampEnrollment> list) {
        this.campEnrollmentList = list;
        return this;
    }
}
