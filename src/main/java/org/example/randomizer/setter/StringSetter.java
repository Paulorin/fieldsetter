package org.example.randomizer.setter;

import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class StringSetter extends AbstractFieldSetterSupplierWithPredicate implements FieldSetterWithPredicate {
	private final Supplier<String> supplier;

	public StringSetter(Supplier<String> supplier, Predicate<Field> predicate){
		super(predicate);
		this.supplier = supplier;
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(field.get(o) == null) {
			String randomString = supplier.get();
			field.set(o, randomString);
		}
	}
}
