package org.example.randomizer.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class PrimitiveBooleanSetter implements FieldSetterWithPredicate {
	private final Random random;

	@Override
	public boolean test(Field field) {
		Class<?> fieldClass = field.getType();
		return fieldClass.getName().equals("boolean");
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		boolean value = (boolean)field.get(o);
		if(!value) {
			boolean randomValue = random.nextBoolean();
			field.setBoolean(o, randomValue);
		}
	}
}
