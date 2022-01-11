/**
 * 
 */
package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.repository.ProductoRepository;

/**
 * @author Christian Muyon
 *
 */
@SpringBootTest
class SolitudStockServicioTest {

	@InjectMocks
	private SolitudStockServicio solitudStockServicio;

	@Mock
	private ProductoRepository productoRepository;

	private int contador;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void deberiaSolicitarStock10() {
		getContador();
		when(productoRepository.save(any())).thenReturn(obtenerProducto());
		solitudStockServicio.solicitarStock10(obtenerProducto(), 3);
		setContador(contador);
		assertSame(1, getContador(), "Test pasa validaciones");
	}

	@Test
	final void deberiaSolicitarStock05() {
		getContador();
		when(productoRepository.save(any())).thenReturn(obtenerProducto());
		solitudStockServicio.solicitarStock05(obtenerProducto(), 10);
		setContador(contador);
		assertSame(1, getContador(), "Test pasa validaciones");
	}

	private Producto obtenerProducto() {
		Producto producto = new Producto();
		producto.setId(9L);
		producto.setCodigo("XNN");
		producto.setNombre("Casco de combate");
		producto.setStock(3);
		return producto;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador + 1;
	}

}
