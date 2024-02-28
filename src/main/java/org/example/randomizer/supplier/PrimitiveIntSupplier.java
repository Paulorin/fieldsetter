package org.example.randomizer.supplier;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.function.IntSupplier;

@RequiredArgsConstructor
public class PrimitiveIntSupplier implements IntSupplier {
	private final Random random;

	@Override
	public int getAsInt() {
		int value = random.nextInt();
		value = value != 0 ? value : value + 1;
		value = random.nextBoolean() ? value : -value;
		return value;
	}
}
