package cn.xports.qd2.circle;

import android.text.NoCopySpan;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

public class OverLinkMovementMethod extends LinkMovementMethod {
    private static Object FROM_BELOW = new NoCopySpan.Concrete();
    public static boolean canScroll;
    private static OverLinkMovementMethod sInstance;

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 2 || canScroll) {
            return super.onTouchEvent(textView, spannable, motionEvent);
        }
        return true;
    }

    public static MovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new OverLinkMovementMethod();
        }
        return sInstance;
    }
}
