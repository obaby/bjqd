package cn.xports.entity;

import java.io.Serializable;
import java.util.List;

public class VenueList implements Serializable {
    private List<Venue> venueList;

    public VenueList(List<Venue> list) {
        this.venueList = list;
    }

    public List<Venue> getVenueList() {
        return this.venueList;
    }

    public void setVenueList(List<Venue> list) {
        this.venueList = list;
    }
}
