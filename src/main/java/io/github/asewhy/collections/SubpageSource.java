package io.github.asewhy.collections;

import io.github.asewhy.collections.base.Datasource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

@SuppressWarnings("unused")
public class SubpageSource<T, K> extends Datasource<Function<K, Collection<T>>, T> {
    private final Iterator<K> subSource;

    public SubpageSource(@NotNull Iterable<K> subSource, Function<K, Collection<T>> function) {
        super(function);

        this.subSource = subSource.iterator();
    }

    @Override
    public Collection<T> getNextSample(Integer from, Integer to) {
        return source.apply(subSource.next());
    }

    @Contract("_, _ -> new")
    public static <T, K> @NotNull SubpageSource<T, K> of(@NotNull Iterable<K> subSource, Function<K, Collection<T>> function) {
        return new SubpageSource<>(subSource, function);
    }
}
