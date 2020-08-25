package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class CampItemSignResult extends Response implements Serializable {
    private String enrollFee;
    private String enrollId;
    private List<MemberInfo> publicCampPlayers;
    private CampTeam publicCampTeam;

    public String getEnrollFee() {
        return this.enrollFee;
    }

    public CampItemSignResult setEnrollFee(String str) {
        this.enrollFee = str;
        return this;
    }

    public List<MemberInfo> getPublicCampPlayers() {
        return this.publicCampPlayers;
    }

    public CampItemSignResult setPublicCampPlayers(List<MemberInfo> list) {
        this.publicCampPlayers = list;
        return this;
    }

    public String getEnrollId() {
        return this.enrollId;
    }

    public CampItemSignResult setEnrollId(String str) {
        this.enrollId = str;
        return this;
    }

    public CampTeam getPublicCampTeam() {
        return this.publicCampTeam;
    }

    public CampItemSignResult setPublicCampTeam(CampTeam campTeam) {
        this.publicCampTeam = campTeam;
        return this;
    }
}
