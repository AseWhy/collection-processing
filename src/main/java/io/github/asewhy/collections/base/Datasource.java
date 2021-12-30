package io.github.asewhy.collections.base;

import java.util.Collection;

public abstract class Datasource<S, T> {
    protected final S source;

    public Datasource(S source) {
        this.source = source;
    }

    public abstract Collection<T> getNextSample(Integer from, Integer to);
}
