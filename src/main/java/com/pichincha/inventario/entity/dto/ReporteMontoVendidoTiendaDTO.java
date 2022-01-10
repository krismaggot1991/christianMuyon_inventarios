/**
 * 
 */
package com.pichincha.inventario.entity.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Christian Muyon
 *
 */
@Data
@Entity
public class ReporteMontoVendidoTiendaDTO {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "MONTO_VENDIDO")
	private Double montoVendido;

	@Column(name = "NOMBRE_TIENDA")
	private String nombreTienda;

	@Column(name = "NOMBRE_PRODUCTO")
	private String nombreProducto;

}
