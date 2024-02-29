package org.example.randomizer.setter;

import lombok.AllArgsConstructor;
import org.example.randomizer.ObjectFieldSetterException;
import org.example.randomizer.annotation.Range;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class RangeSetterSupplier implements FieldSetterSupplierWithPredicate {
	private final Random random;

	@Override
	public boolean test(Field field) {
		Range age = field.getDeclaredAnnotation(Range.class);
		return age != null;
	}


	@Override
	public FieldSetter apply(Field field) {
		Range age = field.getDeclaredAnnotation(Range.class);
		return new RangeSetter(random, age.from(), age.to());
	}

	@AllArgsConstructor
	private static class RangeSetter implements FieldSetter {
		private final Random random;
		private final int from;
		private final int to;

		@Override
		public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
			int randomValueInRange = random.nextInt(to - from + 1) + from;
			Class<?> fieldClass = field.getType();
			if(fieldClass.isPrimitive() ) {
				if(fieldClass.getName().equals("int")) {
					if((int)field.get(o) == 0) {
						field.setInt(o, randomValueInRange);
					}
					return;
				} else if(fieldClass.getName().equals("long")) {
					if((long)field.get(o) == 0) {
						field.setLong(o, randomValueInRange);
					}
					return;
				}
			} else if(fieldClass.equals(Integer.class) || fieldClass.equals(Long.class)) {
				if(field.get(o) == null) {
					field.set(o, randomValueInRange);
					return;
				}
			}
			throw new ObjectFieldSetterException(String.format(
					"Cannot set age of type %s in class %s", fieldClass.getName(), field.getDeclaringClass().getName()));
		}
	}
}
