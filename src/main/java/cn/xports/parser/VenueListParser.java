package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.Venue;
import java.io.Serializable;
import java.util.List;

public class VenueListParser extends Response implements Serializable {
    private List<Venue> venueList;

    public List<Venue> getVenueList() {
        return this.venueList;
    }

    public void setVenueList(List<Venue> list) {
        this.venueList = list;
    }
}
