package cn.xports.baselib.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<T> implements Observer<T> {
    protected String tag;

    public void onComplete() {
    }

    public void onError(Throwable th) {
    }

    public void onNext(T t) {
    }

    public RxObserver(String str) {
        this.tag = str;
    }

    public void onSubscribe(Disposable disposable) {
        RxDisposableManager.getInstance().add(this.tag, disposable);
    }
}
