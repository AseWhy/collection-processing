package io.github.asewhy.collections.base.iterators;

import io.github.asewhy.collections.base.Datasource;
import io.github.asewhy.collections.support.iIteratorProvider;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unused")
public class ChunkIterator<T> implements iIteratorProvider<Collection<T>> {
    private final Datasource<?, T> source;
    private Collection<T> chunk;

    private int marker;

    protected ChunkIterator(Datasource<?, T> source) {
        this.source = source;
        this.marker = 0;
    }

    @Override
    public boolean hasNext() {
        this.chunk = source.getNextSample(marker, ++marker);

        if(this.chunk == null) {
            this.chunk = new ArrayList<>();
        }

        return this.chunk.size() > 0;
    }

    @Override
    public Collection<T> next() {
        return chunk;
    }

    public static <T> ChunkIterator<T> of(Datasource<?, T> source) {
        return new ChunkIterator<>(source);
    }
}
