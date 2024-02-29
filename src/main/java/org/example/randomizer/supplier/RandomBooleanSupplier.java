package org.example.randomizer.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.BooleanSupplier;

@AllArgsConstructor
public class RandomBooleanSupplier implements BooleanSupplier {
	private final Random random;

	@Override
	public boolean getAsBoolean() {
		return random.nextBoolean();
	}
}
