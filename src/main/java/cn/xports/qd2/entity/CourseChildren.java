package cn.xports.qd2.entity;

import java.io.Serializable;

public class CourseChildren implements Serializable {
    private String childrenId;
    private String childrenName;

    public String getChildrenId() {
        return this.childrenId;
    }

    public void setChildrenId(String str) {
        this.childrenId = str;
    }

    public String getChildrenName() {
        return this.childrenName;
    }

    public void setChildrenName(String str) {
        this.childrenName = str;
    }

    public String toString() {
        return this.childrenName;
    }
}
