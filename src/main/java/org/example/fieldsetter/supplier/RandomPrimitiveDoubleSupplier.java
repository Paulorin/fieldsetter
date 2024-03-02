package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.DoubleSupplier;

@AllArgsConstructor
public class RandomPrimitiveDoubleSupplier implements DoubleSupplier {
    private final static double MIN_ZERO_OFFSET = 0.00001;
    private final static double MAX_VALUE = 1000.0;
    private final Random random;

    @Override
    public double getAsDouble() {
        double randomValue = random.nextDouble() * MAX_VALUE;
        if(randomValue < MIN_ZERO_OFFSET) {
            randomValue += MIN_ZERO_OFFSET;
        }
        return random.nextBoolean() ? randomValue : -randomValue;
    }
}
