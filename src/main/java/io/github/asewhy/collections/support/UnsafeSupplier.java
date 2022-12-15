package io.github.asewhy.collections.support;

@FunctionalInterface
public interface UnsafeSupplier<R> {
    R get() throws Exception;
}
