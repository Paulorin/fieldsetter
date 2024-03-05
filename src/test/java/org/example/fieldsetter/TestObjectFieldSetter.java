package org.example.fieldsetter;

import org.assertj.core.data.Offset;
import org.example.fieldsetter.example.Account;
import org.example.fieldsetter.example.Book;
import org.example.fieldsetter.example.Buffer;
import org.example.fieldsetter.example.Device;
import org.example.fieldsetter.example.Elephant;
import org.example.fieldsetter.example.Person;
import org.example.fieldsetter.example.PersonData;
import org.example.fieldsetter.example.PrimitiveBuffer;
import org.example.fieldsetter.example.Product;
import org.example.fieldsetter.example.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestObjectFieldSetter {

	private ObjectFieldSetter fieldSetter;

	@BeforeEach
	public void beforeEach() {
		fieldSetter = TestUtil.getObjectFieldSetter();
	}

	@Test
	public void shouldCreateNotNullAccount() {
		Account account = fieldSetter.get(Account.class);
		assertThat(account).isNotNull();
	}

	@Test
	public void shouldMakeRandomAccount() {
		for(int i=0; i<10; i++) {
			Account account = fieldSetter.get(Account.class);
			assertThat(account).isNotNull();
			assertThat(account.getFirstName()).isNotBlank();
			assertThat(account.getLastName()).isNotBlank();
			assertThat(account.getGender()).isNotNull();
		}
	}


	@Test
	public void shouldMakeRandomProduct() {
		for(int i=0; i<10; i++) {
			Product product = fieldSetter.get(Product.class);
			assertThat(product).isNotNull();
			assertThat(product.getId()).isNotEqualTo(0);
			assertThat(product.getName()).isNotBlank();
			assertThat(product.getElectricClass()).isNotNull();
			assertThat(product.getWeight()).isNotNull();
			assertThat(product.getLength()).isNotNull();
			assertThat(product.getOptionalParameter()).isNotNull();
			System.out.println(product);
		}
	}

	@Test
	public void shouldMakeRandomPerson() {
		for(int i=0; i<10; i++) {
			Person person = fieldSetter.get(Person.class);
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
			Rectangle rectangle = fieldSetter.get(Rectangle.class);
			assertThat(rectangle).isNotNull();
			assertThat(rectangle.getHeight()).isNotCloseTo(0, Offset.offset(0.00001));
			assertThat(rectangle.getWidth()).isNotCloseTo(0, Offset.offset(0.00001));
			assertThat(rectangle.getHidden()).isNotNull();
			assertThat(rectangle.getSides()).isNotNull();
		}
	}

	@Test
	public void shouldMakeRandomDevice() {
		for(int i=0; i<10; i++) {
			Device device = fieldSetter.get(Device.class);
			assertThat(device).isNotNull();
			assertThat(device.getWeight()).isNotCloseTo(0.0f, Offset.offset(0.00001f));
			assertThat(device.getCode()).isNotEqualTo('\u0000');
			assertThat(device.getFlags()).isNotEqualTo((byte)0);
			assertThat(device.getOptionalFlags()).isNotNull();
			assertThat(device.getInitializers()).isNotNull();
			System.out.println(device);
		}
	}

	@Test
	public void shouldMakeRandomPersonData() {
		for(int i=0; i<100; i++) {
			PersonData personData = fieldSetter.get(PersonData.class);
			assertThat(personData).isNotNull();
			assertThat(personData.getAge()).isGreaterThan(0);
			assertThat(personData.getAge()).isLessThan(4);
			assertThat(personData.getInitials()).isNotNull();
			System.out.println(personData);
		}
	}

	@Test
	 public void shouldMakeRandomBook() {
		 for(int i=0; i<100; i++) {
			 Book book = fieldSetter.get(Book.class);
			 assertThat(book).isNotNull();
			 assertThat(book.getAuthor()).isNotNull();
			 assertThat(book.getAuthor().length()).isGreaterThan(9);
			 assertThat(book.getAuthor().length()).isLessThan(51);
			 assertThat(book.getPages()).isNotEqualTo(0);
			 assertThat(book.getIllustrations()).isNotNull();
			 assertThat(book.getShorts()).isNotNull();
			 System.out.println(book);
		 }
	 }

	 @Test
	 public void shouldMakeRandomElephant() {
		for(int i=0; i<10; i++) {
			Elephant elephant = fieldSetter.get(Elephant.class);
			assertThat(elephant).isNotNull();
			assertThat(elephant.getWeight()).isNotNull();
			assertThat(elephant.getWeight()).isNotEqualTo(0);
			System.out.println(elephant);
		}
	 }

	 @Test
	 public void shouldMakeRandomBuffer() {
		for(int i = 0; i < 10; i++) {
			Buffer buffer = fieldSetter.get(Buffer.class);
			assertThat(buffer).isNotNull();
			assertThat(buffer.getLongs()).isNotNull();
			assertThat(buffer.getFloats()).isNotNull();
			assertThat(buffer.getDoubles()).isNotNull();
			assertThat(buffer.getStrings()).isNotNull();
			assertThat(buffer.getOptions()).isNotNull();
			System.out.println(buffer);
		}
	 }

	 @Test
	 public void shouldMakeRandomPrimitiveBuffer() {
		for(int i=0; i<10; i++) {
			PrimitiveBuffer  buffer = fieldSetter.get(PrimitiveBuffer.class);
			assertThat(buffer.getBooleans()).isNotNull();
			assertThat(buffer.getBytes()).isNotNull();
			assertThat(buffer.getChars()).isNotNull();
			assertThat(buffer.getShorts()).isNotNull();
			assertThat(buffer.getInts()).isNotNull();
			assertThat(buffer.getLongs()).isNotNull();
			assertThat(buffer.getFloats()).isNotNull();
			assertThat(buffer.getDoubles()).isNotNull();
			System.out.println(buffer);
		}
	 }
}
