package io.github.asewhy.collections;

import io.github.asewhy.collections.base.UnsafeDatasource;
import io.github.asewhy.collections.support.iUnsafeFunction;
import io.github.asewhy.collections.support.iUnsafeSupplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Function;

public class UnsafeData<T> {
    protected final iUnsafeSupplier<T> receiver;
    protected final Integer retryCount;

    public UnsafeData(iUnsafeSupplier<T> receiver, Integer retryCount) {
        this.receiver = receiver;
        this.retryCount = retryCount;
    }

    public T get() {
        var i = 0;

        while(i < retryCount) {
            try {
                return this.receiver.get();
            } catch (Exception exception) {
                if (i + 1 >= retryCount) {
                    throw new RuntimeException(exception);
                } else {
                    i++;
                }
            }
        }

        throw new RuntimeException("Uncompleted retry queue. " + i + " retries of " + retryCount);
    }

    @Contract("_ -> new")
    public static <T> @NotNull UnsafeData<T> of(@NotNull iUnsafeSupplier<T> supplier) {
        return new UnsafeData<>(supplier, IterableUtils.DEFAULT_RETRY_COUNT);
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull UnsafeData<T> of(@NotNull iUnsafeSupplier<T> supplier, Integer retryCount) {
        return new UnsafeData<>(supplier, retryCount);
    }
}
