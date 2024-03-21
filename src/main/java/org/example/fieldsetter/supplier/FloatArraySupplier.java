package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.FloatSupplier;

import java.util.function.Supplier;

@AllArgsConstructor
public class FloatArraySupplier implements Supplier<Object> {
	private final FloatSupplier supplier;
	private final int length;
	@Override
	public Object get() {
		float[] array = new float[length];
		for(int i=0; i<length; i++) {
			array[i] = supplier.getAsFloat();
		}
		return array;
	}
}
