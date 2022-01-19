package io.github.asewhy.collections;

import io.github.asewhy.collections.base.UnsafeDatasource;
import io.github.asewhy.collections.support.iUnsafeFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@SuppressWarnings("unused")
public class CallbackPageUnsafeSource<T> extends UnsafeDatasource<iUnsafeFunction<Integer, Collection<T>>, T> {
    public CallbackPageUnsafeSource(iUnsafeFunction<Integer, Collection<T>> function, Integer retryCount) {
        super(function, retryCount);
    }

    @Override
    public Collection<T> getNextUnsafeSample(Integer from, Integer to) throws Exception {
        return source.apply(from);
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull CallbackPageUnsafeSource<T> of(iUnsafeFunction<Integer, Collection<T>> function, Integer retryCount) {
        return new CallbackPageUnsafeSource<>(function, retryCount);
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackPageUnsafeSource<T> of(iUnsafeFunction<Integer, Collection<T>> function) {
        return new CallbackPageUnsafeSource<>(function, defaultRetryCount);
    }
}
