package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.repository.ProductoRepository;
import com.pichincha.inventario.to.ProductoCodigoNombreTo;

@SpringBootTest
class ProductoServicioTest {

	@InjectMocks
	private ProductoServicio productoServicio;

	@Mock
	private ProductoRepository productoRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void deberiaObtenerProductosCodigoNombre() {
		when(productoRepository.findAll()).thenReturn(obtenerIterableProducto());
		List<ProductoCodigoNombreTo> listaProductoCodigoNombreTo = productoServicio.obtenerProductosCodigoNombre();
		assertTrue(!listaProductoCodigoNombreTo.isEmpty(), "Lista obtenida no se encuentra vacia");
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
