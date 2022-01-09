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

import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.service.PedidoServicio;
import com.pichincha.inventario.to.pedido.PedidoTo;

/**
 * @author Christian Muyon
 *
 */
@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoServicio pedidoServicio;

	@PostMapping("/realizarPedido")
	public ResponseEntity<?> realizarPedido(@RequestBody PedidoTo pedidoTo) {
		try {
			pedidoServicio.realizarPedido(pedidoTo);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (InventarioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
