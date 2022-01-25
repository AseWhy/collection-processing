package io.github.asewhy.collections.factory;

import io.github.asewhy.collections.*;
import io.github.asewhy.collections.base.iterators.ChunkIterator;
import io.github.asewhy.collections.base.iterators.NestedChunkIterator;
import io.github.asewhy.collections.base.iterators.PageIterator;
import io.github.asewhy.collections.base.iterators.RangeIterator;
import io.github.asewhy.collections.support.iUnsafeBiFunction;
import io.github.asewhy.collections.support.iUnsafeFunction;
import io.github.asewhy.collections.support.iUnsafeSupplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class Iterator {
    //
    // Итерирует выборками
    //

    @Contract("_ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackPageChunkIterator(Function<Integer, Collection<T>> function) {
        return ChunkIterator.of(CallbackPageSource.of(function));
    }

    @Contract("_ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackQueueChunkIterator(Supplier<Collection<T>> supplier) {
        return ChunkIterator.of(CallbackQueueSource.of(supplier));
    }

    @Contract("_ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackFutureChunkIterator(Function<Integer, Future<Collection<T>>> function) {
        return ChunkIterator.of(CallbackFutureSource.of(function));
    }

    @Contract("_, _ -> new")
    public static <T, K> @NotNull ChunkIterator<T> newSubpageChunkIterator(Iterable<K> subSource, Function<K, Collection<T>> function) {
        return ChunkIterator.of(SubpageSource.of(subSource, function));
    }

    //
    // Итерирует потоком (страницы)
    //

    @Contract("_ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackPagePageIterator(Function<Integer, Collection<T>> function) {
        return PageIterator.of(CallbackPageSource.of(function));
    }

    @Contract("_ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackQueuePageIterator(Supplier<Collection<T>> supplier) {
        return PageIterator.of(CallbackQueueSource.of(supplier));
    }

    @Contract("_ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackFuturePageIterator(Function<Integer, Future<Collection<T>>> function) {
        return PageIterator.of(CallbackFutureSource.of(function));
    }

    @Contract("_, _ -> new")
    public static <T, K> @NotNull PageIterator<T> newSubpagePageIterator(Iterable<K> subSource, Function<K, Collection<T>> function) {
        return PageIterator.of(SubpageSource.of(subSource, function));
    }

    //
    // Итерируемые потоки (диапазон)
    //

    @Contract("_ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackQueueRangeIterator(Supplier<Collection<T>> function) {
        return RangeIterator.of(CallbackQueueSource.of(function));
    }

    @Contract("_ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackRangeRangeIterator(BiFunction<Integer, Integer, Collection<T>> function) {
        return RangeIterator.of(CallbackRangeSource.of(function));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackQueueUnsafeRangeIterator(iUnsafeSupplier<Collection<T>> supplier, Integer tryCount) {
        return RangeIterator.of(CallbackQueueUnsafeSource.of(supplier, tryCount));
    }

    @Contract("_ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackFutureRangeIterator(BiFunction<Integer, Integer, Future<Collection<T>>> function) {
        return RangeIterator.of(CallbackFutureRangeSource.of(function));
    }

    //
    // Итерирует вложенные итераторы
    //

    @Contract("_ -> new")
    public static <T> @NotNull NestedChunkIterator<T> newNestedNestedIterator(Collection<Iterable<Collection<T>>> iterables) {
        return NestedChunkIterator.of(NestedPageSource.of(iterables));
    }
}
