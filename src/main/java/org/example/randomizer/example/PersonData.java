package org.example.randomizer.example;

import lombok.Data;
import org.example.randomizer.annotation.Range;

@Data
public class PersonData {
	@Range(from=1, to=3)
	private int age;
}
