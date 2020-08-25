package au.com.gridstone.rxstore;

import au.com.gridstone.rxstore.ListStore;
import com.alipay.sdk.packet.d;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

final class RealListStore<T> implements ListStore<T> {
    /* access modifiers changed from: private */
    public final Converter converter;
    /* access modifiers changed from: private */
    public final File file;
    /* access modifiers changed from: private */
    public final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /* access modifiers changed from: private */
    public final Type type;
    /* access modifiers changed from: private */
    public final PublishSubject<List<T>> updateSubject = PublishSubject.create();

    RealListStore(@NonNull File file2, @NonNull Converter converter2, @NonNull Type type2) {
        Utils.assertNotNull(file2, "file");
        Utils.assertNotNull(converter2, "converter");
        Utils.assertNotNull(type2, d.p);
        this.file = file2;
        this.converter = converter2;
        this.type = new ListType(type2);
    }

    @NonNull
    public Single<List<T>> get() {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            public void subscribe(final SingleEmitter<List<T>> singleEmitter) throws Exception {
                Utils.runInReadLock(RealListStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (!RealListStore.this.file.exists()) {
                            singleEmitter.onSuccess(Collections.emptyList());
                            return;
                        }
                        List list = (List) RealListStore.this.converter.read(RealListStore.this.file, RealListStore.this.type);
                        if (list == null) {
                            list = Collections.emptyList();
                        }
                        singleEmitter.onSuccess(list);
                    }
                });
            }
        });
    }

    @NonNull
    public List<T> blockingGet() {
        return (List) get().blockingGet();
    }

    @NonNull
    public Single<List<T>> observePut(@NonNull final List<T> list) {
        Utils.assertNotNull(list, "list");
        return Single.create(new SingleOnSubscribe<List<T>>() {
            public void subscribe(final SingleEmitter<List<T>> singleEmitter) throws Exception {
                Utils.runInWriteLock(RealListStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (RealListStore.this.file.exists() || RealListStore.this.file.createNewFile()) {
                            Utils.converterWrite(list, RealListStore.this.converter, RealListStore.this.type, RealListStore.this.file);
                            singleEmitter.onSuccess(list);
                            RealListStore.this.updateSubject.onNext(list);
                            return;
                        }
                        throw new IOException("Could not create file for store.");
                    }
                });
            }
        });
    }

    public void put(@NonNull List<T> list) {
        put(list, Schedulers.io());
    }

    public void put(@NonNull List<T> list, @NonNull Scheduler scheduler) {
        Utils.assertNotNull(scheduler, "scheduler");
        observePut(list).subscribeOn(scheduler).subscribe();
    }

    @NonNull
    public Observable<List<T>> observe() {
        return this.updateSubject.startWith(get().toObservable());
    }

    @NonNull
    public Single<List<T>> observeClear() {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            public void subscribe(final SingleEmitter<List<T>> singleEmitter) throws Exception {
                Utils.runInWriteLock(RealListStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (!RealListStore.this.file.exists() || RealListStore.this.file.delete()) {
                            singleEmitter.onSuccess(Collections.emptyList());
                            RealListStore.this.updateSubject.onNext(Collections.emptyList());
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

    @NonNull
    public Single<List<T>> observeAdd(@NonNull final T t) {
        Utils.assertNotNull(t, "value");
        return Single.create(new SingleOnSubscribe<List<T>>() {
            public void subscribe(final SingleEmitter<List<T>> singleEmitter) throws Exception {
                Utils.runInWriteLock(RealListStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (RealListStore.this.file.exists() || RealListStore.this.file.createNewFile()) {
                            List list = (List) RealListStore.this.converter.read(RealListStore.this.file, RealListStore.this.type);
                            if (list == null) {
                                list = Collections.emptyList();
                            }
                            ArrayList arrayList = new ArrayList(list.size() + 1);
                            arrayList.addAll(list);
                            arrayList.add(t);
                            Utils.converterWrite(arrayList, RealListStore.this.converter, RealListStore.this.type, RealListStore.this.file);
                            singleEmitter.onSuccess(arrayList);
                            RealListStore.this.updateSubject.onNext(arrayList);
                            return;
                        }
                        throw new IOException("Could not create file for store.");
                    }
                });
            }
        });
    }

    public void add(@NonNull T t) {
        add(t, Schedulers.io());
    }

    public void add(@NonNull T t, @NonNull Scheduler scheduler) {
        Utils.assertNotNull(scheduler, "scheduler");
        observeAdd(t).subscribeOn(scheduler).subscribe();
    }

    @NonNull
    public Single<List<T>> observeRemove(@NonNull final ListStore.PredicateFunc<T> predicateFunc) {
        Utils.assertNotNull(predicateFunc, "predicateFunc");
        return Single.create(new SingleOnSubscribe<List<T>>() {
            public void subscribe(final SingleEmitter<List<T>> singleEmitter) throws Exception {
                Utils.runInWriteLock(RealListStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (!RealListStore.this.file.exists()) {
                            singleEmitter.onSuccess(Collections.emptyList());
                            return;
                        }
                        List list = (List) RealListStore.this.converter.read(RealListStore.this.file, RealListStore.this.type);
                        if (list == null) {
                            list = Collections.emptyList();
                        }
                        int i = 0;
                        while (true) {
                            if (i >= list.size()) {
                                i = -1;
                                break;
                            } else if (predicateFunc.test(list.get(i))) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        ArrayList arrayList = new ArrayList(list);
                        if (i != -1) {
                            arrayList.remove(i);
                            Utils.converterWrite(arrayList, RealListStore.this.converter, RealListStore.this.type, RealListStore.this.file);
                        }
                        singleEmitter.onSuccess(arrayList);
                        RealListStore.this.updateSubject.onNext(arrayList);
                    }
                });
            }
        });
    }

    public void remove(@NonNull ListStore.PredicateFunc<T> predicateFunc) {
        remove(Schedulers.io(), predicateFunc);
    }

    public void remove(@NonNull Scheduler scheduler, @NonNull ListStore.PredicateFunc<T> predicateFunc) {
        Utils.assertNotNull(scheduler, "scheduler");
        observeRemove(predicateFunc).subscribeOn(scheduler).subscribe();
    }

    @NonNull
    public Single<List<T>> observeRemove(@NonNull final T t) {
        Utils.assertNotNull(t, "value");
        return observeRemove(new ListStore.PredicateFunc<T>() {
            public boolean test(T t) {
                return t.equals(t);
            }
        });
    }

    public void remove(@NonNull T t) {
        remove(t, Schedulers.io());
    }

    public void remove(@NonNull T t, @NonNull Scheduler scheduler) {
        Utils.assertNotNull(scheduler, "scheduler");
        observeRemove(t).subscribeOn(scheduler).subscribe();
    }

    @NonNull
    public Single<List<T>> observeRemove(final int i) {
        return Single.create(new SingleOnSubscribe<List<T>>() {
            public void subscribe(final SingleEmitter<List<T>> singleEmitter) throws Exception {
                Utils.runInWriteLock(RealListStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        List list = (List) RealListStore.this.converter.read(RealListStore.this.file, RealListStore.this.type);
                        if (list == null) {
                            list = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(list);
                        arrayList.remove(i);
                        Utils.converterWrite(arrayList, RealListStore.this.converter, RealListStore.this.type, RealListStore.this.file);
                        singleEmitter.onSuccess(arrayList);
                        RealListStore.this.updateSubject.onNext(arrayList);
                    }
                });
            }
        });
    }

    public void remove(int i) {
        remove(i, Schedulers.io());
    }

    public void remove(int i, @NonNull Scheduler scheduler) {
        Utils.assertNotNull(scheduler, "scheduler");
        observeRemove(i).subscribeOn(scheduler).subscribe();
    }

    @NonNull
    public Single<List<T>> observeReplace(@NonNull final T t, @NonNull final ListStore.PredicateFunc<T> predicateFunc) {
        Utils.assertNotNull(t, "value");
        Utils.assertNotNull(predicateFunc, "predicateFunc");
        return Single.create(new SingleOnSubscribe<List<T>>() {
            public void subscribe(final SingleEmitter<List<T>> singleEmitter) throws Exception {
                Utils.runInWriteLock(RealListStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        if (!RealListStore.this.file.exists()) {
                            singleEmitter.onSuccess(Collections.emptyList());
                            return;
                        }
                        List list = (List) RealListStore.this.converter.read(RealListStore.this.file, RealListStore.this.type);
                        if (list == null) {
                            list = Collections.emptyList();
                        }
                        int i = 0;
                        while (true) {
                            if (i >= list.size()) {
                                i = -1;
                                break;
                            } else if (predicateFunc.test(list.get(i))) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        ArrayList arrayList = new ArrayList(list);
                        if (i != -1) {
                            arrayList.remove(i);
                            arrayList.add(i, t);
                            Utils.converterWrite(arrayList, RealListStore.this.converter, RealListStore.this.type, RealListStore.this.file);
                        }
                        singleEmitter.onSuccess(arrayList);
                        RealListStore.this.updateSubject.onNext(arrayList);
                    }
                });
            }
        });
    }

    public void replace(@NonNull T t, @NonNull ListStore.PredicateFunc<T> predicateFunc) {
        replace(t, Schedulers.io(), predicateFunc);
    }

    public void replace(@NonNull T t, @NonNull Scheduler scheduler, @NonNull ListStore.PredicateFunc<T> predicateFunc) {
        Utils.assertNotNull(scheduler, "scheduler");
        observeReplace(t, predicateFunc).subscribeOn(scheduler).subscribe();
    }

    @NonNull
    public Single<List<T>> observeAddOrReplace(@NonNull final T t, @NonNull final ListStore.PredicateFunc<T> predicateFunc) {
        Utils.assertNotNull(t, "value");
        Utils.assertNotNull(predicateFunc, "predicateFunc");
        return Single.create(new SingleOnSubscribe<List<T>>() {
            public void subscribe(final SingleEmitter<List<T>> singleEmitter) throws Exception {
                Utils.runInWriteLock(RealListStore.this.readWriteLock, new ThrowingRunnable() {
                    public void run() throws Exception {
                        int i;
                        if (RealListStore.this.file.exists() || RealListStore.this.file.createNewFile()) {
                            List list = (List) RealListStore.this.converter.read(RealListStore.this.file, RealListStore.this.type);
                            if (list == null) {
                                list = Collections.emptyList();
                            }
                            int i2 = 0;
                            while (true) {
                                if (i2 >= list.size()) {
                                    i2 = -1;
                                    break;
                                } else if (predicateFunc.test(list.get(i2))) {
                                    break;
                                } else {
                                    i2++;
                                }
                            }
                            if (i2 == -1) {
                                i = list.size() + 1;
                            } else {
                                i = list.size();
                            }
                            ArrayList arrayList = new ArrayList(i);
                            arrayList.addAll(list);
                            if (i2 == -1) {
                                arrayList.add(t);
                            } else {
                                arrayList.remove(i2);
                                arrayList.add(i2, t);
                            }
                            Utils.converterWrite(arrayList, RealListStore.this.converter, RealListStore.this.type, RealListStore.this.file);
                            singleEmitter.onSuccess(arrayList);
                            RealListStore.this.updateSubject.onNext(arrayList);
                            return;
                        }
                        throw new IOException("Could not create store.");
                    }
                });
            }
        });
    }

    public void addOrReplace(@NonNull T t, @NonNull ListStore.PredicateFunc<T> predicateFunc) {
        addOrReplace(t, Schedulers.io(), predicateFunc);
    }

    public void addOrReplace(@NonNull T t, @NonNull Scheduler scheduler, @NonNull ListStore.PredicateFunc<T> predicateFunc) {
        Utils.assertNotNull(scheduler, "scheduler");
        observeAddOrReplace(t, predicateFunc).subscribeOn(scheduler).subscribe();
    }

    static final class ListType implements ParameterizedType {
        private final Type wrappedType;

        public Type getOwnerType() {
            return null;
        }

        ListType(Type type) {
            this.wrappedType = type;
        }

        public Type[] getActualTypeArguments() {
            return new Type[]{this.wrappedType};
        }

        public Type getRawType() {
            return List.class;
        }
    }
}
