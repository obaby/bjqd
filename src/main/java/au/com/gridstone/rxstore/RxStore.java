package au.com.gridstone.rxstore;

import io.reactivex.annotations.NonNull;
import java.io.File;
import java.lang.reflect.Type;

public class RxStore {
    private RxStore() {
        throw new AssertionError("No instances.");
    }

    public static <T> ValueStore<T> value(@NonNull File file, @NonNull Converter converter, @NonNull Type type) {
        return new RealValueStore(file, converter, type);
    }

    public static <T> ListStore<T> list(@NonNull File file, @NonNull Converter converter, @NonNull Type type) {
        return new RealListStore(file, converter, type);
    }
}
