package io.github.asewhy.collections.base;

import java.util.Collection;

public abstract class Datasource<S, T> {
    protected final S source;

    public Datasource(S source) {
        this.source = source;
    }

    /**
     * Получить следующую выборку данных
     *
     * @param from позиция начала выборки
     * @param to позиция конца выборки
     * @return выборка
     */
    public abstract Collection<T> getNextSample(Integer from, Integer to);
}
