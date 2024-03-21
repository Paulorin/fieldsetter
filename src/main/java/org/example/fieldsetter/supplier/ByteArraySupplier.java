package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.ByteSupplier;

import java.util.function.Supplier;

@AllArgsConstructor
public class ByteArraySupplier implements Supplier<Object> {
	private final ByteSupplier supplier;
	private final int length;
	@Override
	public Object get() {
		byte[] array = new byte[length];
		for(int i=0; i<length; i++) {
			array[i] = supplier.getAsByte();
		}
		return array;
	}
}
