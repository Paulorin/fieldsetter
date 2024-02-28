package org.example.randomizer;

public interface Randomizer {
	<T> T generate(Class<T> clazz);
	//<T> T[] generate(Class<T> clazz, int count);
	//<T> void generate(Class<T> clazz, int count, Consumer<T[]> consumer);
	void initialize(Object o);
}
