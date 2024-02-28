package org.example.randomizer.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class PrimitiveLongSetter implements FieldSetterWithPredicate {
	private final Random random;

	@Override
	public boolean test(Field field) {
		Class<?> fieldClass = field.getType();
		return fieldClass.getName().equals("long");
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if((long)field.get(o) == 0) {
			long value = random.nextLong();
			value = value != 0 ? value : value + 1;
			field.setLong(o, random.nextBoolean() ? value : -value);
		}
	}
}
