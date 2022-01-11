/**
 * 
 */
package com.pichincha.inventario.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pichincha.inventario.to.ProductoToList;

/**
 * Clase de pruebas de integracion hacia APIs externas
 * 
 * @author Christian Muyon
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiExternoIntegrationTest {

	private static final String URL_MOCK_PRODUCTOS_API = "https://mocki.io/v1/5ccfa24a-6b55-41b3-90ec-29b8a28f39c4";
	private static final String MOCK_URL10 = "https://mocki.io/v1/a6c5ceff-dbbe-417c-8991-67bdace67dac";
	private static final String MOCK_URL05 = "https://mocki.io/v1/6e5f2a7f-b2bd-4674-86b6-48437f37b715";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void deberiaApiProductosDevolverDataYStatusCorrecto() {
		ResponseEntity<ProductoToList> response = testRestTemplate.getForEntity(URL_MOCK_PRODUCTOS_API,
				ProductoToList.class);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Peticion devuelve respuesta exitosa.");
		assertTrue("Repuesta no se encuentra vacia.", !response.getBody().toString().isEmpty());
	}

	@Test
	public void deberiaApiSolicitudStock05DevolverDataYStatusCorrecto() {
		ResponseEntity<ProductoToList> response = testRestTemplate.getForEntity(MOCK_URL05, ProductoToList.class);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Peticion devuelve respuesta exitosa.");
		assertTrue("Repuesta no se encuentra vacia.", !response.getBody().toString().isEmpty());
	}

	@Test
	public void deberiaApiSolicitudStock10DevolverDataYStatusCorrecto() {
		ResponseEntity<ProductoToList> response = testRestTemplate.getForEntity(MOCK_URL10, ProductoToList.class);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Peticion devuelve respuesta exitosa.");
		assertTrue("Repuesta no se encuentra vacia.", !response.getBody().toString().isEmpty());
	}

}
