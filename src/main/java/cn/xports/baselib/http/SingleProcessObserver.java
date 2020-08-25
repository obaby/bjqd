package cn.xports.baselib.http;

import cn.xports.baselib.mvp.IView;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public abstract class SingleProcessObserver<T> implements SingleObserver<T> {
    protected IView iView;
    protected boolean isShowDialog;
    protected String tag;

    public abstract void next(T t);

    public void onError(ResponseThrowable responseThrowable) {
    }

    public SingleProcessObserver(String str) {
        this.tag = str;
        this.isShowDialog = true;
    }

    public SingleProcessObserver(IView iView2) {
        this.tag = iView2.getClass().getSimpleName();
        this.isShowDialog = true;
        this.iView = iView2;
    }

    public SingleProcessObserver(String str, boolean z) {
        this(str);
        this.isShowDialog = z;
    }

    public SingleProcessObserver(IView iView2, boolean z) {
        this(iView2);
        this.isShowDialog = z;
    }

    public void onSubscribe(Disposable disposable) {
        if (this.isShowDialog && this.iView != null) {
            this.iView.showLoading();
        }
        RxDisposableManager.getInstance().add(this.tag, disposable);
    }

    public void onSuccess(T t) {
        if (this.isShowDialog && this.iView != null) {
            this.iView.hideLoading();
        }
        if (t == null) {
            onError(new ResponseThrowable((Throwable) new NullPointerException(), 100101, "对象值未空"));
        } else {
            next(t);
        }
    }

    public void onError(Throwable th) {
        if (this.isShowDialog && this.iView != null) {
            this.iView.hideLoading();
        }
        try {
            onError((ResponseThrowable) th);
        } catch (Throwable unused) {
        }
    }
}
