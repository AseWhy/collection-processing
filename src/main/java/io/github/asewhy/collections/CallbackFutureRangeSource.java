package io.github.asewhy.collections;

import io.github.asewhy.collections.base.Datasource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("unused")
public class CallbackFutureRangeSource<T> extends Datasource<BiFunction<Integer, Integer, Future<Collection<T>>>, T> {
    protected Future<Collection<T>> await;

    public CallbackFutureRangeSource(BiFunction<Integer, Integer, Future<Collection<T>>> function) {
        super(function);
    }

    private void updateSource(Integer from, Integer to) {
        this.await = this.source.apply(from, to);
    }

    @Override
    public Collection<T> getNextSample(Integer from, Integer to) {
        var result = (Collection<T>) null;

        if (await != null) {
            try {
                result = this.await.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
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

    @Contract("_ -> new")
    public static <T> @NotNull CallbackFutureRangeSource<T> of(BiFunction<Integer, Integer, Future<Collection<T>>> function) {
        return new CallbackFutureRangeSource<>(function);
    }
}
