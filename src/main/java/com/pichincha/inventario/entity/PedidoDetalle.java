/**
 * 
 */
package com.pichincha.inventario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Christian Muyon
 *
 */
@Data
@Entity
@Table(name = "PEDIDO_DETALLE")
public class PedidoDetalle implements Serializable {

	private static final long serialVersionUID = 5784927617391752394L;

	public PedidoDetalle(Pedido pedido, TiendaProducto tiendaProducto, Integer cantidad) {
		super();
		this.pedido = pedido;
		this.tiendaProducto = tiendaProducto;
		this.cantidad = cantidad;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO")
	private Long codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_PEDIDO")
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_TIENDA_PRODUCTO")
	private TiendaProducto tiendaProducto;

	@Column(name = "CANTIDAD")
	private Integer cantidad;

}
