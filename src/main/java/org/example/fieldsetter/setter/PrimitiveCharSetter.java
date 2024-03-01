package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class PrimitiveCharSetter implements FieldSetterWithPredicate {
	private final static char ZERO_VALUE = '\u0000';
	private final static String DEFAULT_ALPHABET ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final Random random;

	@Override
	public boolean test(Field field) {
		Class<?> fieldClass = field.getType();
		return fieldClass.getName().equals("char");
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		char value = (char)field.get(o);
		if(value == ZERO_VALUE) {
			int randomPosition = random.nextInt(DEFAULT_ALPHABET.length());
			char randomChar = DEFAULT_ALPHABET.charAt(randomPosition);
			field.setChar(o, randomChar);
		}
	}
}
