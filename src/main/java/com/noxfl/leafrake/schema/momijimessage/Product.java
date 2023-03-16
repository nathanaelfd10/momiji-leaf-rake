/**
 * 
 */
package com.noxfl.leafrake.schema.momijimessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Fernando Nathanael
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class Product {

	private String hashUrl;
	private String title;
	private int price;
	private int priceDiscount;
	private int soldCount;
	private Category category;

}