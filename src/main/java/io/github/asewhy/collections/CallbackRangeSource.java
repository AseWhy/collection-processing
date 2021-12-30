package io.github.asewhy.collections;

import io.github.asewhy.collections.base.Datasource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CallbackRangeSource<T> extends Datasource<BiFunction<Integer, Integer, Collection<T>>, T> {
    public CallbackRangeSource(BiFunction<Integer, Integer, Collection<T>> function) {
        super(function);
    }

    @Override
    public Collection<T> getNextSample(Integer from, Integer to) {
        return source.apply(from, to);
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackRangeSource<T> of(BiFunction<Integer, Integer, Collection<T>> function) {
        return new CallbackRangeSource<>(function);
    }
}
