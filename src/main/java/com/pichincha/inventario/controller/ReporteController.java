/**
 * 
 */
package com.pichincha.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.inventario.entity.dto.ReporteMontoVendidoTiendaDTO;
import com.pichincha.inventario.entity.dto.ReporteNumeroTransaccionesDTO;
import com.pichincha.inventario.entity.dto.ReporteTransaccionesClienteDTO;
import com.pichincha.inventario.service.ReporteServicio;

/**
 * @author Christian Muyon
 *
 */
@RestController
@RequestMapping(value = "/api/reporte")
public class ReporteController {

	@Autowired
	private ReporteServicio reporteServicio;

	@GetMapping("/obtenerReporteNumeroTransacciones")
	public ResponseEntity<?> obtenerReporteNumeroTransacciones() {
		List<ReporteNumeroTransaccionesDTO> resultado = reporteServicio.obtenerReporteNumeroTransacciones();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@GetMapping("/obtenerReporteMontoVendidoTienda")
	public ResponseEntity<?> obtenerReporteMontoVendidoTienda() {
		List<ReporteMontoVendidoTiendaDTO> resultado = reporteServicio.obtenerReporteMontoVendidoTienda();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@GetMapping("/obtenerReporteTransaccionesCliente/{codigoCliente}/{fechaInicio}/{fechaFin}")
	public ResponseEntity<?> obtenerReporteTransaccionesCliente(@PathVariable("codigoCliente") Long codigoCliente,
			@PathVariable("fechaInicio") String fechaInicio, @PathVariable("fechaFin") String fechaFin) {
		List<ReporteTransaccionesClienteDTO> resultado = reporteServicio
				.obtenerReporteTransaccionesCliente(codigoCliente, fechaInicio, fechaFin);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

}
