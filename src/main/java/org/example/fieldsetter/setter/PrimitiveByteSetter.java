package org.example.fieldsetter.setter;


import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.BytePredicate;
import org.example.fieldsetter.function.ByteSupplier;

import java.lang.reflect.Field;

@AllArgsConstructor
public class PrimitiveByteSetter implements FieldSetter {
	private final ByteSupplier supplier;
	private final BytePredicate predicate;

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(predicate.test(field.getByte(o))) {
			field.setByte(o, supplier.getAsByte());
		}
	}
}
