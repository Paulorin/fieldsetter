package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class RandomShortSupplier implements Supplier<Short> {
	private final Random random;
	@Override
	public Short get() {
		return (short)(random.nextInt(0x10000) + Short.MIN_VALUE);
	}
}
