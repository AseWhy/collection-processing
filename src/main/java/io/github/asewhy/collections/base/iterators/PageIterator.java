package io.github.asewhy.collections.base.iterators;

import io.github.asewhy.collections.base.Datasource;
import io.github.asewhy.collections.support.IteratorProvider;

import java.util.LinkedList;

@SuppressWarnings("unused")
public class PageIterator<T> implements IteratorProvider<T> {
    private final Datasource<?, T> source;
    private final LinkedList<T> chunk;

    private int marker;

    protected PageIterator(Datasource<?, T> source) {
        this.source = source;
        this.marker = 0;
        this.chunk = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        if(chunk.size() > 0) {
            return true;
        } else {
            var sample = source.getNextSample(marker, ++marker);

            if(sample != null) {
                chunk.addAll(sample);
            }

            return chunk.size() > 0;
        }
    }

    @Override
    public T next() {
        return chunk.pop();
    }

    public static <T> PageIterator<T> of(Datasource<?, T> source) {
        return new PageIterator<>(source);
    }
}
