package org.example.fieldsetter.example;

import lombok.Data;

@Data
public class Product {
	private long id;
	private String name;
	private Character electricClass;
	private Float weight;
	private Double length;
	private Long optionalParameter;
}
