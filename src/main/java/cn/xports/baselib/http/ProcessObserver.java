package cn.xports.baselib.http;

import cn.xports.baselib.mvp.IView;
import io.reactivex.disposables.Disposable;

public abstract class ProcessObserver<T> extends RxObserver<T> {
    protected IView iView;
    protected boolean isShowDialog;

    public abstract void next(T t);

    public void onError(ResponseThrowable responseThrowable) {
    }

    public ProcessObserver(String str) {
        super(str);
        this.isShowDialog = true;
    }

    public ProcessObserver(IView iView2) {
        super(iView2.getClass().getSimpleName());
        this.isShowDialog = true;
        this.iView = iView2;
    }

    public ProcessObserver(String str, boolean z) {
        super(str);
        this.isShowDialog = z;
    }

    public ProcessObserver(IView iView2, boolean z) {
        this(iView2);
        this.isShowDialog = z;
    }

    public void onSubscribe(Disposable disposable) {
        if (this.isShowDialog && this.iView != null) {
            this.iView.showLoading();
        }
        super.onSubscribe(disposable);
    }

    public void onNext(T t) {
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
        super.onError(th);
        try {
            onError((ResponseThrowable) th);
        } catch (Throwable unused) {
        }
    }

    public void onComplete() {
        if (this.isShowDialog && this.iView != null) {
            this.iView.hideLoading();
        }
        super.onComplete();
    }
}
