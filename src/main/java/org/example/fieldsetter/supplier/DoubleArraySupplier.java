package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

@AllArgsConstructor
public class DoubleArraySupplier implements Supplier<Object> {
	private final DoubleSupplier supplier;
	private final int length;

	@Override
	public Object get() {
		double[] array = new double[length];
		for(int i=0; i<length; i++) {
			array[i] = supplier.getAsDouble();
		}
		return array;
	}
}
