/**
 * 
 */
package com.pichincha.inventario.entity.dto;

import java.time.LocalDate;

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
public class ReporteNumeroTransaccionesDTO {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "TRANSACCION")
	private Integer transaccion;

	@Column(name = "FECHA")
	private LocalDate fecha;

	@Column(name = "NOMBRE_TIENDA")
	private String nombreTienda;

}
