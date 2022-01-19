package io.github.asewhy.collections.support;

@FunctionalInterface
public interface iUnsafeSupplier<R> {
    R get() throws Exception;
}
