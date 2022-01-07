/**
 * 
 */
package com.pichincha.inventario.to;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pichincha.inventario.entity.Producto;

import lombok.Data;

/**
 * @author Christian Muyon
 *
 */
@Data
public class ProductoTo {

	@NotEmpty(message = "id is required")
	@NotNull(message = "id is required")
	private Long id;

	@NotEmpty(message = "cod is required")
	@NotNull(message = "cod is required")
	private String cod;

	@NotEmpty(message = "name is required")
	@NotNull(message = "name is required")
	private String name;

	@NotEmpty(message = "price is required")
	@NotNull(message = "price is required")
	private Double price;

	@NotEmpty(message = "stock is required")
	@NotNull(message = "stock is required")
	private Integer stock;

	public Producto getProducto() {
		return Producto.builder().id(getId()).codigo(getCod()).nombre(getName()).precio(getPrice()).stock(getStock())
				.build();
	}

}
