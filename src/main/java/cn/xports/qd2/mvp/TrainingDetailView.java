package cn.xports.qd2.mvp;

import android.support.annotation.NonNull;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.TermAndLessonResult;

public interface TrainingDetailView extends IView {
    void saveTermLessonResult(@NonNull TermAndLessonResult termAndLessonResult);
}
