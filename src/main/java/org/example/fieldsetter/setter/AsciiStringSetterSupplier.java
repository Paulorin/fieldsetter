package org.example.fieldsetter.setter;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.annotation.AsciiString;

import java.lang.reflect.Field;
import java.util.Random;

@AllArgsConstructor
public class AsciiStringSetterSupplier implements FieldSetterSupplierWithPredicate {
    private final Random random;

    @Override
    public FieldSetter apply(Field field) {
        AsciiString asciiString = field.getDeclaredAnnotation(AsciiString.class);
        return new AsciiStringSetter(random, asciiString.minAscii(), asciiString.maxAscii(),
                asciiString.minLength(), asciiString.maxLength());
    }

    @Override
    public boolean test(Field field) {
        AsciiString asciiString = field.getDeclaredAnnotation(AsciiString.class);
        return asciiString != null;
    }

    @AllArgsConstructor
    private static class AsciiStringSetter implements FieldSetter {
        private final Random random;
        private final int minAscii;
        private final int maxAscii;
        private final int minLength;
        private final int maxLength;

        @Override
        public void set(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
            if(field.get(o) == null) {
                String randomString = generateRandomString();
                field.set(o, randomString);
            }
        }

        public String generateRandomString() {
            int targetStringLength = random.nextInt(maxLength - minLength + 1) + minLength;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = minAscii + (int)
                        (random.nextFloat() * (maxAscii - minAscii + 1));
                buffer.append((char) randomLimitedInt);
            }
            return buffer.toString();
        }
    }
}
