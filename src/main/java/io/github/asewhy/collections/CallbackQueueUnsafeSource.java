package io.github.asewhy.collections;

import io.github.asewhy.collections.base.UnsafeDatasource;
import io.github.asewhy.collections.support.UnsafeSupplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@SuppressWarnings("unused")
public class CallbackQueueUnsafeSource<T> extends UnsafeDatasource<UnsafeSupplier<Collection<T>>, T> {
    public CallbackQueueUnsafeSource(UnsafeSupplier<Collection<T>> supplier, Integer retryCount) {
        super(supplier, retryCount);
    }

    @Override
    public Collection<T> getNextUnsafeSample(Integer from, Integer to) throws Exception {
        return source.get();
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull CallbackQueueUnsafeSource<T> of(UnsafeSupplier<Collection<T>> supplier, Integer retryCount) {
        return new CallbackQueueUnsafeSource<>(supplier, retryCount);
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackQueueUnsafeSource<T> of(UnsafeSupplier<Collection<T>> supplier) {
        return new CallbackQueueUnsafeSource<>(supplier, IterableUtils.DEFAULT_RETRY_COUNT);
    }
}
