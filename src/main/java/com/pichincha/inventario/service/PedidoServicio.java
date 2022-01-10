/**
 * 
 */
package com.pichincha.inventario.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pichincha.inventario.entity.Pedido;
import com.pichincha.inventario.entity.PedidoDetalle;
import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.entity.Tienda;
import com.pichincha.inventario.entity.TiendaProducto;
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

	@Autowired
	private SolitudStockServicio solitudStockServicio;

	@Autowired
	private TiendaProductoServicio tiendaProductoServicio;

	/**
	 * Guarda pedido y su detall
	 * 
	 * @param pedidoTo Objeto PedidoTo
	 * 
	 * @throws InventarioException
	 * 
	 * @return void
	 */
	@Transactional
	public void realizarPedido(PedidoTo pedidoTo) throws InventarioException {
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteServicio.obtenerPorCodigo(pedidoTo.getCodigoCliente()));
		pedido.setFecha(new Date());
		pedido = pedidoRepository.save(pedido);
		for (PedidoDetalleTo pedidoDetalleTo : pedidoTo.getDetalle()) {
			this.guardarDetalle(pedido, pedidoDetalleTo);
		}
	}

	/**
	 * Guarda detalle del pedido y actualiza el stock del producto segun logica de
	 * negocio.
	 * 
	 * @param pedido          Objeto Pedido
	 * @param pedidoDetalleTo Objeto PedidoDetalleTo
	 * 
	 * @throws InventarioException
	 * 
	 * @return void
	 */
	private void guardarDetalle(Pedido pedido, PedidoDetalleTo pedidoDetalleTo) throws InventarioException {
		Tienda tienda = tiendaServicio.obtenerTiendaPorCodigo(pedidoDetalleTo.getCodigoTienda());
		Producto producto = productoServicio.obtenerProductoPorId(pedidoDetalleTo.getIdProducto());
		TiendaProducto tiendaProducto = tiendaProductoServicio.obtenerPorTiendaYProducto(tienda, producto);

		int stock = producto.getStock();
		stock = stock - pedidoDetalleTo.getCantidad();

		if (stock < 0) {
			stock = stock * (-1);
			if (stock > 10) {
				throw new InventarioException("Unidades no disponibles (>10)");
			} else if (stock > 5 && stock < 10) {
				solitudStockServicio.solicitarStock10(producto, pedidoDetalleTo.getCantidad());
			} else if (stock < 5) {
				solitudStockServicio.solicitarStock05(producto, pedidoDetalleTo.getCantidad());
			}
		} else {
			producto.setStock(stock);
			productoServicio.guardarProducto(producto);
		}

		PedidoDetalle pedidoDetalle = new PedidoDetalle(pedido, tiendaProducto, pedidoDetalleTo.getCantidad());
		pedidoDetalleRepository.save(pedidoDetalle);
	}

}
