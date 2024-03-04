package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.lang.reflect.Array;
import java.util.function.Supplier;

@AllArgsConstructor
public class ArraySupplier<T> implements Supplier<T[]> {
    private final Supplier<T> supplier;
    private final Class<?> clazz;
    private final int length;

    @SuppressWarnings("unchecked")
    @Override
    public T[] get() {
        T[] array = (T[])Array.newInstance(clazz, length);
        for(int i = 0; i < length; i++){
            Array.set(array, i, supplier.get());
        }

        return array;
    }
}
