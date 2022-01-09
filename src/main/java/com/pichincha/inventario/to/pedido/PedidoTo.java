package com.pichincha.inventario.to.pedido;

import java.util.List;

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
public class PedidoTo {

	@NotNull
	private Long codigoCliente;

	@NotNull
	@NotEmpty
	private List<PedidoDetalleTo> detalle;

}
