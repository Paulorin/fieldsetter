package org.example.randomizer.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.function.Supplier;

@AllArgsConstructor
public class ObjectSetter<T> implements FieldSetter {
	private final Supplier<T> supplier;
	private final Predicate<T> predicate;

	@SuppressWarnings("unchecked")
	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		T objectValue = (T)field.get(o);

		if(predicate.test(objectValue)) {
			Object value = supplier.get();
			field.set(o, value);
		}
	}
}
