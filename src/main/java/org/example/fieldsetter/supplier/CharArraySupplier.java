package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.CharSupplier;

import java.util.function.Supplier;

@AllArgsConstructor
public class CharArraySupplier implements Supplier<Object> {
	private final CharSupplier supplier;
	private final int length;
	@Override
	public Object get() {
		char[] array = new char[length];
		for(int i=0; i<length; i++) {
			array[i] = supplier.getAsChar();
		}
		return array;
	}
}
