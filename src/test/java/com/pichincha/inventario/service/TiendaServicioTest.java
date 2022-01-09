/**
 * 
 */
package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.entity.Tienda;
import com.pichincha.inventario.entity.TiendaProducto;
import com.pichincha.inventario.exception.ServiceException;
import com.pichincha.inventario.repository.TiendaProductoRepository;
import com.pichincha.inventario.repository.TiendaRepository;
import com.pichincha.inventario.to.TiendaProductoDetalleTo;
import com.pichincha.inventario.to.TiendaProductoTo;

/**
 * @author Christian Muyon
 *
 */
@SpringBootTest
class TiendaServicioTest {

	@InjectMocks
	private TiendaServicio tiendaServicio;

	@Mock
	private ProductoServicio productoServicio;

	@Mock
	private TiendaProductoRepository tiendaProductoRepository;

	@Mock
	private TiendaRepository tiendaRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void deberiaAsignarGuardarProductosATienda() throws ServiceException {
		when(tiendaRepository.findById(1L)).thenReturn(Optional.of(obtenerTienda()));
		when(tiendaProductoRepository.findByTienda(any())).thenReturn(obtenerListaTiendaProducto());
		when(tiendaProductoRepository.save(any())).thenReturn(obtenerTiendaProducto());
		when(productoServicio.obtenerProductoPorId(anyLong())).thenReturn(obtenerProducto2());
		TiendaProductoDetalleTo tiendaProductoDetalleTo = tiendaServicio
				.asignarGuardarProductosATienda(obtenerTiendaProductoTo());
		assertNotNull(tiendaProductoDetalleTo, "Objeto no es nulo");
	}

	@Test
	final void deberiaAsignarGuardarProductoATienda() throws ServiceException {
		when(productoServicio.obtenerProductoPorId(9L)).thenReturn(obtenerProducto());
		when(tiendaProductoRepository.save(any())).thenReturn(obtenerTiendaProducto());
		TiendaProducto tiendaProducto = tiendaServicio.asignarGuardarProductoATienda(obtenerTienda(), 9L);
		assertNotNull(tiendaProducto, "Objeto no es nulo");
	}

	private Producto obtenerProducto() {
		Producto producto = new Producto();
		producto.setId(9L);
		producto.setCodigo("XNN");
		producto.setNombre("Casco de combate");
		producto.setStock(3);
		return producto;
	}

	private Producto obtenerProducto2() {
		Producto producto = new Producto();
		producto.setId(8L);
		producto.setCodigo("NEU");
		producto.setNombre("Calzado");
		producto.setStock(5);
		return producto;
	}

	private Tienda obtenerTienda() {
		Tienda tienda = new Tienda();
		tienda.setCodigo(1L);
		tienda.setNombre("Tienda 1");
		return tienda;
	}

	private TiendaProducto obtenerTiendaProducto() {
		TiendaProducto tiendaProducto = new TiendaProducto();
		tiendaProducto.setCodigoTiendaProducto(1L);
		tiendaProducto.setProducto(obtenerProducto());
		tiendaProducto.setTienda(obtenerTienda());
		return tiendaProducto;
	}

	private TiendaProducto obtenerTiendaProducto2() {
		TiendaProducto tiendaProducto = new TiendaProducto();
		tiendaProducto.setCodigoTiendaProducto(2L);
		tiendaProducto.setProducto(obtenerProducto2());
		tiendaProducto.setTienda(obtenerTienda());
		return tiendaProducto;
	}

	private List<TiendaProducto> obtenerListaTiendaProducto() {
		List<TiendaProducto> listaTiendaProducto = new ArrayList<>();
		listaTiendaProducto.add(obtenerTiendaProducto());
		listaTiendaProducto.add(obtenerTiendaProducto2());
		return listaTiendaProducto;
	}

	private TiendaProductoTo obtenerTiendaProductoTo() {
		TiendaProductoTo tiendaProductoTo = new TiendaProductoTo();
		tiendaProductoTo.setCodigoTienda(1L);
		List<Long> listaIdProductos = new ArrayList<>();
		listaIdProductos.add(8L);
		listaIdProductos.add(9L);
		tiendaProductoTo.setListaIdProductos(listaIdProductos);
		return tiendaProductoTo;
	}

}
