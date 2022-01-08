package com.pichincha.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.inventario.exception.ServiceException;
import com.pichincha.inventario.service.ProductoServicio;
import com.pichincha.inventario.to.ProductoCodigoNombreTo;

/**
 * @author Christian Muyon
 *
 */
@RestController
@RequestMapping(value = "/api/producto")
public class ProductoController {

	@Autowired
	private ProductoServicio productoServicio;

	@GetMapping(value = "/obtenerProductosCodigoNombre")
	public List<ProductoCodigoNombreTo> obtenerProductosCodigoNombre() {
		return productoServicio.obtenerProductosCodigoNombre();
	}

	@PutMapping(value = "/actualizarStock/{idProducto}/{stock}")
	public ResponseEntity<?> actualizarStockProducto(@PathVariable("idProducto") Long idProducto,
			@PathVariable("stock") int stock) {
		try {
			return new ResponseEntity<>(productoServicio.actualizarStockProducto(idProducto, stock),
					HttpStatus.ACCEPTED);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
