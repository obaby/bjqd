package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class CourseElementsReslut extends Response implements Serializable {
    private List<CourseElement> elementList;

    public List<CourseElement> getElementList() {
        return this.elementList;
    }

    public void setElementList(List<CourseElement> list) {
        this.elementList = list;
    }
}
