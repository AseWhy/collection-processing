package io.github.asewhy.collections;

import io.github.asewhy.collections.base.UnsafeDatasource;
import io.github.asewhy.collections.support.iUnsafeBiFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@SuppressWarnings("unused")
public class CallbackRangeUnsafeSource<T> extends UnsafeDatasource<iUnsafeBiFunction<Integer, Integer, Collection<T>>, T> {
    public CallbackRangeUnsafeSource(iUnsafeBiFunction<Integer, Integer, Collection<T>> function, Integer retryCount) {
        super(function, retryCount);
    }

    @Override
    public Collection<T> getNextUnsafeSample(Integer from, Integer to) throws Exception {
        return source.apply(from, to);
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull CallbackRangeUnsafeSource<T> of(iUnsafeBiFunction<Integer, Integer, Collection<T>> function, Integer retryCount) {
        return new CallbackRangeUnsafeSource<>(function, retryCount);
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackRangeUnsafeSource<T> of(iUnsafeBiFunction<Integer, Integer, Collection<T>> function) {
        return new CallbackRangeUnsafeSource<>(function, defaultRetryCount);
    }
}
