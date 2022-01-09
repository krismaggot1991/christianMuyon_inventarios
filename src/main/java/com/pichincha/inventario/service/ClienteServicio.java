/**
 * 
 */
package com.pichincha.inventario.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.inventario.entity.Cliente;
import com.pichincha.inventario.exception.ServiceException;
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
	 * @throws ServiceException
	 * 
	 * @return Cliente
	 */
	public Cliente guardarCliente(Cliente cliente) throws ServiceException {
		this.validarInformacionCliente(cliente);
		return clienteRepository.save(cliente);
	}

	/**
	 * Obtiene cliente por identificacion.
	 * 
	 * @param identificacion Identificacion de cliente
	 * 
	 * @throws ServiceException
	 * 
	 * @return Cliente
	 */
	public Cliente obtenerPorIdentificacion(String identificacion) throws ServiceException {
		return clienteRepository.findByIdentificacion(identificacion).stream().findFirst()
				.orElseThrow(() -> new ServiceException("Cliente no encontrado."));
	}

	/**
	 * Elimina cliente.
	 * 
	 * @param identificacion Identificacion de cliente a eliminar
	 * 
	 * @throws ServiceException
	 * 
	 * @return void
	 */
	public void eliminarCliente(String identificacion) throws ServiceException {
		Cliente cliente = this.obtenerPorIdentificacion(identificacion);
		clienteRepository.delete(cliente);
	}

	/**
	 * Actualiza informacion de cliente.
	 * 
	 * @param cliente Objeto Cliente
	 * 
	 * @throws ServiceException
	 * 
	 * @return Cliente
	 */
	public Cliente actualizarCliente(Cliente cliente) throws ServiceException {
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
	 * Valida informacion requerida de cliente.
	 * 
	 * @param cliente Objeto Cliente
	 * 
	 * @throws ServiceException
	 * 
	 * @return void
	 */
	private void validarInformacionCliente(Cliente cliente) throws ServiceException {
		if (Objects.isNull(cliente.getIdentificacion()) || cliente.getIdentificacion().isEmpty()) {
			throw new ServiceException("La identificacion es un campo requerido.");
		}
		if (Objects.isNull(cliente.getNombre()) || cliente.getNombre().isEmpty()) {
			throw new ServiceException("El nombre es un campo requerido.");
		}
	}

}
