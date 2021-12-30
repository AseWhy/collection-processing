package io.github.asewhy.collections.datasets;

import io.github.asewhy.collections.base.Datasource;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TestPageDataset<T> extends Datasource<Map<Integer, List<T>>, T> {
    public TestPageDataset(Map<Integer, List<T>> source) {
        super(source);
    }

    @Override
    public Collection<T> getNextSample(Integer page, Integer nextPage) {
        return this.source.get(page);
    }
}
