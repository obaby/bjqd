package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.BookingRule;
import java.io.Serializable;
import java.util.List;

public class BookRuleParser extends Response implements Serializable {
    private List<BookingRule> rules;

    public List<BookingRule> getRules() {
        return this.rules;
    }

    public BookRuleParser setRules(List<BookingRule> list) {
        this.rules = list;
        return this;
    }
}
