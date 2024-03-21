package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.ShortSupplier;

import java.util.function.Supplier;

@AllArgsConstructor
public class ShortArraySupplier implements Supplier<Object> {
	private final ShortSupplier supplier;
	private final int length;
	@Override
	public Object get() {
		short[] array = new short[length];
		for(int i=0; i<length; i++) {
			array[i] = supplier.getAsShort();
		}
		return array;
	}
}
