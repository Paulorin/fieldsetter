package org.example.fieldsetter.setter;

import java.lang.reflect.Field;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FieldSetterSupplierWithPredicate extends Function<Field, FieldSetter>, Predicate<Field> {
}
