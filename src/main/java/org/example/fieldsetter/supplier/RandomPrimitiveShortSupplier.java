package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.ShortSupplier;

import java.util.Random;

@AllArgsConstructor
public class RandomPrimitiveShortSupplier implements ShortSupplier {
	private final Random random;
	@Override
	public short getAsShort() {
		return (short)(random.nextInt(0x10000) + Short.MIN_VALUE);
	}
}
