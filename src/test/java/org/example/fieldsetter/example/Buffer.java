package org.example.fieldsetter.example;

import lombok.Data;

@Data
public class Buffer {
    Long[] longs;
    Float[] floats;
    Double[] doubles;
    String[] strings;
    Option[] options;
    Option choice = Option.FIRST;
    Option secondChoice = Option.SECOND;
}
