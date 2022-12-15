package io.github.asewhy.collections;

import io.github.asewhy.collections.base.UnsafeDatasource;
import io.github.asewhy.collections.support.UnsafeBiFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@SuppressWarnings("unused")
public class CallbackRangeUnsafeSource<T> extends UnsafeDatasource<UnsafeBiFunction<Integer, Integer, Collection<T>>, T> {
    public CallbackRangeUnsafeSource(UnsafeBiFunction<Integer, Integer, Collection<T>> function, Integer retryCount) {
        super(function, retryCount);
    }

    @Override
    public Collection<T> getNextUnsafeSample(Integer from, Integer to) throws Exception {
        return source.apply(from, to);
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull CallbackRangeUnsafeSource<T> of(UnsafeBiFunction<Integer, Integer, Collection<T>> function, Integer retryCount) {
        return new CallbackRangeUnsafeSource<>(function, retryCount);
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackRangeUnsafeSource<T> of(UnsafeBiFunction<Integer, Integer, Collection<T>> function) {
        return new CallbackRangeUnsafeSource<>(function, IterableUtils.DEFAULT_RETRY_COUNT);
    }
}
