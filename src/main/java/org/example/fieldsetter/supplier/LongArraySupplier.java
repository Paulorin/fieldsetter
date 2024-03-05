package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.function.LongSupplier;
import java.util.function.Supplier;

@AllArgsConstructor
public class LongArraySupplier implements Supplier<Object> {
	private final LongSupplier supplier;
	private final int length;
	@Override
	public Object get() {
		long[] array = new long[length];
		for(int i=0; i<length; i++) {
			array[i]=supplier.getAsLong();
		}
		return array;
	}
}
