package cn.xports.baselib.util;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

public class RxBus {
    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        this.mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus get() {
        return Holder.BUS;
    }

    public void post(Object obj) {
        this.mBus.onNext(obj);
    }

    public <T> Flowable<T> toFlowable(Class<T> cls) {
        return this.mBus.ofType(cls);
    }

    public Flowable<Object> toFlowable() {
        return this.mBus;
    }

    public boolean hasSubscribers() {
        return this.mBus.hasSubscribers();
    }

    private static class Holder {
        /* access modifiers changed from: private */
        public static final RxBus BUS = new RxBus();

        private Holder() {
        }
    }
}
