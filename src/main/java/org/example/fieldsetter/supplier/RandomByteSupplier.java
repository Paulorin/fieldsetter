package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class RandomByteSupplier implements Supplier<Byte> {
	private final Random random;
	@Override
	public Byte get() {
		return (byte)(random.nextInt(0x100) + Byte.MIN_VALUE);
	}
}
