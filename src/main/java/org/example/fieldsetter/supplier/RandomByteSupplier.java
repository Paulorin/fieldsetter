package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.ByteSupplier;

import java.util.Random;

@AllArgsConstructor
public class RandomByteSupplier implements ByteSupplier {
	private final Random random;
	@Override
	public byte getAsByte() {
		return (byte) (random.nextInt(0x100) + Byte.MIN_VALUE);
	}
}
