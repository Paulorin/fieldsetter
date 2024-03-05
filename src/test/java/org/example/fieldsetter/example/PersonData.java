package org.example.fieldsetter.example;

import lombok.Data;
import org.example.fieldsetter.annotation.Range;

@Data
public class PersonData {
	@Range(from=1, to=3)
	private int age;
	Character[] initials;
}
