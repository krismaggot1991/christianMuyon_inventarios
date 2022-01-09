/**
 * 
 */
package com.pichincha.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.inventario.entity.Cliente;
import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.service.ClienteServicio;

/**
 * @author Christian Muyon
 *
 */
@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteServicio clienteServicio;

	@GetMapping(value = "/obtenerTodosClientes")
	public Iterable<Cliente> obtenerTodosClientes() {
		return clienteServicio.obtenerTodosClientes();
	}

	@PostMapping(value = "/guardarCliente")
	public ResponseEntity<?> guardarCiente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteGuardado = clienteServicio.guardarCliente(cliente);
			return new ResponseEntity<>(clienteGuardado, HttpStatus.CREATED);
		} catch (InventarioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/obtenerPorIdentificacion/{identificacion}")
	public ResponseEntity<?> obtenerPorIdentificacion(@PathVariable("identificacion") String identificacion) {
		try {
			Cliente cliente = clienteServicio.obtenerPorIdentificacion(identificacion);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch (InventarioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/eliminarClientePorIdentificacion/{identificacion}")
	public ResponseEntity<?> eliminarCientePorIdentificacion(@PathVariable("identificacion") String identificacion) {
		try {
			clienteServicio.eliminarCliente(identificacion);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (InventarioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/actualizarCliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteActualizado = clienteServicio.actualizarCliente(cliente);
			return new ResponseEntity<>(clienteActualizado, HttpStatus.ACCEPTED);
		} catch (InventarioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
