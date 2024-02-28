package org.example.randomizer.setter;

import java.lang.reflect.Field;

public interface FieldSetter {
	void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException;
}
