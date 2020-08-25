package cn.xports.qd2.entity;

import java.io.Serializable;
import java.util.List;

public class CourseOptions implements Serializable {
    private List<CourseChildren> childrenList;
    private String parentId;
    private String parentName;

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String str) {
        this.parentName = str;
    }

    public List<CourseChildren> getChildrenList() {
        return this.childrenList;
    }

    public void setChildrenList(List<CourseChildren> list) {
        this.childrenList = list;
    }

    public String toString() {
        return this.parentName;
    }
}
