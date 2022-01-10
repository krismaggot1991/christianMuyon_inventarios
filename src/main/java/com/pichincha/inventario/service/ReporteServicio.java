/**
 * 
 */
package com.pichincha.inventario.service;

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
		clienteServicio.obtenerPorCodigo(codigoCliente);
		return reporteTransaccionesClienteRepository.obtenerReporteTransaccionesCliente(codigoCliente, fechaInicio,
				fechaFin);
	}
}
