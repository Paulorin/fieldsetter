package org.example.fieldsetter.supplier;

import org.example.fieldsetter.function.ByteSupplier;

import java.util.Random;

public class RandomPrimitiveByteSupplier implements ByteSupplier {
	private final Random random;
	private final ByteSupplier supplier;

	public RandomPrimitiveByteSupplier(Random random, byte from, byte to) {
		this.random = random;
		if(from <= to) {
			supplier = () -> (byte)(this.random.nextInt(to - from + 1) + from);
		}
		else {
			throw new IllegalArgumentException(String.format("to=%d has to be greater then from=%d", from, to));
		}
	}

	public RandomPrimitiveByteSupplier(Random random) {
		this(random, Byte.MIN_VALUE, Byte.MAX_VALUE);
	}

	@Override
	public byte getAsByte() {
		return supplier.getAsByte();
	}
}
