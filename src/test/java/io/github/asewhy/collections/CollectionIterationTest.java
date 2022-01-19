package io.github.asewhy.collections;

import io.github.asewhy.collections.base.iterators.PageIterator;
import io.github.asewhy.collections.datasets.TestPageDataset;
import io.github.asewhy.collections.datasets.TestRangeDataset;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CollectionIterationTest {
    protected Random random = new Random();

    /**
     * Тест генерирует карту, и грубо говоря делает её плоской, тест страничного итератора
     */
    @Test
    public void collectionPageTest() {
        var pages = new HashMap<Integer, List<Integer>>();
        var t = 0;

        for(var i = 0; i < 10 + random.nextInt(20); i++) {
            var sample = new ArrayList<Integer>();

            for(var c = 0; c < 500 + random.nextInt(1000); c++) {
                sample.add(++t);
            }

            pages.put(i, sample);
        }

        var iterator = PageIterator.of(new TestPageDataset<>(pages));
        var last = 0;

        for (var current: iterator) {
            last = current;
        }

        Assert.assertEquals(t, last);
    }

    /**
     * Тест генерирует карту, и грубо говоря делает её плоской, тест итератора диапазона
     */
    @Test
    public void collectionRangeTest() {
        var sample = new ArrayList<Integer>();
        var t = 0;

        for(var i = 0; i < 1000 + random.nextInt(20000); i++) {
            sample.add(++t);
        }

        var iterator = PageIterator.of(new TestRangeDataset<>(sample));
        var last = 0;

        for (var current: iterator) {
            last = current;
        }

        Assert.assertEquals(t, last);
    }
}
