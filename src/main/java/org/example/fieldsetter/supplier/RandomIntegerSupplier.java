package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class RandomIntegerSupplier implements Supplier<Integer> {
    private final Random random;

    @Override
    public Integer get() {
        int value = random.nextInt();
        value = random.nextBoolean() ? value : - value - 1;
        return value;
    }
}
