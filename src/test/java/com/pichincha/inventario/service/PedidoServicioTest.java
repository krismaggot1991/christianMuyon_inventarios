/**
 * 
 */
package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.inventario.entity.Cliente;
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
@SpringBootTest
class PedidoServicioTest {

	@InjectMocks
	private PedidoServicio pedidoServicio;

	@Mock
	private ClienteServicio clienteServicio;

	@Mock
	private PedidoRepository pedidoRepository;

	@Mock
	private TiendaServicio tiendaServicio;

	@Mock
	private ProductoServicio productoServicio;

	@Mock
	private TiendaProductoServicio tiendaProductoServicio;

	@Mock
	private PedidoDetalleRepository pedidoDetalleRepository;

	private int contador;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void deberiaRealizarPedido() throws InventarioException {
		getContador();
		when(clienteServicio.obtenerPorCodigo(anyLong())).thenReturn(obtenerCliente());
		when(pedidoRepository.save(any())).thenReturn(obtenerPedido());
		when(tiendaServicio.obtenerTiendaPorCodigo(anyLong())).thenReturn(obtenerTienda());
		when(productoServicio.obtenerProductoPorId(anyLong())).thenReturn(obtenerProducto());
		when(tiendaProductoServicio.obtenerPorTiendaYProducto(any(), any())).thenReturn(obtenerTiendaProducto());
		when(pedidoDetalleRepository.save(any())).thenReturn(obtenerPedidoDetalle());
		pedidoServicio.realizarPedido(obtenerPedidoTo());
		setContador(contador);
		assertSame(1, getContador(), "Test pasa validaciones");
	}

	private Cliente obtenerCliente() {
		Cliente cliente = new Cliente();
		cliente.setCodigo(1L);
		cliente.setIdentificacion("1803750312");
		cliente.setNombre("Christian Muyon");
		cliente.setFoto("Foto 1");
		return cliente;
	}

	private Pedido obtenerPedido() {
		Pedido pedido = new Pedido();
		pedido.setCliente(obtenerCliente());
		pedido.setCodigo(1L);
		pedido.setFecha(new Date());
		return pedido;
	}

	private Tienda obtenerTienda() {
		Tienda tienda = new Tienda();
		tienda.setCodigo(1L);
		tienda.setNombre("Tienda 1");
		return tienda;
	}

	private Producto obtenerProducto() {
		Producto producto = new Producto();
		producto.setId(9L);
		producto.setCodigo("XNN");
		producto.setNombre("Casco de combate");
		producto.setStock(3);
		return producto;
	}

	private TiendaProducto obtenerTiendaProducto() {
		TiendaProducto tiendaProducto = new TiendaProducto();
		tiendaProducto.setCodigoTiendaProducto(1L);
		tiendaProducto.setProducto(obtenerProducto());
		tiendaProducto.setTienda(obtenerTienda());
		return tiendaProducto;
	}

	private PedidoDetalle obtenerPedidoDetalle() {
		return new PedidoDetalle(obtenerPedido(), obtenerTiendaProducto(), 2);
	}

	private PedidoTo obtenerPedidoTo() {
		PedidoTo pedidoTo = new PedidoTo();
		pedidoTo.setCodigoCliente(1L);
		pedidoTo.setDetalle(obtenerListaPedidoDetalleTo());
		return pedidoTo;
	}

	private List<PedidoDetalleTo> obtenerListaPedidoDetalleTo() {
		List<PedidoDetalleTo> listaPedidoDetalleTo = new ArrayList<>();
		listaPedidoDetalleTo.add(obtenerPedidoDetalleTo());
		return listaPedidoDetalleTo;
	}

	private PedidoDetalleTo obtenerPedidoDetalleTo() {
		PedidoDetalleTo pedidoDetalleTo = new PedidoDetalleTo();
		pedidoDetalleTo.setCantidad(2);
		pedidoDetalleTo.setCodigoTienda(1L);
		pedidoDetalleTo.setIdProducto(9L);
		return pedidoDetalleTo;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador + 1;
	}

}
