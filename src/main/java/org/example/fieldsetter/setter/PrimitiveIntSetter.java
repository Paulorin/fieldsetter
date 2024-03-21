package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;

@AllArgsConstructor
public class PrimitiveIntSetter implements FieldSetter {
	private final IntSupplier supplier;
	private final IntPredicate predicate;

	/**
	 * Creates new PrimitiveIntSetter of int field with specified int supplier and with predicate
	 * always returning true. I.e. this setter will set value of target int field
	 * in any case
	 *
	 * @param supplier supplier of int value
	 */
	public PrimitiveIntSetter(IntSupplier supplier) {
		this(supplier, v -> true);
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(predicate.test(field.getInt(o))) {
			field.setInt(o, supplier.getAsInt());
		}
	}
}
