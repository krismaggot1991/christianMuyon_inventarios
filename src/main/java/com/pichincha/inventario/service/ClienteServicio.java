/**
 * 
 */
package com.pichincha.inventario.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.inventario.entity.Cliente;
import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.repository.ClienteRepository;

/**
 * @author Christian Muyon
 *
 */
@Service
public class ClienteServicio {

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Obtiene informacion de todos los clientes.
	 * 
	 * @return Iterable<Cliente>
	 */
	public Iterable<Cliente> obtenerTodosClientes() {
		return clienteRepository.findAll();
	}

	/**
	 * Guarda informacion de cliente
	 * 
	 * @param cliente Objeto cliente
	 * 
	 * @throws InventarioException
	 * 
	 * @return Cliente
	 */
	public Cliente guardarCliente(Cliente cliente) throws InventarioException {
		this.validarInformacionCliente(cliente);
		return clienteRepository.save(cliente);
	}

	/**
	 * Obtiene cliente por identificacion.
	 * 
	 * @param identificacion Identificacion de cliente
	 * 
	 * @throws InventarioException
	 * 
	 * @return Cliente
	 */
	public Cliente obtenerPorIdentificacion(String identificacion) throws InventarioException {
		return clienteRepository.findByIdentificacion(identificacion).stream().findFirst()
				.orElseThrow(() -> new InventarioException("Cliente no encontrado."));
	}

	/**
	 * Elimina cliente.
	 * 
	 * @param identificacion Identificacion de cliente a eliminar
	 * 
	 * @throws InventarioException
	 * 
	 * @return void
	 */
	public void eliminarCliente(String identificacion) throws InventarioException {
		Cliente cliente = this.obtenerPorIdentificacion(identificacion);
		clienteRepository.delete(cliente);
	}

	/**
	 * Actualiza informacion de cliente.
	 * 
	 * @param cliente Objeto Cliente
	 * 
	 * @throws InventarioException
	 * 
	 * @return Cliente
	 */
	public Cliente actualizarCliente(Cliente cliente) throws InventarioException {
		this.validarInformacionCliente(cliente);
		Cliente clienteObtenido = this.obtenerPorIdentificacion(cliente.getIdentificacion());
		clienteObtenido.setIdentificacion(cliente.getIdentificacion());
		clienteObtenido.setNombre(cliente.getNombre());
		clienteObtenido.setFoto(cliente.getFoto());
		this.guardarCliente(clienteObtenido);
		cliente.setCodigo(clienteObtenido.getCodigo());
		return cliente;
	}

	/**
	 * Obtiene cliente por codigo de cliente
	 * 
	 * @param codigoCliente Codigo de cliente
	 * 
	 * @throws InventarioException
	 * 
	 * @return Cliente
	 */
	public Cliente obtenerPorCodigo(Long codigoCliente) throws InventarioException {
		Optional<Cliente> optionalCliente = clienteRepository.findById(codigoCliente);
		if (optionalCliente.isPresent()) {
			return optionalCliente.get();
		} else {
			throw new InventarioException("No existe cliente con codigo: " + codigoCliente);
		}
	}

	/**
	 * Valida informacion requerida de cliente.
	 * 
	 * @param cliente Objeto Cliente
	 * 
	 * @throws InventarioException
	 * 
	 * @return void
	 */
	private void validarInformacionCliente(Cliente cliente) throws InventarioException {
		if (Objects.isNull(cliente.getIdentificacion()) || cliente.getIdentificacion().isEmpty()) {
			throw new InventarioException("La identificacion es un campo requerido.");
		}
		if (Objects.isNull(cliente.getNombre()) || cliente.getNombre().isEmpty()) {
			throw new InventarioException("El nombre es un campo requerido.");
		}
	}

}
