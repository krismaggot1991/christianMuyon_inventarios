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

/**
 * @author Christian Muyon
 *
 */
@Entity
@Table(name = "PRODUCTO")
public class Producto implements Serializable {

	private static final long serialVersionUID = -7515474921514082548L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CODIGO")
	private String codigo;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "PRECIO")
	private Double precio;

	@Column(name = "STOCK")
	private Integer stock;

}
