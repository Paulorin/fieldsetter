package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class RandomEnumSupplier implements Supplier<Object>{
    private final Random random;
    private final Object[] enums;

    @Override
    public Object get() {
        return enums[random.nextInt(enums.length)];
    }
}
