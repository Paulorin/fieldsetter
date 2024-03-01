package org.example.fieldsetter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AsciiString {
    int minAscii() default 97; // 'a'
    int maxAscii() default 122; // 'z'
    int minLength() default 0;
    int maxLength() default 25;
}
