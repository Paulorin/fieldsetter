package org.example.fieldsetter.setter;

import java.lang.reflect.Field;

/**
 * Setter of "last resort" that is applied in case if the field of unknown type is found
 * in the class. Prints message to stdout with information about field and class name.
 * Does nothing with the field.
 */
public class EmptySetter implements FieldSetter {
	@Override
	public void set(Object o, Field field) {
		System.out.println(String.format("Skip field %s %s of class %s",
				field.getType().getSimpleName(), field.getName(), o.getClass().getSimpleName()));
	}
}
