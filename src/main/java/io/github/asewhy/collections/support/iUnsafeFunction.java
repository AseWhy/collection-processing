package io.github.asewhy.collections.support;

@FunctionalInterface
public interface iUnsafeFunction<T, R> {
    R apply(T arg) throws Exception;
}
