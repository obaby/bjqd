package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class SignElementsResult extends Response implements Serializable {
    private List<ViewElementData> personalElements;
    private List<ViewElementData> teamElements;

    public List<ViewElementData> getPersonalElements() {
        return this.personalElements;
    }

    public SignElementsResult setPersonalElements(List<ViewElementData> list) {
        this.personalElements = list;
        return this;
    }

    public List<ViewElementData> getTeamElements() {
        return this.teamElements;
    }

    public SignElementsResult setTeamElements(List<ViewElementData> list) {
        this.teamElements = list;
        return this;
    }
}
