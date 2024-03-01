package org.example.fieldsetter.setter;

import java.lang.reflect.Field;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

public class IntegerSetter extends AbstractFieldSetterSupplierWithPredicate implements FieldSetterWithPredicate {
	private final IntSupplier supplier;

	public IntegerSetter(IntSupplier supplier, Predicate<Field> predicate) {
		super(predicate);
		this.supplier = supplier;
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(field.get(o) == null) {
			int value = supplier.getAsInt();
			field.set(o, value);
		}
	}
}
