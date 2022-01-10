/**
 * 
 */
package com.pichincha.inventario.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class ReporteServicio {

	@Autowired
	private ReporteNumeroTransaccionesRepository reporteNumeroTransaccionesRepository;

	@Autowired
	private ReporteMontoVendidoTiendaRepository reporteMontoVendidoTiendaRepository;

	@Autowired
	private ReporteTransaccionesClienteRepository reporteTransaccionesClienteRepository;

	@Autowired
	private ClienteServicio clienteServicio;

	final static String FORMATO_FECHA = "yyyyMMdd";

	/**
	 * Obtiene reporte de numero de transacciones de pedidos de productos realizados
	 * por clientes, agrupadas por tienda y fecha
	 * 
	 * @return List<ReporteNumeroTransaccionesDTO> Lista de objetos
	 *         ReporteNumeroTransaccionesDTO
	 */
	@Transactional(readOnly = true)
	public List<ReporteNumeroTransaccionesDTO> obtenerReporteNumeroTransacciones() {
		return reporteNumeroTransaccionesRepository.obtenerReporteNumeroTransacciones();
	}

	/**
	 * Obtiene reporte de monto vendido por tienda y producto
	 * 
	 * @return List<ReporteMontoVendidoTiendaDTO> Lista de objetos
	 *         ReporteMontoVendidoTiendaDTO
	 */
	@Transactional(readOnly = true)
	public List<ReporteMontoVendidoTiendaDTO> obtenerReporteMontoVendidoTienda() {
		return reporteMontoVendidoTiendaRepository.obtenerReporteMontoVendidoTienda();
	}

	/**
	 * Obtiene reporte de transacciones de cliente
	 * 
	 * @param codigoCliente Codigo de cliente
	 * @param fechaInicio   Fecha de inicio
	 * @param fechaFin      Fecha fin
	 * 
	 * @return List<ReporteTransaccionesClienteDTO> Lista de objetos
	 *         ReporteTransaccionesClienteDTO
	 */
	@Transactional(readOnly = true)
	public List<ReporteTransaccionesClienteDTO> obtenerReporteTransaccionesCliente(Long codigoCliente,
			String fechaInicio, String fechaFin) throws InventarioException {
		this.esFechaValida(fechaInicio);
		this.esFechaValida(fechaFin);
		clienteServicio.obtenerPorCodigo(codigoCliente);
		return reporteTransaccionesClienteRepository.obtenerReporteTransaccionesCliente(codigoCliente, fechaInicio,
				fechaFin);
	}

	/**
	 * Valida que fecha String tenga el formato correcto. Formato esperado:
	 * yyyyMMdd.
	 * 
	 * @param fecha Fecha String
	 * 
	 * @return List<ReporteTransaccionesClienteDTO> Lista de objetos
	 *         ReporteTransaccionesClienteDTO
	 */
	public boolean esFechaValida(String fecha) throws InventarioException {
		try {
			DateFormat df = new SimpleDateFormat(FORMATO_FECHA);
			df.setLenient(false);
			df.parse(fecha);
			return true;
		} catch (ParseException e) {
			throw new InventarioException("La fecha ingresada: " + fecha
					+ " no se encuentra en el formato de fecha correcto. El formato esperado es yyyyMMdd.");
		}
	}
}
