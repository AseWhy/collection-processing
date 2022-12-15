package io.github.asewhy.collections.support;

@FunctionalInterface
public interface UnsafeBiFunction<T, S, R> {
    R apply(T arg1, S arg2) throws Exception;
}
