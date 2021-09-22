package com.kosalaam.api.common;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionFunction<T, R> {

    R apply(T r) throws Exception;

    static <T, R> Function<T, R> wrapper(ExceptionFunction<T, R> f) {
        return (T r) -> {
            try {
                return f.apply(r);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
