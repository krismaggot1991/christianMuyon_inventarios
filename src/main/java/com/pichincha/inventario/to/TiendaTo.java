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
 * @author Christian Muyon
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TiendaTo {

	@NotEmpty
	@NotNull
	private Long codigo;

	@NotEmpty
	@NotNull
	private String nombre;
}
