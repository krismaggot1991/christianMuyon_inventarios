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
import com.pichincha.inventario.repository.ReporteMontoVendidoTiendaRepository;
import com.pichincha.inventario.repository.ReporteNumeroTransaccionesRepository;

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
}
