package au.com.gridstone.rxstore;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import java.io.File;
import java.lang.reflect.Type;

public interface Converter {
    @Nullable
    <T> T read(@NonNull File file, @NonNull Type type) throws ConverterException;

    <T> void write(@Nullable T t, @NonNull Type type, @NonNull File file) throws ConverterException;
}
