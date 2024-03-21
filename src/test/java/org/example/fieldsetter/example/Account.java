package org.example.fieldsetter.example;


import lombok.Data;
import org.example.fieldsetter.annotation.Range;

@Data
public class Account {
	private String firstName;
	private String lastName;
	private Gender gender;
	@Range(from=14)
	private Integer age;
}
