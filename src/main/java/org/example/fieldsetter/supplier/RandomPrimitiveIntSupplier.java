package org.example.fieldsetter.supplier;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.function.IntSupplier;

/**
 * Supplies primitive int value in range Integer.MIN_VALUE : Integer.MAX_VALUE
 * return random int value in range Integer.MIN_VALUE : Integer.MAX_VALUE
 */
@RequiredArgsConstructor
public class RandomPrimitiveIntSupplier implements IntSupplier {
	private final Random random;

	@Override
	public int getAsInt() {
		int value = random.nextInt();
		value = random.nextBoolean() ? value : - value - 1;
		return value;
	}
}
