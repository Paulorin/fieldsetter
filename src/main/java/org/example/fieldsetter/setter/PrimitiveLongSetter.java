package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;

@AllArgsConstructor
public class PrimitiveLongSetter implements FieldSetter {
	private final LongSupplier supplier;
	private final LongPredicate predicate;

	/**
	 * Creates new PrimitiveLongSetter of long field with specified long supplier and with predicate
	 * always returning true. I.e. this setter will set value of target long field
	 * in any case
	 *
	 * @param supplier supplier of long value
	 */
	public PrimitiveLongSetter(LongSupplier supplier) {
		this(supplier, v -> v == 0);
	}

	@Override
	public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
		if(predicate.test(field.getLong(o))) {
			field.setLong(o, supplier.getAsLong());
		}
	}
}
