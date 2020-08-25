package au.com.gridstone.rxstore;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public interface ValueStore<T> {
    @Nullable
    T blockingGet();

    void clear();

    void clear(@NonNull Scheduler scheduler);

    @NonNull
    Maybe<T> get();

    @NonNull
    Observable<ValueUpdate<T>> observe();

    @NonNull
    Completable observeClear();

    @NonNull
    Single<T> observePut(@NonNull T t);

    void put(@NonNull T t);

    void put(@NonNull T t, @NonNull Scheduler scheduler);

    public static final class ValueUpdate<T> {
        private static final ValueUpdate EMPTY = new ValueUpdate((Object) null);
        public final boolean empty;
        @Nullable
        public final T value;

        ValueUpdate(@Nullable T t) {
            this.value = t;
            this.empty = t == null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ValueUpdate)) {
                return false;
            }
            ValueUpdate valueUpdate = (ValueUpdate) obj;
            if (this.empty) {
                return valueUpdate.empty;
            }
            return this.value.equals(valueUpdate.value);
        }

        public int hashCode() {
            return ((this.value == null ? 0 : this.value.hashCode()) + (this.empty ? 1 : 0)) * 37;
        }

        static <T> ValueUpdate<T> empty() {
            return EMPTY;
        }
    }
}
