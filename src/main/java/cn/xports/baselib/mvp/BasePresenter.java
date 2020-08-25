package cn.xports.baselib.mvp;

import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.mvp.IView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010\bB\u0011\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010\tB\u0007\b\u0016¢\u0006\u0002\u0010\nJ\u0017\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$H\u0004¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020'H\u0016J\u000f\u0010(\u001a\u0004\u0018\u00010'H\u0004¢\u0006\u0002\u0010)R\"\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u0007\u001a\u0004\u0018\u00018\u0001X\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010\t¨\u0006*"}, d2 = {"Lcn/xports/baselib/mvp/BasePresenter;", "M", "Lcn/xports/baselib/mvp/IModel;", "V", "Lcn/xports/baselib/mvp/IView;", "Lcn/xports/baselib/mvp/IPresenter;", "model", "rootView", "(Lcn/xports/baselib/mvp/IModel;Lcn/xports/baselib/mvp/IView;)V", "(Lcn/xports/baselib/mvp/IView;)V", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "setCompositeDisposable", "(Lio/reactivex/disposables/CompositeDisposable;)V", "getModel", "()Lcn/xports/baselib/mvp/IModel;", "setModel", "(Lcn/xports/baselib/mvp/IModel;)V", "Lcn/xports/baselib/mvp/IModel;", "getRootView", "()Lcn/xports/baselib/mvp/IView;", "setRootView", "Lcn/xports/baselib/mvp/IView;", "addDispose", "", "disposable", "Lio/reactivex/disposables/Disposable;", "(Lio/reactivex/disposables/Disposable;)Ljava/lang/Boolean;", "onDestroy", "", "unDispose", "()Lkotlin/Unit;", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BasePresenter.kt */
public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    private String TAG = getClass().getSimpleName();
    @Nullable
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Nullable
    private M model;
    @Nullable
    private V rootView;

    public void onStart() {
        IPresenter.DefaultImpls.onStart(this);
    }

    @Nullable
    public final CompositeDisposable getCompositeDisposable() {
        return this.compositeDisposable;
    }

    public final void setCompositeDisposable(@Nullable CompositeDisposable compositeDisposable2) {
        this.compositeDisposable = compositeDisposable2;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        this.TAG = str;
    }

    @Nullable
    public final M getModel() {
        return this.model;
    }

    public final void setModel(@Nullable M m) {
        this.model = m;
    }

    @Nullable
    public final V getRootView() {
        return this.rootView;
    }

    public final void setRootView(@Nullable V v) {
        this.rootView = v;
    }

    public BasePresenter(@Nullable M m, @Nullable V v) {
        this.model = m;
        this.rootView = v;
        onStart();
    }

    public BasePresenter(@Nullable V v) {
        this.rootView = v;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    public void onDestroy() {
        RxDisposableManager.getInstance().clear(this.TAG);
        unDispose();
        if (this.model != null) {
            M m = this.model;
            if (m == null) {
                Intrinsics.throwNpe();
            }
            m.onDestroy();
        }
        this.model = (IModel) null;
        this.rootView = (IView) null;
        this.compositeDisposable = null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Boolean addDispose(@NotNull Disposable disposable) {
        Intrinsics.checkParameterIsNotNull(disposable, "disposable");
        CompositeDisposable compositeDisposable2 = this.compositeDisposable;
        if (compositeDisposable2 != null) {
            return Boolean.valueOf(compositeDisposable2.add(disposable));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Unit unDispose() {
        CompositeDisposable compositeDisposable2 = this.compositeDisposable;
        if (compositeDisposable2 == null) {
            return null;
        }
        compositeDisposable2.clear();
        return Unit.INSTANCE;
    }
}
