package io.github.asewhy.collections.factory;

import io.github.asewhy.collections.*;
import io.github.asewhy.collections.base.iterators.ChunkIterator;
import io.github.asewhy.collections.base.iterators.PageIterator;
import io.github.asewhy.collections.base.iterators.RangeIterator;
import io.github.asewhy.collections.support.iUnsafeBiFunction;
import io.github.asewhy.collections.support.iUnsafeFunction;
import io.github.asewhy.collections.support.iUnsafeSupplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.Future;

public class Unsafe {
    //
    // Получить ненадежные данные
    //
    @Contract("_ -> new")
    public static <T> @NotNull UnsafeData<T> newUnsafeData(iUnsafeSupplier<T> receiver) {
        return UnsafeData.of(receiver);
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull UnsafeData<T> newUnsafeData(iUnsafeSupplier<T> receiver, Integer retryCount) {
        return UnsafeData.of(receiver, retryCount);
    }

    @Contract("_ -> new")
    public static <T> @NotNull T getUnsafeData(iUnsafeSupplier<T> receiver) {
        return UnsafeData.of(receiver).get();
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull T getUnsafeData(iUnsafeSupplier<T> receiver, Integer retryCount) {
        return UnsafeData.of(receiver, retryCount).get();
    }

    //
    // Chunk
    //

    @Contract("_ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackPageUnsafeChunkIterator(iUnsafeFunction<Integer, Collection<T>> function) {
        return ChunkIterator.of(CallbackPageUnsafeSource.of(function));
    }

    @Contract("_ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackQueueUnsafeChunkIterator(iUnsafeSupplier<Collection<T>> supplier) {
        return ChunkIterator.of(CallbackQueueUnsafeSource.of(supplier));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackPageUnsafeChunkIterator(iUnsafeFunction<Integer, Collection<T>> function, Integer tryCount) {
        return ChunkIterator.of(CallbackPageUnsafeSource.of(function, tryCount));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackQueueUnsafeChunkIterator(iUnsafeSupplier<Collection<T>> supplier, Integer tryCount) {
        return ChunkIterator.of(CallbackQueueUnsafeSource.of(supplier, tryCount));
    }

    @Contract("_ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackFutureUnsafeChunkIterator(iUnsafeFunction<Integer, Future<Collection<T>>> function) {
        return ChunkIterator.of(CallbackFutureUnsafeSource.of(function));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull ChunkIterator<T> newCallbackFutureUnsafeChunkIterator(iUnsafeFunction<Integer, Future<Collection<T>>> function, Integer tryCount) {
        return ChunkIterator.of(CallbackFutureUnsafeSource.of(function, tryCount));
    }

    @Contract("_, _ -> new")
    public static <T, K> @NotNull ChunkIterator<T> newSubpageUnsafeChunkIterator(Iterable<K> subSource, iUnsafeFunction<K, Collection<T>> function) {
        return ChunkIterator.of(SubpageUnsafeSource.of(subSource, function));
    }

    @Contract("_, _, _ -> new")
    public static <T, K> @NotNull ChunkIterator<T> newSubpageUnsafeChunkIterator(Iterable<K> subSource, iUnsafeFunction<K, Collection<T>> function, Integer tryCount) {
        return ChunkIterator.of(SubpageUnsafeSource.of(subSource, function, tryCount));
    }

    //
    // Page
    //

    @Contract("_ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackPageUnsafePageIterator(iUnsafeFunction<Integer, Collection<T>> function) {
        return PageIterator.of(CallbackPageUnsafeSource.of(function));
    }

    @Contract("_ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackQueueUnsafePageIterator(iUnsafeSupplier<Collection<T>> supplier) {
        return PageIterator.of(CallbackQueueUnsafeSource.of(supplier));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackPageUnsafePageIterator(iUnsafeFunction<Integer, Collection<T>> function, Integer tryCount) {
        return PageIterator.of(CallbackPageUnsafeSource.of(function, tryCount));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackQueueUnsafePageIterator(iUnsafeSupplier<Collection<T>> supplier, Integer tryCount) {
        return PageIterator.of(CallbackQueueUnsafeSource.of(supplier, tryCount));
    }

    @Contract("_ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackFutureUnsafePageIterator(iUnsafeFunction<Integer, Future<Collection<T>>> function) {
        return PageIterator.of(CallbackFutureUnsafeSource.of(function));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull PageIterator<T> newCallbackFutureUnsafePageIterator(iUnsafeFunction<Integer, Future<Collection<T>>> function, Integer tryCount) {
        return PageIterator.of(CallbackFutureUnsafeSource.of(function, tryCount));
    }

    @Contract("_, _ -> new")
    public static <T, K> @NotNull PageIterator<T> newSubpageUnsafePageIterator(Iterable<K> subSource, iUnsafeFunction<K, Collection<T>> function) {
        return PageIterator.of(SubpageUnsafeSource.of(subSource, function));
    }

    @Contract("_, _, _ -> new")
    public static <T, K> @NotNull PageIterator<T> newSubpageUnsafePageIterator(Iterable<K> subSource, iUnsafeFunction<K, Collection<T>> function, Integer tryCount) {
        return PageIterator.of(SubpageUnsafeSource.of(subSource, function, tryCount));
    }

    //
    // Range
    //

    @Contract("_ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackRangeUnsafeRangeIterator(iUnsafeBiFunction<Integer, Integer, Collection<T>> function) {
        return RangeIterator.of(CallbackRangeUnsafeSource.of(function));
    }

    @Contract("_ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackQueueUnsafeRangeIterator(iUnsafeSupplier<Collection<T>> function) {
        return RangeIterator.of(CallbackQueueUnsafeSource.of(function));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackRangeUnsafeRangeIterator(iUnsafeBiFunction<Integer, Integer, Collection<T>> function, Integer tryCount) {
        return RangeIterator.of(CallbackRangeUnsafeSource.of(function, tryCount));
    }

    @Contract("_ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackFutureUnsafeRangeIterator(iUnsafeBiFunction<Integer, Integer, Future<Collection<T>>> function) {
        return RangeIterator.of(CallbackFutureRangeUnsafeSource.of(function));
    }

    @Contract("_, _ -> new")
    public static <T> @NotNull RangeIterator<T> newCallbackFutureUnsafeRangeIterator(iUnsafeBiFunction<Integer, Integer, Future<Collection<T>>> function, Integer tryCount) {
        return RangeIterator.of(CallbackFutureRangeUnsafeSource.of(function, tryCount));
    }
}
