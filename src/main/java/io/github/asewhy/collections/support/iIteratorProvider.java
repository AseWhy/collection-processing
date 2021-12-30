package io.github.asewhy.collections.support;

import java.util.Iterator;

public interface iIteratorProvider<T> extends Iterator<T> {
    default Iterable<T> iterable() {
        return () -> iIteratorProvider.this;
    }
}
