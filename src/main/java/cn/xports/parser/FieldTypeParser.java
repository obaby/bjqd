package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.FieldTypeBean;
import java.io.Serializable;
import java.util.List;

public class FieldTypeParser extends Response implements Serializable {
    private List<FieldTypeBean> fieldTypeList;
    private String fullTag;

    public List<FieldTypeBean> getFieldTypeList() {
        return this.fieldTypeList;
    }

    public FieldTypeParser setFieldTypeList(List<FieldTypeBean> list) {
        this.fieldTypeList = list;
        return this;
    }

    public String getFullTag() {
        return this.fullTag;
    }

    public FieldTypeParser setFullTag(String str) {
        this.fullTag = str;
        return this;
    }
}
