package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.repository.ProductoRepository;
import com.pichincha.inventario.to.ProductoCodigoNombreTo;

/**
 * @author Christian Muyon
 *
 */
@SpringBootTest
class ProductoServicioTest {

	@InjectMocks
	private ProductoServicio productoServicio;

	@Mock
	private ProductoRepository productoRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void deberiaObtenerProductosCodigoNombre() {
		when(productoRepository.findAll()).thenReturn(obtenerIterableProducto());
		List<ProductoCodigoNombreTo> listaProductoCodigoNombreTo = productoServicio.obtenerProductosCodigoNombre();
		assertTrue(!listaProductoCodigoNombreTo.isEmpty(), "Lista obtenida no se encuentra vacia");
	}

	@Test
	final void deberiaActualizarStockProducto() throws InventarioException {
		when(productoRepository.findById(9L)).thenReturn(obtenerProductoOptional());
		when(productoServicio.guardarProducto(obtenerProducto1())).thenReturn(obtenerProducto1());
		Producto producto = productoServicio.actualizarStockProducto(9L, 3);
		assertSame(3, producto.getStock(), "El stock actualizado es 3.");
	}

	@org.junit.Test(expected = InventarioException.class)
	void deberiaLanzarExepcionCuandoActualizaStock0() throws InventarioException {
		when(productoRepository.findById(9L)).thenReturn(obtenerProductoOptional());
		when(productoServicio.guardarProducto(obtenerProducto1())).thenReturn(obtenerProducto1());
		productoServicio.actualizarStockProducto(9L, 0);
	}

	@org.junit.Test(expected = InventarioException.class)
	void deberiaLanzarExepcionCuandoActualizaStockMenorCero() throws InventarioException {
		when(productoRepository.findById(9L)).thenReturn(obtenerProductoOptional());
		when(productoServicio.guardarProducto(obtenerProducto1())).thenReturn(obtenerProducto1());
		productoServicio.actualizarStockProducto(9L, -1);
	}

	private Optional<Producto> obtenerProductoOptional() {
		return Optional.of(obtenerProducto1());
	}

	private Iterable<Producto> obtenerIterableProducto() {
		List<Producto> listaProductos = new ArrayList<>();
		listaProductos.add(obtenerProducto1());
		listaProductos.add(obtenerProducto2());

		Iterable<Producto> iterableProducto = listaProductos;
		return iterableProducto;
	}

	private Producto obtenerProducto1() {
		Producto producto = new Producto();
		producto.setId(9L);
		producto.setCodigo("XNN");
		producto.setNombre("Casco de combate");
		producto.setStock(3);
		return producto;
	}

	private Producto obtenerProducto2() {
		Producto producto = new Producto();
		producto.setId(28L);
		producto.setCodigo("L99");
		producto.setNombre("Bicicleta");
		producto.setStock(90);
		return producto;
	}

}
