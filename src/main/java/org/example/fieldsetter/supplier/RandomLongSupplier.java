package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class RandomLongSupplier implements Supplier<Long> {
	private final Random random;

	@Override
	public Long get() {
		long value = random.nextLong();
		return random.nextBoolean() ? value : - value - 1;
	}
}
