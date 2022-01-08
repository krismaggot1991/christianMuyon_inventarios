/**
 * 
 */
package com.pichincha.inventario.to;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HP
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoIdNombreTo {

	@NotEmpty
	@NotNull
	private Long id;

	@NotEmpty
	@NotNull
	private String nombre;

}
