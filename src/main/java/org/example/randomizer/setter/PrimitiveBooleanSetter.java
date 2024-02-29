package org.example.randomizer.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

@AllArgsConstructor
public class PrimitiveBooleanSetter implements FieldSetter {
	private final BooleanSupplier supplier;
	private final Predicate<Boolean> predicate;

	public PrimitiveBooleanSetter(BooleanSupplier supplier) {
		this(supplier, b -> true );
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(predicate.test(field.getBoolean(o))) {
			field.setBoolean(o, supplier.getAsBoolean());
		}
	}
}
