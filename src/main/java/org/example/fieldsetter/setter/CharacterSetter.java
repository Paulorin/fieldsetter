package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class CharacterSetter implements FieldSetterWithPredicate {
	private final static String DEFAULT_ALPHABET ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final Random random;

	@Override
	public boolean test(Field field) {
		Class<?> fieldClass = field.getType();
		return fieldClass.equals(Character.class);
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(field.get(o) == null) {
			int randomPosition = random.nextInt(DEFAULT_ALPHABET.length());
			char randomChar = DEFAULT_ALPHABET.charAt(randomPosition);
			field.set(o, randomChar);
		}
	}
}
