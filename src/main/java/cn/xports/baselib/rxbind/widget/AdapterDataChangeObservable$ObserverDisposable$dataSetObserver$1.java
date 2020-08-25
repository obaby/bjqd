package cn.xports.baselib.rxbind.widget;

import android.database.DataSetObserver;
import cn.xports.baselib.rxbind.widget.AdapterDataChangeObservable;
import io.reactivex.Observer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"cn/xports/baselib/rxbind/widget/AdapterDataChangeObservable$ObserverDisposable$dataSetObserver$1", "Landroid/database/DataSetObserver;", "onChanged", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AdapterDataChangeObservable.kt */
public final class AdapterDataChangeObservable$ObserverDisposable$dataSetObserver$1 extends DataSetObserver {
    final /* synthetic */ Observer $observer;
    final /* synthetic */ AdapterDataChangeObservable.ObserverDisposable this$0;

    AdapterDataChangeObservable$ObserverDisposable$dataSetObserver$1(AdapterDataChangeObservable.ObserverDisposable observerDisposable, Observer observer) {
        this.this$0 = observerDisposable;
        this.$observer = observer;
    }

    public void onChanged() {
        if (!this.this$0.isDisposed()) {
            this.$observer.onNext(this.this$0.adapter);
        }
    }
}
