package org.example.fieldsetter.supplier;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class TestRandomPrimitiveByteSupplier {
	@Test
	public void shouldReturnMinAndMaxValue() {
		RandomPrimitiveByteSupplier supplier = new RandomPrimitiveByteSupplier(ThreadLocalRandom.current());
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		for(int i=0; i<100000; i++) {
			byte value = supplier.getAsByte();
			if(value > maxValue) {
				maxValue = value;
			}
			if(value < minValue) {
				minValue = value;
			}
		}
		Assertions.assertThat(minValue).isEqualTo(Byte.MIN_VALUE);
		Assertions.assertThat(maxValue).isEqualTo(Byte.MAX_VALUE);
	}

	@Test
	public void shouldReturnValuesInPositiveRange() {
		RandomPrimitiveByteSupplier supplier = new RandomPrimitiveByteSupplier(ThreadLocalRandom.current(), (byte)1, (byte)10);
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		for(int i=0; i<100000; i++) {
			byte value = supplier.getAsByte();
			if(value > maxValue) {
				maxValue = value;
			}
			if(value < minValue) {
				minValue = value;
			}
		}
		Assertions.assertThat(minValue).isEqualTo((byte)1);
		Assertions.assertThat(maxValue).isEqualTo((byte)10);
	}

	@Test
	public void shouldReturnValuesInNegativeRange() {
		RandomPrimitiveByteSupplier supplier = new RandomPrimitiveByteSupplier(ThreadLocalRandom.current(), (byte)-50, (byte)-5);
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		for(int i=0; i<100000; i++) {
			byte value = supplier.getAsByte();
			if(value > maxValue) {
				maxValue = value;
			}
			if(value < minValue) {
				minValue = value;
			}
		}
		Assertions.assertThat(minValue).isEqualTo((byte)-50);
		Assertions.assertThat(maxValue).isEqualTo((byte)-5);
	}

	@Test
	public void shouldReturnValuesWithUpperZero() {
		RandomPrimitiveByteSupplier supplier = new RandomPrimitiveByteSupplier(ThreadLocalRandom.current(), (byte)-50, (byte)0);
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		for(int i=0; i<100000; i++) {
			byte value = supplier.getAsByte();
			if(value > maxValue) {
				maxValue = value;
			}
			if(value < minValue) {
				minValue = value;
			}
		}
		Assertions.assertThat(minValue).isEqualTo((byte)-50);
		Assertions.assertThat(maxValue).isEqualTo((byte)0);
	}

	@Test
	public void shouldReturnValuesWithLowZero() {
		RandomPrimitiveByteSupplier supplier = new RandomPrimitiveByteSupplier(ThreadLocalRandom.current(), (byte)0, Byte.MAX_VALUE);
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		for(int i=0; i<100000; i++) {
			byte value = supplier.getAsByte();
			if(value > maxValue) {
				maxValue = value;
			}
			if(value < minValue) {
				minValue = value;
			}
		}
		Assertions.assertThat(minValue).isEqualTo((byte)0);
		Assertions.assertThat(maxValue).isEqualTo(Byte.MAX_VALUE);
	}

	@Test
	public void shouldReturnValuesFromNegativeToPositive() {
		RandomPrimitiveByteSupplier supplier = new RandomPrimitiveByteSupplier(ThreadLocalRandom.current(), (byte)-10, (byte)35);
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		for(int i=0; i<100000; i++) {
			byte value = supplier.getAsByte();
			if(value > maxValue) {
				maxValue = value;
			}
			if(value < minValue) {
				minValue = value;
			}
		}
		Assertions.assertThat(minValue).isEqualTo((byte)-10);
		Assertions.assertThat(maxValue).isEqualTo((byte)35);
	}

	@Test
	public void shouldWorkZeroRange(){
		RandomPrimitiveByteSupplier supplier = new RandomPrimitiveByteSupplier(ThreadLocalRandom.current(), (byte)10, (byte)10);
		byte minValue = Byte.MAX_VALUE;
		byte maxValue = Byte.MIN_VALUE;
		for(int i=0; i<100000; i++) {
			byte value = supplier.getAsByte();
			if(value > maxValue) {
				maxValue = value;
			}
			if(value < minValue) {
				minValue = value;
			}
		}
		Assertions.assertThat(minValue).isEqualTo((byte)10);
		Assertions.assertThat(maxValue).isEqualTo((byte)10);
	}


}
