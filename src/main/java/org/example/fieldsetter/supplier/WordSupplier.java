package org.example.fieldsetter.supplier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@AllArgsConstructor
public class WordSupplier implements Supplier<String> {
    private final Random random;
    private static final String[] RANDOM_WORDS;

    static {
        RANDOM_WORDS = "One Two What is that Why not Or else".split(" ");
    }

    @Override
    public String get() {
        return RANDOM_WORDS[random.nextInt(RANDOM_WORDS.length)];
    }
}
