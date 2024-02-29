package org.example.randomizer;

import org.example.randomizer.setter.*;
import org.example.randomizer.supplier.PrimitiveIntSupplier;
import org.example.randomizer.supplier.RandomBooleanSupplier;
import org.example.randomizer.supplier.WordSupplier;

import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ObjectFieldSetter implements ObjectInitializer {

	private final List<FieldSetterSupplierWithPredicate> fieldSetterSuppliers;
	private final FieldSetterWithPredicate emptySetter;
	private SoftReference<Map<String, List<FieldWithSetter>>> classSetters;

	public ObjectFieldSetter() {
		Random random = new Random(System.currentTimeMillis());
		classSetters = new SoftReference<>(new HashMap<>());
		emptySetter = new EmptySetter();
		fieldSetterSuppliers = new LinkedList<>();
		fieldSetterSuppliers.add(new RangeSetterSupplier(random));
		fieldSetterSuppliers.add(new AsciiStringSetterSupplier(random));
		fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
			f -> new ObjectSetter<>(new WordSupplier(random), Objects::isNull),
				f -> f.getType().equals(String.class)));
		fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
				f -> new PrimitiveBooleanSetter(new RandomBooleanSupplier(random)),
				f-> f.getType().getName().equals("boolean"))
		);

		fieldSetterSuppliers.add(new PrimitiveCharSetter(random));
		fieldSetterSuppliers.add(new PrimitiveIntSetter(new PrimitiveIntSupplier(random), f -> f.getType().getName().equals("int")));
		fieldSetterSuppliers.add(new PrimitiveLongSetter(random));
		fieldSetterSuppliers.add(new PrimitiveFloatSetter(random));
		fieldSetterSuppliers.add(new PrimitiveDoubleSetter(random));
		fieldSetterSuppliers.add(new BooleanSetter(random));
		fieldSetterSuppliers.add(new CharacterSetter(random));
		fieldSetterSuppliers.add(new EnumSetter(random));
		fieldSetterSuppliers.add(new IntegerSetter(new PrimitiveIntSupplier(random), f->f.getType().equals(Integer.class)));
		fieldSetterSuppliers.add(emptySetter);
	}

	@Override
	public <T> T get(Class<T> clazz) {
		T o = newInstance(clazz);
		initialize(o);
		return o;
	}

	@Override
	public void initialize(Object o) {
		List<FieldWithSetter> fieldWithSetters = getFieldWithSetters(o);
		for(FieldWithSetter fieldWithSetter : fieldWithSetters) {
			initializeField(o, fieldWithSetter);
		}
	}

	private List<FieldWithSetter> getFieldWithSetters(Object o) {
		Map<String, List<FieldWithSetter>> fieldSettersByClassName = getFieldSettersByClassName();

		List<FieldWithSetter> fieldWithSetters = fieldSettersByClassName.get(o.getClass().getName());
		if(fieldWithSetters == null) {
			fieldWithSetters = calculateFieldsWithSetters(o);
			fieldSettersByClassName.put(o.getClass().getName(), fieldWithSetters);
		}
		return fieldWithSetters;
	}

	private Map<String, List<FieldWithSetter>> getFieldSettersByClassName() {
		Map<String, List<FieldWithSetter>> fieldSetterByClassName = classSetters.get();
		if(fieldSetterByClassName == null) {
			fieldSetterByClassName = new HashMap<>();
			classSetters = new SoftReference<>(fieldSetterByClassName);
		}
		return fieldSetterByClassName;
	}

	private List<FieldWithSetter> calculateFieldsWithSetters(Object o) {
		List<Field> fields = getAllFields(o.getClass());
		List<FieldWithSetter> fieldsWithSetters = new LinkedList<>();
		for(Field field:fields) {
			FieldSetter fieldSetter = getFieldSetter(field);
			fieldsWithSetters.add(new FieldWithSetter(field, fieldSetter));
		}
		return fieldsWithSetters;
	}

	private void initializeField(Object o, FieldWithSetter fieldWithSetter) {
		Field field = fieldWithSetter.getField();
		FieldSetter fieldSetter = fieldWithSetter.getFieldSetter();
		field.setAccessible(true);
		try {
			fieldSetter.set(o, field);
		} catch(RuntimeException | IllegalAccessException e) {
			throw new ObjectFieldSetterException(String.format(
					"Cannot set field %s of class %s", field.getName(), o.getClass().getName()), e.getCause());
		}
	}

	private FieldSetter getFieldSetter(Field field) {
		for(FieldSetterSupplierWithPredicate supplier: fieldSetterSuppliers) {
			if(supplier.test(field)) {
				return supplier.apply(field);
			}
		}
		return emptySetter;
	}

	private List<Field> getAllFields(Class<?> clazz) {

		List<Field> fields = new LinkedList<>(Arrays.asList(clazz.getDeclaredFields()));
		for(Class<?> superClass = clazz.getSuperclass();
				!superClass.equals(Object.class);
				superClass = superClass.getSuperclass())  {

			fields.addAll(Arrays.asList(superClass.getDeclaredFields()));
		}
		return fields;
	}

	private <T> T newInstance(Class<T> clazz) {
		Constructor<T> constructor = getDefaultConstructor(clazz);
		try {
			constructor.setAccessible(true);
			return constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new ObjectFieldSetterException(String.format("Error calling constructor %s", constructor), e);
		}
	}

	private <T> Constructor<T> getDefaultConstructor(Class<T> clazz) {
		try {
			return clazz.getDeclaredConstructor();
		} catch (NoSuchMethodException e) {
			throw new ObjectFieldSetterException(String.format("Error getting default constructor of %s", clazz.getName()), e);
		}
	}
}
