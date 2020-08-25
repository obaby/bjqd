package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.BookingDay;
import java.io.Serializable;
import java.util.List;

public class BookDayParser extends Response implements Serializable {
    private List<BookingDay> week;

    public List<BookingDay> getWeek() {
        return this.week;
    }

    public BookDayParser setWeek(List<BookingDay> list) {
        this.week = list;
        return this;
    }
}
