/**
 * 
 */
package com.pichincha.inventario.to;

import java.util.List;

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
public class TiendaProductoDetalleTo {

	@NotNull
	public TiendaTo tiendaTo;

	@NotNull
	public List<ProductoIdNombreTo> productos;

}
