package cn.xports.entity;

import java.io.Serializable;

public class VenueService implements Serializable {
    private String address;
    private int bookDays;
    private String centerId;
    private String endDate;
    private int endSegment;
    private int fieldTag;
    private String fullTag;
    private double latitude;
    private double longitude;
    private String serviceId;
    private String serviceName;
    private String startDate;
    private int startSegment;
    private Venue venue;
    private String venueId;

    public Venue getVenue() {
        return this.venue;
    }

    public void setVenue(Venue venue2) {
        this.venue = venue2;
    }

    public int getStartSegment() {
        return this.startSegment;
    }

    public void setStartSegment(int i) {
        this.startSegment = i;
    }

    public int getFieldTag() {
        return this.fieldTag;
    }

    public void setFieldTag(int i) {
        this.fieldTag = i;
    }

    public String getCenterId() {
        return this.centerId;
    }

    public void setCenterId(String str) {
        this.centerId = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public int getBookDays() {
        return this.bookDays;
    }

    public void setBookDays(int i) {
        this.bookDays = i;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public void setVenueId(String str) {
        this.venueId = str;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public int getEndSegment() {
        return this.endSegment;
    }

    public void setEndSegment(int i) {
        this.endSegment = i;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public String getFullTag() {
        return this.fullTag;
    }

    public void setFullTag(String str) {
        this.fullTag = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }
}
