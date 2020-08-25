package cn.xports.qd2.mvp;

import android.support.annotation.NonNull;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.baselib.mvp.IModel;
import cn.xports.qd2.entity.DateWeekday;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.http.ApiService2;
import com.blankj.utilcode.util.CollectionUtils;
import java.util.ArrayList;

public class CourseResvPresenter extends BasePresenter<IModel, CourseResvView> {
    private ApiService2 service2 = ((ApiService2) RetrofitUtil.getInstance().create(ApiService2.class));

    public CourseResvPresenter(CourseResvView courseResvView) {
        super(courseResvView);
    }

    public void getCoachCourse() {
        this.service2.getCoachCourse().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ((CourseResvView) CourseResvPresenter.this.getRootView()).showCoachs(dataMap.getDataList(K.coachCourses));
            }

            public void onError(@NonNull DataMap dataMap) {
                ((CourseResvView) CourseResvPresenter.this.getRootView()).showCoachs(new ArrayList());
            }
        });
    }

    public void getCourseSchedule(String str, String str2) {
        this.service2.getCourseSchedule(str, str2).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ArrayList<DataMap> dataList = dataMap.getDataList(K.scheduleList);
                final DataMap dataMap2 = new DataMap();
                final ArrayList arrayList = new ArrayList();
                CollectionUtils.forAllDo(dataList, new CollectionUtils.Closure<DataMap>() {
                    public void execute(int i, DataMap dataMap) {
                        String string = dataMap.getString(K.lessonDate);
                        arrayList.add(new DateWeekday().setDate(string));
                        dataMap2.put(string, dataMap.getDataList(K.lessonList));
                        ((CourseResvView) CourseResvPresenter.this.getRootView()).showSchedule(arrayList, dataMap2);
                    }
                });
            }

            public void onError(@NonNull DataMap dataMap) {
                ((CourseResvView) CourseResvPresenter.this.getRootView()).showSchedule(new ArrayList(), new DataMap());
            }
        });
    }

    public void courseResv(String str, String str2, String str3) {
        this.service2.courseResv(str2, str, str3).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ((CourseResvView) CourseResvPresenter.this.getRootView()).courseResv(dataMap, 0);
            }

            public void onError(@NonNull DataMap dataMap) {
                ((CourseResvView) CourseResvPresenter.this.getRootView()).courseResv(dataMap, 1);
            }
        });
    }
}
