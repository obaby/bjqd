package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class HomeBannerBean extends Response implements Serializable {
    public List<AdList> adList;

    public class AdList implements Serializable {
        public long appId;
        public long associatedEntityId;
        public String associatedEntityType;
        public long centerId;
        public String contType;
        public String content;
        public String cover;
        public long createStaffId;
        public String createTime;
        public String endDate;
        public long id;
        public String outerLink;
        public String startDate;
        public String state;
        public String title;
        public String type;
        public long updateStaffId;
        public String updateTime;

        public AdList() {
        }
    }
}
