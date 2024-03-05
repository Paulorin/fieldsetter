package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

@AllArgsConstructor
public class IntArraySupplier implements Supplier<Object> {
	private final IntSupplier supplier;
	private final int length;
	@Override
	public Object get() {
		int[] array = new int[length];
		for(int i=0; i<length; i++) {
			array[i] = supplier.getAsInt();
		}
		return array;
	}
}
