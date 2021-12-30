package io.github.asewhy.collections;

import io.github.asewhy.collections.base.Datasource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CallbackQueueSource<T> extends Datasource<Supplier<Collection<T>>, T> {
    public CallbackQueueSource(Supplier<Collection<T>> supplier) {
        super(supplier);
    }

    @Override
    public Collection<T> getNextSample(Integer from, Integer to) {
        return source.get();
    }

    @Contract("_ -> new")
    public static <T> @NotNull CallbackQueueSource<T> of(Supplier<Collection<T>> supplier) {
        return new CallbackQueueSource<>(supplier);
    }
}
