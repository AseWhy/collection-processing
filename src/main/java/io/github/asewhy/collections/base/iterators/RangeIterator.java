package io.github.asewhy.collections.base.iterators;

import io.github.asewhy.collections.base.Datasource;
import io.github.asewhy.collections.support.iIteratorProvider;

import java.util.LinkedList;

@SuppressWarnings("unused")
public class RangeIterator<T> implements iIteratorProvider<T> {
    private final Datasource<?, T> source;
    private final Integer step;
    private final LinkedList<T> chunk;

    private Integer marker;

    protected RangeIterator(Datasource<?, T> source, Integer step) {
        this.source = source;
        this.step = step;
        this.marker = 0;
        this.chunk = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        if(chunk.size() > 0) {
            return true;
        } else {
            var sample = source.getNextSample(marker, marker + step);

            if(sample != null) {
                chunk.addAll(sample);

                marker += sample.size();
            }

            return chunk.size() > 0;
        }
    }

    @Override
    public T next() {
        return chunk.pop();
    }

    public static <T> RangeIterator<T> of(Datasource<?, T> source, Integer step) {
        return new RangeIterator<>(source, step);
    }

    public static <T> RangeIterator<T> of(Datasource<?, T> source) {
        return new RangeIterator<>(source, 500);
    }
}
