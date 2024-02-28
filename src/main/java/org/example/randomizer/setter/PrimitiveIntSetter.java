package org.example.randomizer.setter;

import java.lang.reflect.Field;
import java.util.function.IntSupplier;
import java.util.function.Predicate;


public class PrimitiveIntSetter extends AbstractFieldSetterSupplierWithPredicate implements FieldSetterWithPredicate {
	private final IntSupplier supplier;

	public PrimitiveIntSetter(IntSupplier supplier, Predicate<Field> predicate) {
		super(predicate);
		this.supplier = supplier;
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if((int)field.get(o) == 0) {
			field.setInt(o, supplier.getAsInt());
		}
	}
}
