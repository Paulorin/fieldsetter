package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.LongSupplier;

/**
 * Supplies random primitive long value in range Long.MIN_VALUE : Long.MAX_VALUE
 * return random long value in range Long.MIN_VALUE : Long.MAX_VALUE
 */
@AllArgsConstructor
public class RandomPrimitiveLongSupplier implements LongSupplier {
	private final Random random;
	@Override
	public long getAsLong() {
		long value = random.nextLong();
		return random.nextBoolean() ? value : - value - 1;
	}
}
