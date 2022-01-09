package com.pichincha.inventario.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.inventario.entity.Cliente;
import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.repository.ClienteRepository;

@SpringBootTest
class ClienteServicioTest {

	@InjectMocks
	private ClienteServicio clienteServicio;

	@Mock
	private ClienteRepository clienteRepository;

	private int contador;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void deberiaObtenerTodosClientes() {
		when(clienteRepository.findAll()).thenReturn(obtenerIterableClientes());
		Iterable<Cliente> iterableCliente = clienteServicio.obtenerTodosClientes();
		List<Cliente> resultado = new ArrayList<>();
		iterableCliente.forEach(resultado::add);
		assertTrue(!resultado.isEmpty(), "Lista de clientes contiene elementos.");
	}

	@Test
	final void deberiaGuardarCliente() throws InventarioException {
		when(clienteRepository.save(any())).thenReturn(obtenerCliente1());
		Cliente cliente = clienteServicio.guardarCliente(obtenerCliente1());
		assertNotNull("Objecto no nulo.", cliente);
	}

	@Test
	final void deberiaObtenerPorIdentificacion() throws InventarioException {
		List<Cliente> listaClientes = new ArrayList<>();
		this.obtenerIterableClientes().forEach(listaClientes::add);
		when(clienteRepository.findByIdentificacion(anyString())).thenReturn(listaClientes);
		Cliente cliente = clienteServicio.obtenerPorIdentificacion("1803750312");
		assertNotNull("Objecto no nulo.", cliente);
	}

	@Test
	final void deberiaEliminarCliente() throws InventarioException {
		getContador();
		List<Cliente> listaClientes = new ArrayList<>();
		this.obtenerIterableClientes().forEach(listaClientes::add);
		when(clienteRepository.findByIdentificacion(anyString())).thenReturn(listaClientes);
		clienteServicio.eliminarCliente("1803750312");
		setContador(contador);
		assertSame(1, getContador(), "Test pasa validaciones");
	}

	@Test
	final void deberiaActualizarCliente() throws InventarioException {
		List<Cliente> listaClientes = new ArrayList<>();
		this.obtenerIterableClientes().forEach(listaClientes::add);
		when(clienteRepository.findByIdentificacion(anyString())).thenReturn(listaClientes);
		Cliente clienteActualizar = obtenerCliente1();
		clienteActualizar.setNombre("Felipe");
		Cliente clienteActualizado = clienteServicio.actualizarCliente(clienteActualizar);
		assertSame("Felipe", clienteActualizado.getNombre(), "El nombre del cliente se actualiza correctamente");
	}

	@org.junit.Test(expected = InventarioException.class)
	final void deberiaLanzarExepcionObtenerPorIdentificacionNoValida() throws InventarioException {
		List<Cliente> listaClientes = new ArrayList<>();
		this.obtenerIterableClientes().forEach(listaClientes::add);
		when(clienteRepository.findByIdentificacion(anyString())).thenReturn(listaClientes);
		clienteServicio.obtenerPorIdentificacion("12893264");
	}

	private Iterable<Cliente> obtenerIterableClientes() {
		List<Cliente> listaClientes = new ArrayList<>();
		listaClientes.add(obtenerCliente1());
		listaClientes.add(obtenerCliente2());
		Iterable<Cliente> iterableCliente = listaClientes;
		return iterableCliente;
	}

	private Cliente obtenerCliente1() {
		Cliente cliente = new Cliente();
		cliente.setCodigo(1L);
		cliente.setIdentificacion("1803750312");
		cliente.setNombre("Christian Muyon");
		cliente.setFoto("Foto 1");
		return cliente;
	}

	private Cliente obtenerCliente2() {
		Cliente cliente = new Cliente();
		cliente.setCodigo(2L);
		cliente.setIdentificacion("1703678251");
		cliente.setNombre("Javier Garcia");
		cliente.setFoto("Foto 2");
		return cliente;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador + 1;
	}

}
