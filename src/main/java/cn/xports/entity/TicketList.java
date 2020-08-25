package cn.xports.entity;

import java.io.Serializable;

public class TicketList implements Serializable {
    private int backFee;
    private boolean checked;
    private int price;
    private String ticketId;
    private String time;
    private String unit;

    public int getBackFee() {
        return this.backFee;
    }

    public void setBackFee(int i) {
        this.backFee = i;
    }

    public String getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(String str) {
        this.ticketId = str;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }
}
