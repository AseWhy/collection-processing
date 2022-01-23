package io.github.asewhy.collections;

import io.github.asewhy.collections.base.Datasource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class NestedPageSource<T> extends Datasource<List<Iterator<Collection<T>>>, T> {
    public NestedPageSource(@NotNull Collection<Iterable<Collection<T>>> iterable) {
        super(iterable.stream().map(Iterable::iterator).collect(Collectors.toList()));
    }

    @Override
    public Collection<T> getNextSample(Integer from, Integer to) {
        var source = this.source.get(from);

        if(source == null) {
            return null;
        } else {
            if(source.hasNext()) {
                return source.next();
            } else {
                return null;
            }
        }
    }

    @Contract("_ -> new")
    public static <T> @NotNull NestedPageSource<T> of(@NotNull Collection<Iterable<Collection<T>>> iterable) {
        return new NestedPageSource<>(iterable);
    }
}
