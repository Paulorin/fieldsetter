package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class BooleanSetter implements FieldSetterWithPredicate {
	private final Random random;

	@Override
	public boolean test(Field field) {
		Class<?> fieldClass = field.getType();
		return fieldClass.equals(Boolean.class);
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(field.get(o) == null) {
			boolean randomValue = random.nextBoolean();
			field.setBoolean(o, randomValue);
		}
	}
}
