package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;
import org.example.fieldsetter.function.CharSupplier;

import java.util.Random;

@AllArgsConstructor
public class RandomPrimitiveCharSupplier implements CharSupplier {
    private final Random random;
    private final static String DEFAULT_ALPHABET = " \tabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public char getAsChar() {
        int randomPosition = random.nextInt(DEFAULT_ALPHABET.length());
        return DEFAULT_ALPHABET.charAt(randomPosition);
    }
}
