/**
 * 
 */
package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.entity.Tienda;
import com.pichincha.inventario.entity.TiendaProducto;
import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.repository.TiendaProductoRepository;

/**
 * @author Christian Muyon
 *
 */
@SpringBootTest
class TiendaProductoServicioTest {

	@InjectMocks
	private TiendaProductoServicio tiendaProductoServicio;

	@Mock
	private TiendaProductoRepository tiendaProductoRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void deberiaObtenerPorTiendaYProducto() throws InventarioException {
		when(tiendaProductoRepository.findByTiendaAndProducto(any(), any()))
				.thenReturn(obtenerListaLlenaTiendaProducto());
		TiendaProducto tiendaProducto = tiendaProductoServicio.obtenerPorTiendaYProducto(any(), any());
		assertNotNull(tiendaProducto, "Objeto tiendaProducto no es nulo.");
	}

	@org.junit.Test(expected = InventarioException.class)
	final void deberiaLanzarExepcionAlObtenerPorTiendaYProductoNoExistentes() throws InventarioException {
		when(tiendaProductoRepository.findByTiendaAndProducto(any(), any()))
				.thenReturn(obtenerListaVaciaTiendaProducto());
		tiendaProductoServicio.obtenerPorTiendaYProducto(any(), any());
	}

	private List<TiendaProducto> obtenerListaLlenaTiendaProducto() {
		List<TiendaProducto> listaTiendaProducto = new ArrayList<>();
		listaTiendaProducto.add(obtenerTiendaProducto());
		return listaTiendaProducto;
	}

	private List<TiendaProducto> obtenerListaVaciaTiendaProducto() {
		List<TiendaProducto> listaTiendaProducto = new ArrayList<>();
		return listaTiendaProducto;
	}

	private TiendaProducto obtenerTiendaProducto() {
		TiendaProducto tiendaProducto = new TiendaProducto();
		tiendaProducto.setCodigoTiendaProducto(1L);
		tiendaProducto.setTienda(obtenerTienda());
		tiendaProducto.setProducto(obtenerProducto());
		return tiendaProducto;
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

}
