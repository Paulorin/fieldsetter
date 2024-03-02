package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.ShortPredicate;
import org.example.fieldsetter.function.ShortSupplier;

import java.lang.reflect.Field;

@AllArgsConstructor
public class PrimitiveShortSetter implements FieldSetter {
	ShortSupplier supplier;
	ShortPredicate predicate;

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		short value = field.getShort(o);
		if(predicate.test(value)) {
			field.setShort(o, supplier.getAsShort());
		}
	}
}
