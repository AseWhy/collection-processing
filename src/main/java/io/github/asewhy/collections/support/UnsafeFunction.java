package io.github.asewhy.collections.support;

@FunctionalInterface
public interface UnsafeFunction<T, R> {
    R apply(T arg) throws Exception;
}
