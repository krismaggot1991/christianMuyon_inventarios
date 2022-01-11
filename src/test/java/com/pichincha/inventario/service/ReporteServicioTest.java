/**
 * 
 */
package com.pichincha.inventario.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.inventario.entity.Cliente;
import com.pichincha.inventario.entity.dto.ReporteMontoVendidoTiendaDTO;
import com.pichincha.inventario.entity.dto.ReporteNumeroTransaccionesDTO;
import com.pichincha.inventario.entity.dto.ReporteTransaccionesClienteDTO;
import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.repository.ReporteMontoVendidoTiendaRepository;
import com.pichincha.inventario.repository.ReporteNumeroTransaccionesRepository;
import com.pichincha.inventario.repository.ReporteTransaccionesClienteRepository;

/**
 * @author Christian Muyon
 *
 */
@SpringBootTest
class ReporteServicioTest {

	@InjectMocks
	private ReporteServicio reporteServicio;

	@Mock
	private ReporteNumeroTransaccionesRepository reporteNumeroTransaccionesRepository;

	@Mock
	private ReporteMontoVendidoTiendaRepository reporteMontoVendidoTiendaRepository;

	@Mock
	private ClienteServicio clienteServicio;

	@Mock
	private ReporteTransaccionesClienteRepository reporteTransaccionesClienteRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void deberiaObtenerReporteNumeroTransacciones() {
		when(reporteNumeroTransaccionesRepository.obtenerReporteNumeroTransacciones())
				.thenReturn(obtenerListaReporteNumeroTransaccionesDTO());
		List<ReporteNumeroTransaccionesDTO> listaReporteNumeroTransaccionesDTO = reporteServicio
				.obtenerReporteNumeroTransacciones();
		assertTrue(!listaReporteNumeroTransaccionesDTO.isEmpty(),
				"Lista de ReporteNumeroTransaccionesDTO no esta vacia.");
	}

	@Test
	final void deberiaObtenerReporteMontoVendidoTienda() {
		when(reporteMontoVendidoTiendaRepository.obtenerReporteMontoVendidoTienda())
				.thenReturn(obtenerListaReporteMontoVendidoTiendaDTO());
		List<ReporteMontoVendidoTiendaDTO> listaReporteMontoVendidoTiendaDTO = reporteServicio
				.obtenerReporteMontoVendidoTienda();
		assertTrue(!listaReporteMontoVendidoTiendaDTO.isEmpty(),
				"Lista de ReporteMontoVendidoTiendaDTO no esta vacia.");
	}

	@Test
	final void deberiaObtenerReporteTransaccionesCliente() throws InventarioException {
		when(clienteServicio.obtenerPorCodigo(anyLong())).thenReturn(obtenerCliente());
		when(reporteTransaccionesClienteRepository.obtenerReporteTransaccionesCliente(anyLong(), anyString(),
				anyString())).thenReturn(obtenerListaReporteTransaccionesClienteDTO());
		List<ReporteTransaccionesClienteDTO> listaReporteTransaccionesClienteDTO = reporteServicio
				.obtenerReporteTransaccionesCliente(1L, "20220101", "20220111");
		assertTrue(!listaReporteTransaccionesClienteDTO.isEmpty(),
				"Lista de ReporteTransaccionesClienteDTO no esta vacia.");
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

	private List<ReporteNumeroTransaccionesDTO> obtenerListaReporteNumeroTransaccionesDTO() {
		List<ReporteNumeroTransaccionesDTO> listaReporteNumeroTransaccionesDTO = new ArrayList<>();
		listaReporteNumeroTransaccionesDTO.add(obtenerReporteNumeroTransaccionesDTO());
		return listaReporteNumeroTransaccionesDTO;
	}

	private ReporteNumeroTransaccionesDTO obtenerReporteNumeroTransaccionesDTO() {
		ReporteNumeroTransaccionesDTO reporteNumeroTransaccionesDTO = new ReporteNumeroTransaccionesDTO();
		LocalDate date = LocalDate.of(2020, 1, 8);
		reporteNumeroTransaccionesDTO.setFecha(date);
		reporteNumeroTransaccionesDTO.setId(1L);
		reporteNumeroTransaccionesDTO.setNombreTienda("Tienda 1");
		reporteNumeroTransaccionesDTO.setTransaccion(3);
		return reporteNumeroTransaccionesDTO;
	}

	private List<ReporteMontoVendidoTiendaDTO> obtenerListaReporteMontoVendidoTiendaDTO() {
		List<ReporteMontoVendidoTiendaDTO> listaReporteMontoVendidoTiendaDTO = new ArrayList<>();
		listaReporteMontoVendidoTiendaDTO.add(obtenerReporteMontoVendidoTiendaDTO());
		return listaReporteMontoVendidoTiendaDTO;
	}

	private ReporteMontoVendidoTiendaDTO obtenerReporteMontoVendidoTiendaDTO() {
		ReporteMontoVendidoTiendaDTO reporteMontoVendidoTiendaDTO = new ReporteMontoVendidoTiendaDTO();
		reporteMontoVendidoTiendaDTO.setId(2L);
		double valor = 120.2;
		reporteMontoVendidoTiendaDTO.setMontoVendido(valor);
		reporteMontoVendidoTiendaDTO.setNombreProducto("Producto 1");
		reporteMontoVendidoTiendaDTO.setNombreTienda("Tienda 2");
		return reporteMontoVendidoTiendaDTO;
	}

	private Cliente obtenerCliente() {
		Cliente cliente = new Cliente();
		cliente.setCodigo(1L);
		cliente.setIdentificacion("1803750312");
		cliente.setNombre("Christian Muyon");
		cliente.setFoto("Foto 1");
		return cliente;
	}

	private List<ReporteTransaccionesClienteDTO> obtenerListaReporteTransaccionesClienteDTO() {
		List<ReporteTransaccionesClienteDTO> listaReporteTransaccionesClienteDTO = new ArrayList<>();
		listaReporteTransaccionesClienteDTO.add(obtenerReporteTransaccionesClienteDTO());
		return listaReporteTransaccionesClienteDTO;
	}

	private ReporteTransaccionesClienteDTO obtenerReporteTransaccionesClienteDTO() {
		ReporteTransaccionesClienteDTO reporteTransaccionesClienteDTO = new ReporteTransaccionesClienteDTO();
		reporteTransaccionesClienteDTO.setClienteIdentificacion("1803750312");
		reporteTransaccionesClienteDTO.setClienteNombre("Christian Muyon");
		LocalDate date = LocalDate.of(2022, 1, 8);
		reporteTransaccionesClienteDTO.setFechaTransaccion(date);
		reporteTransaccionesClienteDTO.setId(3L);
		reporteTransaccionesClienteDTO.setTransaccion(1);
		return reporteTransaccionesClienteDTO;
	}

}
