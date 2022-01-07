/**
 * 
 */
package com.pichincha.inventario.to;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author Christian Muyon
 *
 */
@Data
public class ProductoToList {

	@NotEmpty
	private List<ProductoTo> prods;

	public ProductoToList() {
		prods = new ArrayList<>();
	}
}
