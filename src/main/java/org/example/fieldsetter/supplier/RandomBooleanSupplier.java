package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class RandomBooleanSupplier implements Supplier<Boolean> {
    private final Random random;

    @Override
    public Boolean get() {
        return random.nextBoolean();
    }
}
