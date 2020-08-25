package cn.xports.baselib.crash.compat;

import android.os.Message;

public interface IActivityKiller {
    void finishLaunchActivity(Message message);

    void finishPauseActivity(Message message);

    void finishResumeActivity(Message message);

    void finishStopActivity(Message message);
}
