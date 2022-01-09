/**
 * 
 */
package com.pichincha.inventario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 4452082414857432363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO")
	private Long codigo;

	@Column(name = "IDENTIFICACION")
	@NotNull
	@NotEmpty(message = "La identificacion es un campo obligatorio")
	private String identificacion;

	@Column(name = "NOMBRE")
	@NotNull
	@NotEmpty(message = "La identificacion es un campo obligatorio")
	private String nombre;

	@Column(name = "FOTO")
	private String foto;

}
