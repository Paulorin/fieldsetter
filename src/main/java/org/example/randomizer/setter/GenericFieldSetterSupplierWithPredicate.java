package org.example.randomizer.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
public class GenericFieldSetterSupplierWithPredicate implements FieldSetterSupplierWithPredicate {
	private Function<Field, FieldSetter> setterSupplier;
	private Predicate<Field> predicate;

	@Override
	public FieldSetter apply(Field field) {
		return setterSupplier.apply(field);
	}

	@Override
	public boolean test(Field field) {
		return predicate.test(field);
	}
}
