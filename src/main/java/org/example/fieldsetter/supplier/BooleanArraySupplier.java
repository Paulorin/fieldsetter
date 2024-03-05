package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@AllArgsConstructor
public class BooleanArraySupplier implements Supplier<Object> {
	private final BooleanSupplier supplier;
	private final int length;

	@Override
	public Object get() {
		boolean[] array = new boolean[length];
		for(int i=0; i<length; i++) {
			array[i] = supplier.getAsBoolean();
		}
		return array;
	}
}
