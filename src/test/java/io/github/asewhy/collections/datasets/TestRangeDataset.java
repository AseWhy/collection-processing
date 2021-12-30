package io.github.asewhy.collections.datasets;

import io.github.asewhy.collections.base.Datasource;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TestRangeDataset<T> extends Datasource<List<T>, T> {
    public TestRangeDataset(List<T> source) {
        super(source);
    }

    @Override
    public Collection<T> getNextSample(Integer from, Integer to) {
        if(to > source.size()) {
            to = source.size();
        }

        return this.source.subList(from, to);
    }
}
