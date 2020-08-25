package cn.xports.qd2.mvp;

import android.support.annotation.NonNull;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.DateWeekday;
import java.util.ArrayList;

public interface CourseResvView extends IView {
    void courseResv(@NonNull DataMap dataMap, int i);

    void showCoachs(@NonNull ArrayList<DataMap> arrayList);

    void showSchedule(@NonNull ArrayList<DateWeekday> arrayList, @NonNull DataMap dataMap);
}
