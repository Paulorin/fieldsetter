package org.example.fieldsetter.supplier;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

public class TestRandomByteSupplier {
	@Test
	public void shouldReturnMinAndMaxValues() {
		RandomByteSupplier supplier = new RandomByteSupplier(ThreadLocalRandom.current());
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		for(int i=0; i<100000; i++) {
			Byte value = supplier.get();
			assertThat(value).isNotNull();
			if(value > maxValue) {
				maxValue = value;
			}
			if(value < minValue) {
				minValue = value;
			}
		}
		assertThat(minValue).isEqualTo(Byte.MIN_VALUE);
		assertThat(maxValue).isEqualTo(Byte.MAX_VALUE);
	}

	@Test
	public void shouldReturnSomeNulls() {
		RandomByteSupplier supplier = new RandomByteSupplier(ThreadLocalRandom.current(), (byte)5, (byte)120, 1);
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		int nullCount = 0;
		for(int i=0; i<100000; i++) {
			Byte value = supplier.get();
			if(value == null) {
				nullCount++;
			} else {
				if (value > maxValue) {
					maxValue = value;
				}
				if (value < minValue) {
					minValue = value;
				}
			}
		}
		assertThat(nullCount).isGreaterThan(0);
		assertThat(minValue).isEqualTo((byte)5);
		assertThat(maxValue).isEqualTo((byte)120);
	}

	@Test
	public void shouldReturnNulls() {
		RandomByteSupplier supplier = new RandomByteSupplier(ThreadLocalRandom.current(), (byte)5, (byte)120, 100);
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		int nullCount = 0;
		for(int i=0; i<100000; i++) {
			Byte value = supplier.get();
			assertThat(value).isNull();
			nullCount++;
		}
		assertThat(nullCount).isEqualTo(100000);
	}

}
