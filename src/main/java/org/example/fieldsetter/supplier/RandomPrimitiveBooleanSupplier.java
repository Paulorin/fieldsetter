package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.BooleanSupplier;

@AllArgsConstructor
public class RandomPrimitiveBooleanSupplier implements BooleanSupplier {
	private final Random random;

	@Override
	public boolean getAsBoolean() {
		return random.nextBoolean();
	}
}
