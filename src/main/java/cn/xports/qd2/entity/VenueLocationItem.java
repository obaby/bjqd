package cn.xports.qd2.entity;

import java.io.Serializable;

public class VenueLocationItem implements Serializable {
    private String address;
    private String latitude;
    private String longitude;
    private String name;
    private String venueId;
    private String venueName;

    public String getName() {
        return this.name;
    }

    public VenueLocationItem setName(String str) {
        this.name = str;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public VenueLocationItem setAddress(String str) {
        this.address = str;
        return this;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public VenueLocationItem setLatitude(String str) {
        this.latitude = str;
        return this;
    }

    public String getVenueName() {
        return this.venueName;
    }

    public VenueLocationItem setVenueName(String str) {
        this.venueName = str;
        return this;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public VenueLocationItem setVenueId(String str) {
        this.venueId = str;
        return this;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public VenueLocationItem setLongitude(String str) {
        this.longitude = str;
        return this;
    }
}
