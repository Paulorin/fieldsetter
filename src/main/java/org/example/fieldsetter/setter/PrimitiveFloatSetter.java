package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.FloatPredicate;
import org.example.fieldsetter.function.FloatSupplier;

import java.lang.reflect.Field;

@AllArgsConstructor
public class PrimitiveFloatSetter implements FieldSetter {
	private final FloatSupplier floatSupplier;
	private final FloatPredicate floatPredicate;


	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		float value = field.getFloat(o);

		if(floatPredicate.test(value)) {
			field.setFloat(o, floatSupplier.getAsFloat());
		}
	}
}
