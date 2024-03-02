package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;

@AllArgsConstructor
public class PrimitiveDoubleSetter implements FieldSetter {
	private final DoubleSupplier doubleSupplier;
	private final DoublePredicate doublePredicate;

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		double value = field.getDouble(o);

		if(doublePredicate.test(value)) {
			field.setDouble(o, doubleSupplier.getAsDouble());
		}
	}
}
