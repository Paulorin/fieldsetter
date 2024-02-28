package org.example.randomizer.example;


import lombok.Data;
import org.example.randomizer.annotation.Range;

@Data
public class Account {
	private String firstName;
	private String lastName;
	private Gender gender;
	@Range(from=14,to=110)
	private Integer age;
}
