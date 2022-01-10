/**
 * 
 */
package com.pichincha.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pichincha.inventario.entity.dto.ReporteNumeroTransaccionesDTO;
import com.pichincha.inventario.repository.ReporteNumeroTransaccionesRepository;

/**
 * @author Christian Muyon
 *
 */
@Service
public class ReporteServicio {

	@Autowired
	private ReporteNumeroTransaccionesRepository reporteNumeroTransaccionesRepository;

	/**
	 * Obtiene report de numero de transacciones de pedidos de productos realizados
	 * por clientes, agrupadas por tienda y fecha
	 * 
	 * @return List<ReporteNumeroTransaccionesDTO> Lista de objetos
	 *         ReporteNumeroTransaccionesDTO
	 */
	@Transactional(readOnly = true)
	public List<ReporteNumeroTransaccionesDTO> obtenerReporteNumeroTransacciones() {
		return reporteNumeroTransaccionesRepository.obtenerReporteNumeroTransacciones();
	}
}
