/**
 * 
 */
package com.pichincha.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.repository.ProductoRepository;
import com.pichincha.inventario.to.pedido.PedidoExtraTo;

/**
 * @author Christian Muyon
 *
 */
@Service
public class SolitudStockServicio {

	@Autowired
	private ProductoRepository productoRepository;

	private static final String MOCK_URL10 = "https://mocki.io/v1/a6c5ceff-dbbe-417c-8991-67bdace67dac";
	private static final String MOCK_URL05 = "https://mocki.io/v1/6e5f2a7f-b2bd-4674-86b6-48437f37b715";

	/**
	 * Solicita 10 unidades de stock extra. El stock extra es sumado al stock actual
	 * del producto, a dicha suma se le resta la cantidad solicitada del produco por
	 * el cliente
	 * 
	 * @param producto              Objeto producto
	 * @param cantidadPedidoCliente Canntidad olicitada del producto por el cliente
	 * 
	 * @return void
	 */
	public void solicitarStock10(Producto producto, int cantidadPedidoCliente) {
		RestTemplate restTemplate = new RestTemplate();
		procesar(producto, restTemplate, MOCK_URL10, cantidadPedidoCliente);
	}

	/**
	 * Solicita 5 unidades de stock extra. El stock extra es sumado al stock actual
	 * del producto, a dicha suma se le resta la cantidad solicitada del produco por
	 * el cliente. Metodo ejecutado de forma asincronica.
	 * 
	 * @param producto              Objeto producto
	 * @param cantidadPedidoCliente Canntidad olicitada del producto por el cliente
	 * 
	 * @return void
	 */
	@Async
	public void solicitarStock05(Producto producto, int cantidadPedidoCliente) {
		RestTemplate restTemplate = new RestTemplate();
		procesar(producto, restTemplate, MOCK_URL05, cantidadPedidoCliente);
	}

	/**
	 * Actualiza el stock de un determinado prducto, suma el stock actual del
	 * producto mas el stock extra de servicio mock, a dicha suma se le resta la
	 * cantidad solicitada del produco por el cliente
	 * 
	 * @param producto              Objeto producto
	 * @param restTemplate          Objeto RestTemplate
	 * @param url                   Url de servicio mock de stock extra
	 * @param cantidadPedidoCliente Canntidad olicitada del producto por el cliente
	 * 
	 * @return void
	 */
	private void procesar(Producto producto, RestTemplate restTemplate, String url, int cantidadPedidoCliente) {
		ResponseEntity<PedidoExtraTo> response = restTemplate.getForEntity(url, PedidoExtraTo.class);
		PedidoExtraTo pedidoExtraTo = response.getBody();

		int stockActual = producto.getStock();
		int stockExtra = pedidoExtraTo.getStock();

		stockActual = stockActual + stockExtra - cantidadPedidoCliente;
		producto.setStock(stockActual);

		productoRepository.save(producto);
	}
}
