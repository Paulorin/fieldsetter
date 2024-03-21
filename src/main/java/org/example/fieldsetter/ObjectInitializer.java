package org.example.fieldsetter;

public interface ObjectInitializer {
	/**
	 * Creates object of specified class and sets that fields with configured default values.
	 * @param clazz the class to be created
	 * @return object of created class
	 * @throws ObjectFieldSetterException throws this exception in case of any error
	 */
	<T> T get(Class<T> clazz);
	//<T> T[] generate(Class<T> clazz, int count);
	//<T> void generate(Class<T> clazz, int count, Consumer<T[]> consumer);

	/**
	 * Initializes fields of specified class with configured default values
	 * @param o the object which fields to be initialized  with configured default values
	 */
	void initialize(Object o);
}
