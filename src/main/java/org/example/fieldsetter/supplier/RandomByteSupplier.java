package org.example.fieldsetter.supplier;

import java.util.Random;
import java.util.function.Supplier;

public class RandomByteSupplier implements Supplier<Byte> {
	private final Random random;
	private final RandomPrimitiveByteSupplier byteSupplier;
	private final int valuePercentage;
	private final Supplier<Byte> supplier;

	public RandomByteSupplier(Random random, byte from, byte to, int nullPercentage) {
		if(nullPercentage < 0 || nullPercentage > 100) {
			throw new IllegalArgumentException("nullPercentage should be in range [0, 100]");
		}
		if(to < from) {
			throw new IllegalArgumentException("parameter 'to' has to be greater then 'from'");
		}
		this.random = random;
		byteSupplier = new RandomPrimitiveByteSupplier(random, from, to);
		valuePercentage = 100 - nullPercentage;
		if(valuePercentage == 0) {
			supplier = () -> null;
		} else if (valuePercentage == 100) {
			supplier = byteSupplier::getAsByte;
		} else {
			supplier = () -> {
				if(this.random.nextInt(101) > valuePercentage) {
					return null;
				} else {
					return byteSupplier.getAsByte();
				}
			};
		}
	}


	public RandomByteSupplier(Random random, byte from, byte to) {
		this(random, from, to, 0);
	}

	public RandomByteSupplier(Random random) {
		this(random, Byte.MIN_VALUE, Byte.MAX_VALUE, 0);
	}

	@Override
	public Byte get() {
		return supplier.get();
	}
}
