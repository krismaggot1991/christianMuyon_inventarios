/**
 * 
 */
package com.pichincha.inventario.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pichincha.inventario.to.ProductoToList;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Christian Muyon
 *
 */
@Slf4j
@Component
public class StartApp {

	private static final String URL_MOCK_PRODUCTOS_API = "https://mocki.io/v1/5ccfa24a-6b55-41b3-90ec-29b8a28f39c4";

	/**
	 * Ejecuta acciones una vez iniciada la aplicacion
	 * 
	 * @author Christian Muyon Rivera
	 * 
	 * @return void
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void startApp() {
		log.info("Programa arranca...");
		inicializarProductos();
	}

	/**
	 * Consume API mock de productos para almacenarlos en tabla PRODUCTO
	 * 
	 * @return void
	 */
	private void inicializarProductos() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<ProductoToList> response = restTemplate.getForEntity(URL_MOCK_PRODUCTOS_API,
				ProductoToList.class);
		ProductoToList productoToList = response.getBody();

		System.out.println("productoToList.getProds().size(): " + productoToList.getProds().size());

		productoToList.getProds().forEach(z -> {
			System.out.println("z.getName(): " + z.getName());
		});
	}

}
