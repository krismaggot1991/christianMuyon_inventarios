/**
 * 
 */
package com.pichincha.inventario.to.pedido;

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
public class PedidoDetalleTo {

	@NotNull
	private Long codigoTienda;

	@NotNull
	private Long idProducto;

	@NotNull
	private Integer cantidad;

}
