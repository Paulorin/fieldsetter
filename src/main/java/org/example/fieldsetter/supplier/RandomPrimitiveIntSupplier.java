package org.example.fieldsetter.supplier;

import java.util.Random;
import java.util.function.IntSupplier;

/**
 * Supplies primitive int value in range Integer.MIN_VALUE : Integer.MAX_VALUE
 * return random int value in range Integer.MIN_VALUE : Integer.MAX_VALUE
 */
public class RandomPrimitiveIntSupplier implements IntSupplier {
	private final Random random;
	private final IntSupplier supplier;

	public RandomPrimitiveIntSupplier(Random random, int from, int to) {
		this.random = random;
		if(from >= 0 && to > 0 || from < 0 && to <= 0) {
			supplier = () -> this.random.nextInt(to - from + 1) + from;
		} else if(from < 0) {
			supplier = () -> this.random.nextInt(to ) - this.random.nextInt(-(from+1));
		} else {
			throw new IllegalArgumentException(String.format("to=%d has to be greater then from=%d", from, to));
		}
	}

	public RandomPrimitiveIntSupplier(Random random) {
		this(random, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public int getAsInt() {
		return supplier.getAsInt();
	}
}
