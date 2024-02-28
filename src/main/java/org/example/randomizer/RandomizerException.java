package org.example.randomizer;

public class RandomizerException extends RuntimeException {
	public RandomizerException(String message, Throwable cause) {
		super(message, cause);
	}

	public RandomizerException(String message) {
		super(message);
	}
}
