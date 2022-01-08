/**
 * 
 */
package com.pichincha.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.inventario.exception.ServiceException;
import com.pichincha.inventario.service.TiendaServicio;
import com.pichincha.inventario.to.TiendaProductoDetalleTo;
import com.pichincha.inventario.to.TiendaProductoTo;

/**
 * @author Christian Muyon
 *
 */
@RestController
@RequestMapping(value = "/api/tienda")
public class TiendaController {

	@Autowired
	private TiendaServicio tiendaServicio;

	@PostMapping("/asignarProductosATienda")
	public ResponseEntity<?> asignarProductosATienda(@RequestBody TiendaProductoTo tiendaProductoTo) {
		try {
			TiendaProductoDetalleTo tiendaProductoDetalleTo = tiendaServicio
					.asignarGuardarProductosATienda(tiendaProductoTo);
			return new ResponseEntity<>(tiendaProductoDetalleTo, HttpStatus.ACCEPTED);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
