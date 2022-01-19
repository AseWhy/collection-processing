package io.github.asewhy.collections.base;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public abstract class UnsafeDatasource<S, T> extends Datasource<S, T> {
    /**
     * Стандарное для всех количество попыток запроса перед трансляцией исключения
     */
    public static final Integer defaultRetryCount = 5;

    //
    // Количество попыток запроса перед трансляцией исключения
    //
    protected final Integer tryCount;

    /**
     * Повторяющийся при ошибках источник данных (при ошибке запроса запрос повторится нужное количество раз)
     *
     * @param source источник данных
     * @param retryCount количество повторений при ошибке
     */
    public UnsafeDatasource(S source, Integer retryCount) {
        super(source);

        if(retryCount < 0) {
            retryCount = 0;
        }

        this.tryCount = retryCount;
    }

    /**
     * Получить следующую успешную выборку (при не успешном выполнении призойдет повторение)
     *
     * @param from начальная точка выборки
     * @param to конечная точка выборки
     * @return коллекция полученная выборкой
     */
    public abstract Collection<T> getNextUnsafeSample(Integer from, Integer to) throws Exception;

    public final @Nullable Collection<T> getNextSample(Integer from, Integer to) {
        var i = 0;

        while(i < tryCount) {
            try {
                return getNextUnsafeSample(from, to);
            } catch (Exception exception) {
                if (i + 1 >= tryCount) {
                    throw new RuntimeException(exception);
                } else {
                    i++;
                }
            }
        }

        throw new RuntimeException("Uncompleted retry queue. " + i + " retries of " + tryCount);
    }
}
