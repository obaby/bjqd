package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class AddPlayerResult extends Response implements Serializable {
    private MemberInfo playerInfo;

    public MemberInfo getPlayerInfo() {
        return this.playerInfo;
    }

    public AddPlayerResult setPlayerInfo(MemberInfo memberInfo) {
        this.playerInfo = memberInfo;
        return this;
    }
}
