package org.example.randomizer.setter;

import java.lang.reflect.Field;

public interface FieldSetterWithPredicate extends FieldSetterSupplierWithPredicate, FieldSetter {
	@Override
	default FieldSetter apply(Field field) {
		return this;
	}
}
