package io.github.asewhy.collections.support;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface iIteratorProvider<T> extends Iterator<T>, Iterable<T> {
    /**
     * Получить итератор (себя) из себя
     *
     * @return итератор (себя)
     */
    default Iterator<T> iterator() {
        return iIteratorProvider.this;
    }

    /**
     * Получить поток данных из этого итератора
     *
     * @return поток данных
     */
    default Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    /**
     * Получить параллельный поток данных из этого итератора
     *
     * @return параллельный поток данных
     */
    default Stream<T> parralelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }
}
