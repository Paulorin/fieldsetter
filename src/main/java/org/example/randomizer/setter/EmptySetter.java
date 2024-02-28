package org.example.randomizer.setter;

import java.lang.reflect.Field;

public class EmptySetter implements FieldSetterWithPredicate {

	@Override
	public boolean test(Field field) {
		return true;
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		System.out.println(String.format("Skip field %s %s of class %s",
				field.getType().getSimpleName(), field.getName(), o.getClass().getSimpleName()));
	}
}
