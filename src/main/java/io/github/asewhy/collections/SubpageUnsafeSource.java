package io.github.asewhy.collections;

import io.github.asewhy.collections.base.UnsafeDatasource;
import io.github.asewhy.collections.support.iUnsafeFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

@SuppressWarnings("unused")
public class SubpageUnsafeSource<T, K> extends UnsafeDatasource<iUnsafeFunction<K, Collection<T>>, T> {
    private final Iterator<K> subSource;

    public SubpageUnsafeSource(@NotNull Iterable<K> subSource, iUnsafeFunction<K, Collection<T>> function, Integer retryCount) {
        super(function, retryCount);

        this.subSource = subSource.iterator();
    }

    @Override
    public Collection<T> getNextUnsafeSample(Integer from, Integer to) throws Exception {
        return source.apply(subSource.next());
    }

    @Contract("_, _, _ -> new")
    public static <T, K> @NotNull SubpageUnsafeSource<T, K> of(@NotNull Iterable<K> subSource, iUnsafeFunction<K, Collection<T>> function, Integer retryCount) {
        return new SubpageUnsafeSource<>(subSource, function, retryCount);
    }

    @Contract("_, _ -> new")
    public static <T, K> @NotNull SubpageUnsafeSource<T, K> of(@NotNull Iterable<K> subSource, iUnsafeFunction<K, Collection<T>> function) {
        return new SubpageUnsafeSource<>(subSource, function, IterableUtils.DEFAULT_RETRY_COUNT);
    }
}
