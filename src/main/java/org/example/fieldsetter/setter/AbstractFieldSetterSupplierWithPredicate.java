package org.example.fieldsetter.setter;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.function.Predicate;

@RequiredArgsConstructor
public abstract class AbstractFieldSetterSupplierWithPredicate implements FieldSetterSupplierWithPredicate {
	private final Predicate<Field> predicate;
	@Override
	public boolean test(Field field) {
		return predicate.test(field);
	}
}
