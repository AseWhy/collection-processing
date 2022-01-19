package io.github.asewhy.collections;

import io.github.asewhy.collections.base.Datasource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

@SuppressWarnings("unused")
public class CallbackFutureSource<T> extends Datasource<Function<Integer, Future<Collection<T>>>, T> {
    protected Future<Collection<T>> await;

    public CallbackFutureSource(Function<Integer, Future<Collection<T>>> function) {
        super(function);
        getNextSample(0, 0);
    }

    private void updateSource(Integer page) {
        this.await = this.source.apply(page);
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
            updateSource(to);
        }

        return result;
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackFutureSource<T> of(Function<Integer, Future<Collection<T>>> function) {
        return new CallbackFutureSource<>(function);
    }
}
