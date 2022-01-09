/**
 * 
 */
package com.pichincha.inventario.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.inventario.entity.Pedido;
import com.pichincha.inventario.entity.PedidoDetalle;
import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.entity.Tienda;
import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.repository.PedidoDetalleRepository;
import com.pichincha.inventario.repository.PedidoRepository;
import com.pichincha.inventario.to.pedido.PedidoDetalleTo;
import com.pichincha.inventario.to.pedido.PedidoTo;

/**
 * @author Christian Muyon
 *
 */
@Service
public class PedidoServicio {

	@Autowired
	private ClienteServicio clienteServicio;

	@Autowired
	private TiendaServicio tiendaServicio;

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoDetalleRepository pedidoDetalleRepository;

	public void realizarPedido(PedidoTo pedidoTo) throws InventarioException {

		Pedido pedido = new Pedido();
		pedido.setCliente(clienteServicio.obtenerPorCodigo(pedidoTo.getCodigoCliente()));
		pedido.setFecha(new Date());

		for (PedidoDetalleTo pedidoDetalleTo : pedidoTo.getDetalle()) {
			this.guardarDetalle(pedido, pedidoDetalleTo);
		}

	}

	private void guardarDetalle(Pedido pedido, PedidoDetalleTo pedidoDetalleTo) throws InventarioException {
		Tienda tienda = tiendaServicio.obtenerTiendaPorCodigo(pedidoDetalleTo.getCodigoTienda());
		Producto producto = productoServicio.obtenerProductoPorId(pedidoDetalleTo.getIdProducto());
		pedido = pedidoRepository.save(pedido);
		PedidoDetalle pedidoDetalle = new PedidoDetalle(pedido, tienda, producto, pedidoDetalleTo.getCantidad());
		pedidoDetalleRepository.save(pedidoDetalle);
	}

}
