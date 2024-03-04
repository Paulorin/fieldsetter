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
import org.example.fieldsetter.supplier.RandomBooleanSupplier;
import org.example.fieldsetter.supplier.RandomByteSupplier;
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
import org.example.fieldsetter.supplier.ShortSupplier;
import org.example.fieldsetter.supplier.WordSupplier;

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
                f -> new ObjectSetter<>(new WordSupplier(random), Objects::isNull),
                f -> f.getType().equals(String.class))
        );
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveBooleanSetter(new RandomPrimitiveBooleanSupplier(random)),
                f -> f.getType().getName().equals("boolean"))
        );
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveIntSetter(new RandomPrimitiveIntSupplier(random), v -> v == 0),
                f -> f.getType().getName().equals("int"))
        );
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveLongSetter(new RandomPrimitiveLongSupplier(random)),
                f -> f.getType().getName().equals("long"))
        );
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
            f -> new PrimitiveFloatSetter(new RandomPrimitiveFloatSupplier(random), v -> v == 0.0),
            f -> f.getType().getName().equals("float"))
        );
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveDoubleSetter(new RandomPrimitiveDoubleSupplier(random), v -> v == 0.0),
                f -> f.getType().getName().equals("double")
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new RandomBooleanSupplier(random), Objects::isNull),
                f -> f.getType().equals(Boolean.class))
        );
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveCharSetter(new RandomPrimitiveCharSupplier(random), c -> c == '\u0000'),
                f -> f.getType().getName().equals("char"))
        );
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
                f -> f.getType().getName().equals("byte")
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new PrimitiveShortSetter(new RandomPrimitiveShortSupplier(random), s -> s == 0),
                f -> f.getType().getName().equals("short")
        ));
        fieldSetterSuppliers.add(new GenericFieldSetterSupplierWithPredicate(
                f -> new ObjectSetter<>(new ShortSupplier(random), Objects::isNull),
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
        fieldSetterSuppliers.trimToSize();

        return new ObjectFieldSetter(fieldSetterSuppliers, new EmptySetter());
    }
}
