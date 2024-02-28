package org.example.randomizer.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class EnumSetter implements FieldSetterWithPredicate {
	private final Random random;

	@Override
	public boolean test(Field field) {
		Class<?> fieldClass = field.getType();
		return fieldClass.isEnum();
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(field.get(o) == null) {
			Class<?> enumClass = field.getType();
			Object[] enums = enumClass.getEnumConstants();
			field.set(o, enums[random.nextInt(enums.length)]);
		}
	}
}
