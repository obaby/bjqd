package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class HomeWeatherBean extends Response implements Serializable {
    public Weather weather;

    public class Weather {
        public Now now;

        public Weather() {
        }

        public class Now {
            public String fl;
            public String hum;
            public String pres;
            public String tmp;
            public String vis;

            public Now() {
            }
        }
    }
}
