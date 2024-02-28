package org.example.randomizer.example;

import lombok.Data;
import org.example.randomizer.annotation.Range;

@Data
public class Person {
	private String firstName;
	private String secondName;
	@Range(from=0, to=75)
	private Integer age;
}
