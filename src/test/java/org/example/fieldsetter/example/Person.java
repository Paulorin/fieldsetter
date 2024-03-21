package org.example.fieldsetter.example;

import lombok.Data;
import org.example.fieldsetter.annotation.Range;

@Data
public class Person {
	private String firstName;
	private String secondName;
	@Range(to=75)
	private Integer age;
}
