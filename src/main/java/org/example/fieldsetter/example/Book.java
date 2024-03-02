package org.example.fieldsetter.example;

import lombok.Data;
import org.example.fieldsetter.annotation.AsciiString;

@Data
public class Book {
    @AsciiString(minLength = 10, maxLength = 50)
    private String author;
    private short pages;
    private Short illustrations;
}
