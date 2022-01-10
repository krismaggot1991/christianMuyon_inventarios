/**
 * 
 */
package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.inventario.exception.InventarioException;

/**
 * @author Christian Muyon
 *
 */
@SpringBootTest
class ReporteServicioTest {

	@InjectMocks
	private ReporteServicio reporteServicio;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void deberiaDevolverFechaValida() throws InventarioException {
		assertTrue(reporteServicio.esFechaValida("20220111"),
				"La enntrada 20220111 es considerada como una fecha valida.");
	}

	@org.junit.Test(expected = InventarioException.class)
	final void deberiaLanzarExepcionPorFechaInvalida() throws InventarioException {
		reporteServicio.esFechaValida("20221309");
	}

}
