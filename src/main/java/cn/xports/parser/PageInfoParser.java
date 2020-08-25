package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.PageInfo;
import java.io.Serializable;

public class PageInfoParser<T> extends Response implements Serializable {
    private PageInfo<T> pageInfo;
    private String sysdate;
    private String trainingCourseNewModel;

    public String getSysdate() {
        return this.sysdate;
    }

    public void setSysdate(String str) {
        this.sysdate = str;
    }

    public PageInfo<T> getPageInfo() {
        return this.pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo2) {
        this.pageInfo = pageInfo2;
    }

    public String getTrainingCourseNewModel() {
        return this.trainingCourseNewModel;
    }

    public void setTrainingCourseNewModel(String str) {
        this.trainingCourseNewModel = str;
    }
}
