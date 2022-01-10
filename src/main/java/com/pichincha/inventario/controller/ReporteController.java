/**
 * 
 */
package com.pichincha.inventario.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.inventario.entity.dto.ReporteMontoVendidoTiendaDTO;
import com.pichincha.inventario.entity.dto.ReporteNumeroTransaccionesDTO;
import com.pichincha.inventario.entity.dto.ReporteTransaccionesClienteDTO;
import com.pichincha.inventario.exception.InventarioException;
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
			@PathVariable("fechaInicio") String fechaInicio, @PathVariable("fechaFin") String fechaFin)
			throws IOException {
		FileWriter fileWriter = null;
		try {
			List<ReporteTransaccionesClienteDTO> resultado = reporteServicio
					.obtenerReporteTransaccionesCliente(codigoCliente, fechaInicio, fechaFin);

			/* Construccion de contenido de archivo */
			StringBuilder fileContent = new StringBuilder(
					"ID,TRANSACCION,FECHA_TRANSACCION,CLIENTE_IDENTIFICACION,CLIENTE_NOMBRE\n");
			for (ReporteTransaccionesClienteDTO reporteTransaccionesClienteDTO : resultado) {
				fileContent.append(reporteTransaccionesClienteDTO.getId()).append(",")
						.append(reporteTransaccionesClienteDTO.getTransaccion()).append(",")
						.append(reporteTransaccionesClienteDTO.getFechaTransaccion()).append(",")
						.append(reporteTransaccionesClienteDTO.getClienteIdentificacion()).append(",")
						.append(reporteTransaccionesClienteDTO.getClienteNombre()).append("\n");
			}

			String fileName = "reporteTransaccionesCliente.csv";
			fileWriter = new FileWriter(fileName);
			fileWriter.write(fileContent.toString());
			fileWriter.flush();

			File file = new File(fileName);

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");

			return ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/txt")).body(resource);
		} catch (InventarioException | IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if (fileWriter != null) {
				fileWriter.close();
			}
		}

	}

}
