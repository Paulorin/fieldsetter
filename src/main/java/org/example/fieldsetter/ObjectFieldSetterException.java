package org.example.fieldsetter;

public class ObjectFieldSetterException extends RuntimeException {
	public ObjectFieldSetterException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectFieldSetterException(String message) {
		super(message);
	}
}
