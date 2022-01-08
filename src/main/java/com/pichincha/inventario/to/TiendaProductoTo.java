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
public class TiendaProductoTo {

	@NotNull
	public Long codigoTienda;

	@NotNull
	public List<Long> listaIdProductos;

}
