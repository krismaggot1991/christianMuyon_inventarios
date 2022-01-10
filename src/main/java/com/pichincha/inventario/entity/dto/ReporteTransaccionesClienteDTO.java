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
public class ReporteTransaccionesClienteDTO {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "TRANSACCION")
	private Integer transaccion;

	@Column(name = "FECHA_TRANSACCION")
	private LocalDate fechaTransaccion;

	@Column(name = "CLIENTE_IDENTIFICACION")
	private String clienteIdentificacion;

	@Column(name = "CLIENTE_NOMBRE")
	private String clienteNombre;

}
