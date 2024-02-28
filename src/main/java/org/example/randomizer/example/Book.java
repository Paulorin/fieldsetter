package org.example.randomizer.example;

import lombok.Data;
import org.example.randomizer.annotation.AsciiString;

@Data
public class Book {
    @AsciiString(minLength = 10, maxLength = 50)
    private String author;
}
