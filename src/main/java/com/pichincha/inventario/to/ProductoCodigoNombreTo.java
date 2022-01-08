package com.pichincha.inventario.to;

import lombok.Builder;
import lombok.Data;

/**
 * @author Christian Muyon
 *
 */
@Data
@Builder
public class ProductoCodigoNombreTo {

	private String codigo;

	private String nombre;

}
