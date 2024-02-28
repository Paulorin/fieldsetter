package org.example.randomizer.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class PrimitiveFloatSetter implements FieldSetterWithPredicate {
	private final static float MIN_ZERO_OFFSET = 0.00001f;
	private final static float MAX_VALUE = 1000.0f;
	private final Random random;

	@Override
	public boolean test(Field field) {
		Class<?> fieldClass = field.getType();
		return fieldClass.getName().equals("float");
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		float value = random.nextFloat() * MAX_VALUE;
		value = random.nextBoolean() ? value : -value;
		if(value - 0.0f < 0.00001f) {
			value += 0.00001f;
		}
		field.setFloat(o, value);
	}
}
