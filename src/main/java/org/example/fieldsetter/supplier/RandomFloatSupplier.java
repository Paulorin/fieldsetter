package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class RandomFloatSupplier implements Supplier<Float> {
	private final static float MIN_ZERO_OFFSET = 0.00001f;
	private final static float MAX_VALUE = 1000.0f;

	private final Random random;

	@Override
	public Float get() {
		float value = random.nextFloat() * MAX_VALUE;
		value = random.nextBoolean() ? value : -value;
		if(value - 0.0f < MIN_ZERO_OFFSET) {
			value += MIN_ZERO_OFFSET;
		}
		return value;
	}
}
