package io.github.asewhy.collections;

import io.github.asewhy.collections.base.Datasource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Function;

@SuppressWarnings("unused")
public class CallbackPageSource<T> extends Datasource<Function<Integer, Collection<T>>, T> {
    public CallbackPageSource(Function<Integer, Collection<T>> function) {
        super(function);
    }

    @Override
    public Collection<T> getNextSample(Integer from, Integer to) {
        return source.apply(from);
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackPageSource<T> of(Function<Integer, Collection<T>> function) {
        return new CallbackPageSource<>(function);
    }
}
