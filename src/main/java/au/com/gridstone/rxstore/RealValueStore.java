package au.com.gridstone.rxstore;

import au.com.gridstone.rxstore.ValueStore;
import com.alipay.sdk.packet.d;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.locks.ReentrantReadWriteLock;

final class RealValueStore<T> implements ValueStore<T> {
    /* access modifiers changed from: private */
    public final Converter converter;
    /* access modifiers changed from: private */
    public final File file;
    /* access modifiers changed from: private */
    public final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /* access modifiers changed from: private */
    public final Type type;
    /* access modifiers changed from: private */
    public final PublishSubject<ValueStore.ValueUpdate<T>> updateSubject = PublishSubject.create();

    RealValueStore(@NonNull File file2, @NonNull Converter converter2, @NonNull Type type2) {
        Utils.assertNotNull(file2, "file");
        Utils.assertNotNull(converter2, "converter");
        Utils.assertNotNull(type2, d.p);
        this.file = file2;
        this.converter = converter2;
        this.type = type2;
    }

    @NonNull
    public Maybe<T> get() {
        return Maybe.create(new MaybeOnSubscribe<T>() {
            public void subscribe(final MaybeEmitter<T> maybeEmitter) throws Exception {
                Utils.runInReadLock(RealValueStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (!RealValueStore.this.file.exists()) {
                            maybeEmitter.onComplete();
                            return;
                        }
                        Object read = RealValueStore.this.converter.read(RealValueStore.this.file, RealValueStore.this.type);
                        if (read == null) {
                            maybeEmitter.onComplete();
                        }
                        maybeEmitter.onSuccess(read);
                    }
                });
            }
        });
    }

    @Nullable
    public T blockingGet() {
        return get().blockingGet();
    }

    @NonNull
    public Single<T> observePut(@NonNull final T t) {
        Utils.assertNotNull(t, "value");
        return Single.create(new SingleOnSubscribe<T>() {
            public void subscribe(final SingleEmitter<T> singleEmitter) throws Exception {
                Utils.runInWriteLock(RealValueStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (RealValueStore.this.file.exists() || RealValueStore.this.file.createNewFile()) {
                            Utils.converterWrite(t, RealValueStore.this.converter, RealValueStore.this.type, RealValueStore.this.file);
                            singleEmitter.onSuccess(t);
                            RealValueStore.this.updateSubject.onNext(new ValueStore.ValueUpdate(t));
                            return;
                        }
                        throw new IOException("Could not create file for store.");
                    }
                });
            }
        });
    }

    public void put(@NonNull T t) {
        put(t, Schedulers.io());
    }

    public void put(@NonNull T t, @NonNull Scheduler scheduler) {
        Utils.assertNotNull(scheduler, "scheduler");
        observePut(t).subscribeOn(scheduler).subscribe();
    }

    @NonNull
    public Observable<ValueStore.ValueUpdate<T>> observe() {
        return this.updateSubject.startWith(get().map(new Function<T, ValueStore.ValueUpdate<T>>() {
            public ValueStore.ValueUpdate<T> apply(T t) throws Exception {
                return new ValueStore.ValueUpdate<>(t);
            }
        }).defaultIfEmpty(ValueStore.ValueUpdate.empty()).toObservable());
    }

    @NonNull
    public Completable observeClear() {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(final CompletableEmitter completableEmitter) throws Exception {
                Utils.runInWriteLock(RealValueStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (!RealValueStore.this.file.exists() || RealValueStore.this.file.delete()) {
                            completableEmitter.onComplete();
                            RealValueStore.this.updateSubject.onNext(ValueStore.ValueUpdate.empty());
                            return;
                        }
                        throw new IOException("Clear operation on store failed.");
                    }
                });
            }
        });
    }

    public void clear() {
        clear(Schedulers.io());
    }

    public void clear(@NonNull Scheduler scheduler) {
        Utils.assertNotNull(scheduler, "scheduler");
        observeClear().subscribeOn(scheduler).subscribe();
    }
}
