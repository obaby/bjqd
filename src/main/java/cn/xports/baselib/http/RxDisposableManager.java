package cn.xports.baselib.http;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.Map;

public class RxDisposableManager {
    private static RxDisposableManager instance;
    private Map<String, CompositeDisposable> disposableMap = new HashMap();

    private RxDisposableManager() {
    }

    public static synchronized RxDisposableManager getInstance() {
        RxDisposableManager rxDisposableManager;
        synchronized (RxDisposableManager.class) {
            if (instance == null) {
                instance = new RxDisposableManager();
            }
            rxDisposableManager = instance;
        }
        return rxDisposableManager;
    }

    public void add(String str, Disposable disposable) {
        if (this.disposableMap.containsKey(str)) {
            this.disposableMap.get(str).add(disposable);
            return;
        }
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
        this.disposableMap.put(str, compositeDisposable);
    }

    public void clear(String str) {
        if (this.disposableMap.keySet().contains(str)) {
            this.disposableMap.get(str).clear();
            this.disposableMap.remove(str);
        }
    }
}
