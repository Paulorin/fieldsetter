package org.example.randomizer;

import org.assertj.core.data.Offset;
import org.example.randomizer.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestObjectRandomizer {

	private ObjectRandomizer randomizer;

	@BeforeEach
	public void beforeEach() {
		randomizer = new ObjectRandomizer(System.currentTimeMillis());
	}

	@Test
	public void shouldCreateNotNullAccount() {
		Account account = randomizer.generate(Account.class);
		assertThat(account).isNotNull();
	}

	@Test
	public void shouldMakeRandomAccount() {
		for(int i=0; i<10; i++) {
			Account account = randomizer.generate(Account.class);
			assertThat(account).isNotNull();
			assertThat(account.getFirstName()).isNotBlank();
			assertThat(account.getLastName()).isNotBlank();
			assertThat(account.getGender()).isNotNull();
		}
	}

	@Test
	public void shouldMakeRandomProduct() {
		for(int i=0; i<10; i++) {
			Product product = randomizer.generate(Product.class);
			assertThat(product).isNotNull();
			assertThat(product.getId()).isNotEqualTo(0);
			assertThat(product.getName()).isNotBlank();
			assertThat(product.getElectricClass()).isNotNull();
			System.out.println(product);
		}
	}

	@Test
	public void shouldMakeRandomPerson() {
		for(int i=0; i<10; i++) {
			Person person = randomizer.generate(Person.class);
			assertThat(person).isNotNull();
			assertThat(person.getFirstName()).isNotBlank();
			assertThat(person.getSecondName()).isNotBlank();
			assertThat(person.getAge()).isGreaterThan(-1);
			assertThat(person.getAge()).isLessThan(76);
		}
	}

	@Test
	public void shouldMakeRandomRectangle() {
		for(int i=0; i<10; i++) {
			Rectangle rectangle = randomizer.generate(Rectangle.class);
			assertThat(rectangle).isNotNull();
			assertThat(rectangle.getHeight()).isNotCloseTo(0, Offset.offset(0.00001));
			assertThat(rectangle.getWidth()).isNotCloseTo(0, Offset.offset(0.00001));
		}
	}

	@Test
	public void shouldMakeRandomDevice() {
		for(int i=0; i<10; i++) {
			Device device = randomizer.generate(Device.class);
			assertThat(device).isNotNull();
			assertThat(device.getWeight()).isNotCloseTo(0.0f, Offset.offset(0.00001f));
			assertThat(device.getCode()).isNotEqualTo('\u0000');
		}
	}

	@Test
	public void shouldMakeRandomPersonData() {
		for(int i=0; i<100; i++) {
			PersonData personData = randomizer.generate(PersonData.class);
			assertThat(personData).isNotNull();
			assertThat(personData.getAge()).isNotNull();
			assertThat(personData.getAge()).isGreaterThan(0);
			assertThat(personData.getAge()).isLessThan(4);
		}
	}

	@Test
	 public void shouldMakeRandomBook() {
		 for(int i=0; i<100; i++) {
			 Book book = randomizer.generate(Book.class);
			 assertThat(book).isNotNull();
			 assertThat(book.getAuthor()).isNotNull();
			 assertThat(book.getAuthor().length()).isGreaterThan(9);
			 assertThat(book.getAuthor().length()).isLessThan(51);
		 }
	 }

	 @Test
	 public void test() {
		for(int i=0; i<10; i++) {
			Elephant elephant = randomizer.generate(Elephant.class);
			assertThat(elephant).isNotNull();
			assertThat(elephant.getWeight()).isNotNull();
			assertThat(elephant.getWeight()).isNotEqualTo(0);
			System.out.println(elephant);
		}
	 }
}
