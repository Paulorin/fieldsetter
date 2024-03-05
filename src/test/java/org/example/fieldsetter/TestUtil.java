package org.example.fieldsetter;

import org.example.fieldsetter.setter.AsciiStringSetterSupplier;
import org.example.fieldsetter.setter.EmptySetter;
import org.example.fieldsetter.setter.FieldSetterSupplierWithPredicate;
import org.example.fieldsetter.setter.GenericFieldSetterSupplierWithPredicate;
import org.example.fieldsetter.setter.ObjectSetter;
import org.example.fieldsetter.setter.PrimitiveBooleanSetter;
import org.example.fieldsetter.setter.PrimitiveByteSetter;
import org.example.fieldsetter.setter.PrimitiveCharSetter;
import org.example.fieldsetter.setter.PrimitiveDoubleSetter;
import org.example.fieldsetter.setter.PrimitiveFloatSetter;
import org.example.fieldsetter.setter.PrimitiveIntSetter;
import org.example.fieldsetter.setter.PrimitiveLongSetter;
import org.example.fieldsetter.setter.PrimitiveShortSetter;
import org.example.fieldsetter.setter.RangeSetterSupplier;
import org.example.fieldsetter.supplier.ArraySupplier;
import org.example.fieldsetter.supplier.BooleanArraySupplier;
import org.example.fieldsetter.supplier.ByteArraySupplier;
import org.example.fieldsetter.supplier.CharArraySupplier;
import org.example.fieldsetter.supplier.DoubleArraySupplier;
import org.example.fieldsetter.supplier.FloatArraySupplier;
import org.example.fieldsetter.supplier.IntArraySupplier;
import org.example.fieldsetter.supplier.LongArraySupplier;
import org.example.fieldsetter.supplier.RandomBooleanSupplier;
import org.example.fieldsetter.supplier.RandomByteSupplier;
import org.example.fieldsetter.supplier.RandomDoubleSupplier;
import org.example.fieldsetter.supplier.RandomFloatSupplier;
import org.example.fieldsetter.supplier.RandomLongSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveByteSupplier;
import org.example.fieldsetter.supplier.RandomCharacterSupplier;
import org.example.fieldsetter.supplier.RandomEnumSupplier;
import org.example.fieldsetter.supplier.RandomIntegerSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveBooleanSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveCharSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveDoubleSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveFloatSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveIntSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveLongSupplier;
import org.example.fieldsetter.supplier.RandomPrimitiveShortSupplier;
import org.example.fieldsetter.supplier.RandomShortSupplier;
import org.example.fieldsetter.supplier.RandomStringSupplier;
import org.example.fieldsetter.supplier.ShortArraySupplier;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class TestUtil {
    public static ObjectFieldSetter getObjectFieldSetter(){
        Random random = new Random(System.currentTimeMillis());

        ArrayList<FieldSetterSupplierWithPredicate> fieldSetterSuppliers = new ArrayList<>();

        fieldSetterSuppliers.add(new RangeSetterSupplier(random));
        fieldSetterSuppliers.add(new AsciiStringSetterSupplier(random));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomStringSupplier(random), Objects::isNull),
                f -> f.getType().equals(String.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveBooleanSetter(new RandomPrimitiveBooleanSupplier(random)),
                f -> f.getType().equals(boolean.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveIntSetter(new RandomPrimitiveIntSupplier(random), v -> v == 0),
                f -> f.getType().equals(int.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveLongSetter(new RandomPrimitiveLongSupplier(random), v -> v ==0L),
                f -> f.getType().equals(long.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
            f -> new PrimitiveFloatSetter(new RandomPrimitiveFloatSupplier(random), v -> v == 0.0),
            f -> f.getType().equals(float.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveDoubleSetter(new RandomPrimitiveDoubleSupplier(random), v -> v == 0.0),
                f -> f.getType().equals(double.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomBooleanSupplier(random), Objects::isNull),
                f -> f.getType().equals(Boolean.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveCharSetter(new RandomPrimitiveCharSupplier(random), c -> c == '\u0000'),
                f -> f.getType().equals(char.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomCharacterSupplier(random), Objects::isNull),
                f -> f.getType().equals(Character.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomEnumSupplier(random, f.getType().getEnumConstants()), Objects::isNull),
                f -> f.getType().isEnum()
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveByteSetter(new RandomPrimitiveByteSupplier(random), b -> b == 0),
                f -> f.getType().equals(byte.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveShortSetter(new RandomPrimitiveShortSupplier(random), s -> s == 0),
                f -> f.getType().equals(short.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomShortSupplier(random), Objects::isNull),
                f-> f.getType().equals(Short.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomByteSupplier(random), Objects::isNull),
                f -> f.getType().equals(Byte.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomIntegerSupplier(random), Objects::isNull),
                f -> f.getType().equals(Integer.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomLongSupplier(random), Objects::isNull),
                f -> f.getType().equals(Long.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomFloatSupplier(random), Objects::isNull),
                f -> f.getType().equals(Float.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomDoubleSupplier(random), Objects::isNull),
                f -> f.getType().equals(Double.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomCharacterSupplier(random), Character.class, 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(Character.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomByteSupplier(random), Byte.class, 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(Byte.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomShortSupplier(random), Short.class, 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(Short.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomIntegerSupplier(random), Integer.class, 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(Integer.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomLongSupplier(random), Long.class, 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(Long.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomFloatSupplier(random), Float.class, 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(Float.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomDoubleSupplier(random), Double.class, 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(Double.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomStringSupplier(random), String.class, 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(String.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ArraySupplier<>(new RandomEnumSupplier(random, f.getType().getComponentType().getEnumConstants()), f.getType().getComponentType(), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().isEnum()
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>( new BooleanArraySupplier(new RandomPrimitiveBooleanSupplier(random), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(boolean.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>( new ByteArraySupplier(new RandomPrimitiveByteSupplier(random), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(byte.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>( new ShortArraySupplier(new RandomPrimitiveShortSupplier(random), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(short.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>( new CharArraySupplier(new RandomPrimitiveCharSupplier(random), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(char.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>( new IntArraySupplier(new RandomPrimitiveIntSupplier(random), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(int.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>( new LongArraySupplier(new RandomPrimitiveLongSupplier(random), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(long.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>( new FloatArraySupplier(new RandomPrimitiveFloatSupplier(random), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(float.class)
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>( new DoubleArraySupplier(new RandomPrimitiveDoubleSupplier(random), 2), Objects::isNull),
                f -> f.getType().isArray() && f.getType().getComponentType().equals(double.class)
        ));
        fieldSetterSuppliers.trimToSize();

        return new ObjectFieldSetter(fieldSetterSuppliers, new EmptySetter());
    }
}
