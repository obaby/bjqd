package cn.xports.baselib.rxbind.view;

import android.support.annotation.CheckResult;
import android.support.annotation.RequiresApi;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import cn.xports.baselib.rxbind.InitialValueObservable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/view/RxView__ViewAttachEventObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewAttachesObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewClickObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewDragObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewFocusChangeObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewHoverObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewKeyObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewLayoutChangeEventObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewLayoutChangeObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewLongClickObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewScrollChangeEventObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewSystemUiVisibilityChangeObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewTouchObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewTreeObserverDrawObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewTreeObserverGlobalLayoutObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewTreeObserverPreDrawObservableKt", "cn/xports/baselib/rxbind/view/RxView__ViewVisibilityConsumerKt"}, k = 4, mv = {1, 1, 15})
public final class RxView {
    @NotNull
    @CheckResult
    public static final Observable<ViewAttachEvent> attachEvents(@NotNull View view) {
        return RxView__ViewAttachEventObservableKt.attachEvents(view);
    }

    @NotNull
    @CheckResult
    public static final Observable<Unit> attaches(@NotNull View view) {
        return RxView__ViewAttachesObservableKt.attaches(view);
    }

    @NotNull
    @CheckResult
    public static final Observable<Unit> clicks(@NotNull View view) {
        return RxView__ViewClickObservableKt.clicks(view);
    }

    @NotNull
    @CheckResult
    public static final Observable<Unit> detaches(@NotNull View view) {
        return RxView__ViewAttachesObservableKt.detaches(view);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<DragEvent> drags(@NotNull View view) {
        return RxView__ViewDragObservableKt.drags$default(view, (Function1) null, 1, (Object) null);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<DragEvent> drags(@NotNull View view, @NotNull Function1<? super DragEvent, Boolean> function1) {
        return RxView__ViewDragObservableKt.drags(view, function1);
    }

    @RequiresApi(16)
    @CheckResult
    @NotNull
    public static final Observable<Unit> draws(@NotNull View view) {
        return RxView__ViewTreeObserverDrawObservableKt.draws(view);
    }

    @NotNull
    @CheckResult
    public static final InitialValueObservable<Boolean> focusChanges(@NotNull View view) {
        return RxView__ViewFocusChangeObservableKt.focusChanges(view);
    }

    @NotNull
    @CheckResult
    public static final Observable<Unit> globalLayouts(@NotNull View view) {
        return RxView__ViewTreeObserverGlobalLayoutObservableKt.globalLayouts(view);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<MotionEvent> hovers(@NotNull View view) {
        return RxView__ViewHoverObservableKt.hovers$default(view, (Function1) null, 1, (Object) null);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<MotionEvent> hovers(@NotNull View view, @NotNull Function1<? super MotionEvent, Boolean> function1) {
        return RxView__ViewHoverObservableKt.hovers(view, function1);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<KeyEvent> keys(@NotNull View view) {
        return RxView__ViewKeyObservableKt.keys$default(view, (Function1) null, 1, (Object) null);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<KeyEvent> keys(@NotNull View view, @NotNull Function1<? super KeyEvent, Boolean> function1) {
        return RxView__ViewKeyObservableKt.keys(view, function1);
    }

    @NotNull
    @CheckResult
    public static final Observable<ViewLayoutChangeEvent> layoutChangeEvents(@NotNull View view) {
        return RxView__ViewLayoutChangeEventObservableKt.layoutChangeEvents(view);
    }

    @NotNull
    @CheckResult
    public static final Observable<Unit> layoutChanges(@NotNull View view) {
        return RxView__ViewLayoutChangeObservableKt.layoutChanges(view);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<Unit> longClicks(@NotNull View view) {
        return RxView__ViewLongClickObservableKt.longClicks$default(view, (Function0) null, 1, (Object) null);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<Unit> longClicks(@NotNull View view, @NotNull Function0<Boolean> function0) {
        return RxView__ViewLongClickObservableKt.longClicks(view, function0);
    }

    @NotNull
    @CheckResult
    public static final Observable<Unit> preDraws(@NotNull View view, @NotNull Function0<Boolean> function0) {
        return RxView__ViewTreeObserverPreDrawObservableKt.preDraws(view, function0);
    }

    @RequiresApi(23)
    @CheckResult
    @NotNull
    public static final Observable<ViewScrollChangeEvent> scrollChangeEvents(@NotNull View view) {
        return RxView__ViewScrollChangeEventObservableKt.scrollChangeEvents(view);
    }

    @NotNull
    @CheckResult
    public static final Observable<Integer> systemUiVisibilityChanges(@NotNull View view) {
        return RxView__ViewSystemUiVisibilityChangeObservableKt.systemUiVisibilityChanges(view);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<MotionEvent> touches(@NotNull View view) {
        return RxView__ViewTouchObservableKt.touches$default(view, (Function1) null, 1, (Object) null);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<MotionEvent> touches(@NotNull View view, @NotNull Function1<? super MotionEvent, Boolean> function1) {
        return RxView__ViewTouchObservableKt.touches(view, function1);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Consumer<? super Boolean> visibility(@NotNull View view) {
        return RxView__ViewVisibilityConsumerKt.visibility$default(view, 0, 1, (Object) null);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Consumer<? super Boolean> visibility(@NotNull View view, int i) {
        return RxView__ViewVisibilityConsumerKt.visibility(view, i);
    }
}
