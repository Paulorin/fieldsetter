package org.example.fieldsetter;

import org.example.fieldsetter.setter.*;
import org.example.fieldsetter.supplier.RandomPrimitiveIntSupplier;
import org.example.fieldsetter.supplier.RandomBooleanSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveLongSupplier;
import org.example.fieldsetter.supplier.WordSupplier;

import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ObjectFieldSetter implements ObjectInitializer {

	private final List<FieldSetterSupplierWithPredicate> fieldSetterSuppliers;
	private final FieldSetter emptySetter;

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
		fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
				f -> new PrimitiveIntSetter(new RandomPrimitiveIntSupplier(random), v -> v == 0),
				f -> f.getType().getName().equals("int")));
		fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
				f-> new PrimitiveLongSetter(new RandomPrimitiveLongSupplier(random)),
				f -> f.getType().getName().equals("long")));
		fieldSetterSuppliers.add(new PrimitiveFloatSetter(random));
		fieldSetterSuppliers.add(new PrimitiveDoubleSetter(random));
		fieldSetterSuppliers.add(new BooleanSetter(random));
		fieldSetterSuppliers.add(new CharacterSetter(random));
		fieldSetterSuppliers.add(new EnumSetter(random));
		fieldSetterSuppliers.add(new IntegerSetter(new RandomPrimitiveIntSupplier(random), f->f.getType().equals(Integer.class)));
	}

	/**
	 * ObjectFieldSetter is capable to create objects of specified type and set object
	 * fields to some default or random value.
	 *
	 * @param fieldSetterSuppliers is a collection with setters of separate fields. Each
	 *                             setter responsible for setting of field of specific
	 *                             type or field annotated by specific annotation. There
	 *                             are a lot of examples of setters in com.example.randomizer.setter
	 *                             package
	 *
	 * @param emptySetter is a last resort setter that is applied to a field of unknown or
	 *                    unsupported type. It may do nothing then unknown field is just ignored.
	 *                    It may print something to log or stdout for test purposes. Or it may
	 *                    throw ObjectFieldSetterException. The default EmptySetter prints
	 *                    message to stdout with information about field that where skipped.
	 */
	public ObjectFieldSetter(List<FieldSetterSupplierWithPredicate> fieldSetterSuppliers,
							 FieldSetter emptySetter) {
		this.fieldSetterSuppliers = fieldSetterSuppliers;
		this.emptySetter = emptySetter;
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
		Map<String, List<FieldWithSetter>> fieldSettersByClassName = getMapFromSoftReference();

		List<FieldWithSetter> fieldWithSetters = fieldSettersByClassName.get(o.getClass().getName());
		if(fieldWithSetters == null) {
			fieldWithSetters = calculateFieldsWithSetters(o);
			fieldSettersByClassName.put(o.getClass().getName(), fieldWithSetters);
		}
		return fieldWithSetters;
	}

	private Map<String, List<FieldWithSetter>> getMapFromSoftReference() {
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
