package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class TeamInfoResult extends Response implements Serializable {
    private List<MemberInfo> confirmPlayerList;
    private List<MemberInfo> normalPlayerList;
    private int playerNum;
    private CampItem publicCampItem;
    private CampTeam publicCampTeam;

    public List<MemberInfo> getNormalPlayerList() {
        return this.normalPlayerList;
    }

    public TeamInfoResult setNormalPlayerList(List<MemberInfo> list) {
        this.normalPlayerList = list;
        return this;
    }

    public List<MemberInfo> getConfirmPlayerList() {
        return this.confirmPlayerList;
    }

    public TeamInfoResult setConfirmPlayerList(List<MemberInfo> list) {
        this.confirmPlayerList = list;
        return this;
    }

    public CampItem getPublicCampItem() {
        return this.publicCampItem;
    }

    public TeamInfoResult setPublicCampItem(CampItem campItem) {
        this.publicCampItem = campItem;
        return this;
    }

    public CampTeam getPublicCampTeam() {
        return this.publicCampTeam;
    }

    public TeamInfoResult setPublicCampTeam(CampTeam campTeam) {
        this.publicCampTeam = campTeam;
        return this;
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    public TeamInfoResult setPlayerNum(int i) {
        this.playerNum = i;
        return this;
    }
}
