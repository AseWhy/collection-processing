package io.github.asewhy.collections;

import io.github.asewhy.collections.base.UnsafeDatasource;
import io.github.asewhy.collections.support.iUnsafeBiFunction;
import io.github.asewhy.collections.support.iUnsafeFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Future;

@SuppressWarnings("unused")
public class CallbackFutureRangeUnsafeSource<T> extends UnsafeDatasource<iUnsafeBiFunction<Integer, Integer, Future<Collection<T>>>, T> {
    protected Future<Collection<T>> await;

    public CallbackFutureRangeUnsafeSource(iUnsafeBiFunction<Integer, Integer, Future<Collection<T>>> function, Integer retryCount) {
        super(function, retryCount);
    }

    private void updateSource(Integer from, Integer to) throws Exception {
        this.await = this.source.apply(from, to);
    }

    @Override
    public Collection<T> getNextUnsafeSample(Integer from, Integer to) throws Exception {
        var result = (Collection<T>) null;

        if (await != null) {
            result = this.await.get();
        }

        if(result == null) {
            result = new ArrayList<>();
        }

        if(result.size() == 0) {
            this.await = null;
        } else {
            updateSource(from, to);
        }

        return result;
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull CallbackFutureRangeUnsafeSource<T> of(iUnsafeBiFunction<Integer, Integer, Future<Collection<T>>> function, Integer retryCount) {
        return new CallbackFutureRangeUnsafeSource<>(function, retryCount);
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackFutureRangeUnsafeSource<T> of(iUnsafeBiFunction<Integer, Integer, Future<Collection<T>>> function) {
        return new CallbackFutureRangeUnsafeSource<>(function, defaultRetryCount);
    }
}
