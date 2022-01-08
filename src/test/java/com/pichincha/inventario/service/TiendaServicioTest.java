/**
 * 
 */
package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void deberiaAsignarGuardarProductoATienda() throws ServiceException {
		when(productoServicio.obtenerProductoPorId(9L)).thenReturn(Optional.of(obtenerProducto()));
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

}
