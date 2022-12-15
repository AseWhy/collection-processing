package io.github.asewhy.collections;

import io.github.asewhy.collections.base.UnsafeDatasource;
import io.github.asewhy.collections.support.UnsafeFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Future;

@SuppressWarnings("unused")
public class CallbackFutureUnsafeSource<T> extends UnsafeDatasource<UnsafeFunction<Integer, Future<Collection<T>>>, T> {
    protected Future<Collection<T>> await;

    public CallbackFutureUnsafeSource(UnsafeFunction<Integer, Future<Collection<T>>> function, Integer retryCount) {
        super(function, retryCount);
        getNextSample(0, 0);
    }

    private void updateSource(Integer page) throws Exception {
        this.await = this.source.apply(page);
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
            updateSource(to);
        }

        return result;
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull CallbackFutureUnsafeSource<T> of(UnsafeFunction<Integer, Future<Collection<T>>> function, Integer retryCount) {
        return new CallbackFutureUnsafeSource<>(function, retryCount);
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackFutureUnsafeSource<T> of(UnsafeFunction<Integer, Future<Collection<T>>> function) {
        return new CallbackFutureUnsafeSource<>(function, IterableUtils.DEFAULT_RETRY_COUNT);
    }
}
