package au.com.gridstone.rxstore;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import java.util.List;

public interface ListStore<T> {

    public interface PredicateFunc<T> {
        boolean test(@NonNull T t);
    }

    void add(@NonNull T t);

    void add(@NonNull T t, @NonNull Scheduler scheduler);

    void addOrReplace(@NonNull T t, @NonNull PredicateFunc<T> predicateFunc);

    void addOrReplace(@NonNull T t, @NonNull Scheduler scheduler, @NonNull PredicateFunc<T> predicateFunc);

    @NonNull
    List<T> blockingGet();

    void clear();

    void clear(@NonNull Scheduler scheduler);

    @NonNull
    Single<List<T>> get();

    @NonNull
    Observable<List<T>> observe();

    @NonNull
    Single<List<T>> observeAdd(@NonNull T t);

    @NonNull
    Single<List<T>> observeAddOrReplace(@NonNull T t, @NonNull PredicateFunc<T> predicateFunc);

    @NonNull
    Single<List<T>> observeClear();

    @NonNull
    Single<List<T>> observePut(@NonNull List<T> list);

    @NonNull
    Single<List<T>> observeRemove(int i);

    @NonNull
    Single<List<T>> observeRemove(@NonNull PredicateFunc<T> predicateFunc);

    @NonNull
    Single<List<T>> observeRemove(@NonNull T t);

    @NonNull
    Single<List<T>> observeReplace(@NonNull T t, @NonNull PredicateFunc<T> predicateFunc);

    void put(@NonNull List<T> list);

    void put(@NonNull List<T> list, @NonNull Scheduler scheduler);

    void remove(int i);

    void remove(int i, @NonNull Scheduler scheduler);

    void remove(@NonNull PredicateFunc<T> predicateFunc);

    void remove(@NonNull Scheduler scheduler, @NonNull PredicateFunc<T> predicateFunc);

    void remove(@NonNull T t);

    void remove(@NonNull T t, @NonNull Scheduler scheduler);

    void replace(@NonNull T t, @NonNull PredicateFunc<T> predicateFunc);

    void replace(@NonNull T t, @NonNull Scheduler scheduler, @NonNull PredicateFunc<T> predicateFunc);
}
