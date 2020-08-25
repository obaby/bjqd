package cn.xports.qd2.mvp;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.baselib.mvp.IModel;
import cn.xports.qd2.entity.TermAndLessonResult;
import cn.xports.qd2.http.ApiService2;

public class TrainingDetailPresenter extends BasePresenter<IModel, TrainingDetailView> {
    /* access modifiers changed from: private */
    public TermAndLessonResult result;
    private ApiService2 service2 = ((ApiService2) RetrofitUtil.getInstance().create(ApiService2.class));

    public TrainingDetailPresenter(TrainingDetailView trainingDetailView) {
        super(trainingDetailView);
    }

    public void getTermLesson(String str) {
        this.service2.getTermLesson(str).compose(RxUtil.applyErrorsWithIO()).subscribe(new ProcessObserver<TermAndLessonResult>(getRootView()) {
            public void next(TermAndLessonResult termAndLessonResult) {
                TermAndLessonResult unused = TrainingDetailPresenter.this.result = termAndLessonResult;
                if (termAndLessonResult != null) {
                    ((TrainingDetailView) TrainingDetailPresenter.this.getRootView()).saveTermLessonResult(termAndLessonResult);
                }
            }
        });
    }
}
