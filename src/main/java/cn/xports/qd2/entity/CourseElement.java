package cn.xports.qd2.entity;

import java.io.Serializable;
import java.util.List;

public class CourseElement implements Serializable {
    private Long courseId;
    private int elementId;
    private String elementName;
    private String inputType;
    private List<CourseOptions> options;
    private int order;
    private String required;

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long l) {
        this.courseId = l;
    }

    public int getElementId() {
        return this.elementId;
    }

    public void setElementId(int i) {
        this.elementId = i;
    }

    public String getElementName() {
        return this.elementName;
    }

    public void setElementName(String str) {
        this.elementName = str;
    }

    public String getInputType() {
        return this.inputType;
    }

    public void setInputType(String str) {
        this.inputType = str;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int i) {
        this.order = i;
    }

    public String getRequired() {
        return this.required;
    }

    public void setRequired(String str) {
        this.required = str;
    }

    public List<CourseOptions> getOptions() {
        return this.options;
    }

    public void setOptions(List<CourseOptions> list) {
        this.options = list;
    }
}
