package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class RandomCharacterSupplier implements Supplier<Character> {
    private final Random random;

    private final static String DEFAULT_ALPHABET = " \tabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Override
    public Character get() {
        int randomPosition = random.nextInt(DEFAULT_ALPHABET.length());
        return DEFAULT_ALPHABET.charAt(randomPosition);
    }
}
