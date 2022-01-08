package com.pichincha.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
