package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.FloatSupplier;

import java.util.Random;

@AllArgsConstructor
public class RandomPrimitiveFloatSupplier implements FloatSupplier {
    private final static float MIN_ZERO_OFFSET = 0.00001f;
    private final static float MAX_VALUE = 1000.0f;
    private final Random random;

    @Override
    public float getAsFloat() {
        float value = random.nextFloat() * MAX_VALUE;
        value = random.nextBoolean() ? value : -value;
        if(value - 0.0f < MIN_ZERO_OFFSET) {
            value += MIN_ZERO_OFFSET;
        }
        return value;
    }
}
