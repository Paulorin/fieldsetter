package org.example.fieldsetter.setter;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class FieldWithSetter {
	private final Field field;
	private final FieldSetter fieldSetter;
}
