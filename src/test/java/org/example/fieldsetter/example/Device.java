package org.example.fieldsetter.example;

import lombok.Data;

@Data
public class Device {
	private float weight;
	private boolean digital;
	private char code;
	private byte flags;
	private Byte optionalFlags;
	private Byte[] initializers;
}
