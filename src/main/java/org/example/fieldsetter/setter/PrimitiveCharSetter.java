package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.CharPredicate;
import org.example.fieldsetter.function.CharSupplier;

import java.lang.reflect.Field;

@AllArgsConstructor
public class PrimitiveCharSetter implements FieldSetter {
	private final CharSupplier supplier;
	private final CharPredicate predicate;

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		char value = field.getChar(o);
		if(predicate.test(value)) {
			field.setChar(o, supplier.getAsChar());
		}
	}
}
