package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class PrimitiveDoubleSetter implements FieldSetterWithPredicate {
	private final static double MIN_ZERO_OFFSET = 0.00001;
	private final double MAX_VALUE = 1000.0;
	private final Random random;

	@Override
	public boolean test(Field field) {
		Class<?> fieldClass = field.getType();
		return fieldClass.getName().equals("double");
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		double value = (double)field.get(o);
		if(value > - MIN_ZERO_OFFSET && value < MIN_ZERO_OFFSET) {
			double randomValue = random.nextDouble() * MAX_VALUE;
			if(randomValue < MIN_ZERO_OFFSET) {
				randomValue += MIN_ZERO_OFFSET;
			}
			randomValue = random.nextBoolean() ? randomValue : -randomValue;
			field.setDouble(o, randomValue);
		}
	}
}
