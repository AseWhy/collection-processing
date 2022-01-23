package io.github.asewhy.collections.base.iterators;

import io.github.asewhy.collections.base.Datasource;
import io.github.asewhy.collections.support.iIteratorProvider;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("unused")
public class NestedChunkIterator<T> implements iIteratorProvider<Collection<T>> {
    private final Datasource<?, T> source;

    private Collection<T> chunk;

    private int iterator;

    protected NestedChunkIterator(Datasource<?, T> source) {
        this.source = source;
        this.iterator = 0;
        this.chunk = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        if(chunk.size() > 0) {
            return true;
        } else {
            var sample = source.getNextSample(this.iterator, this.iterator);

            if(sample != null) {
                sample = source.getNextSample(++this.iterator, this.iterator);

                if(sample == null) {
                    return false;
                } else {
                    chunk = sample;
                }
            }

            return chunk.size() > 0;
        }
    }

    @Override
    public Collection<T> next() {
        return chunk;
    }

    public static <T> NestedChunkIterator<T> of(Datasource<?, T> source) {
        return new NestedChunkIterator<>(source);
    }
}
