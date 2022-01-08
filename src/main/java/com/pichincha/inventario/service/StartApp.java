/**
 * 
 */
package com.pichincha.inventario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Ejecuta acciones una vez iniciada la aplicacion
	 * 
	 * @return void
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void startApp() {
		log.info("Arranca aplicacion...");
		this.inicializarProductos();
		this.inicializarTiendas();
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
		productoToList.getProds().forEach(itemProducto -> productoServicio.guardarProducto(itemProducto.getProducto()));
	}

	/**
	 * Registr tiendas en base de datos
	 * 
	 * @return void
	 * 
	 */
	private void inicializarTiendas() {
		List<String> listaSql = obtenerListaSqlTienda();
		listaSql.forEach(sql -> executeSql(sql));
	}

	/**
	 * Obtiene lista de sentencias SQL para registro de tiendas
	 * 
	 * @return List<String>
	 * 
	 */
	private List<String> obtenerListaSqlTienda() {
		String sentenciaSql1 = "INSERT INTO TIENDA (CODIGO, NOMBRE) VALUES (1, 'Tienda 1')";
		String sentenciaSql2 = "INSERT INTO TIENDA (CODIGO, NOMBRE) VALUES (2, 'Tienda 2')";
		String sentenciaSql3 = "INSERT INTO TIENDA (CODIGO, NOMBRE) VALUES (3, 'Tienda 3')";
		String sentenciaSql4 = "INSERT INTO TIENDA (CODIGO, NOMBRE) VALUES (4, 'Tienda 4')";

		List<String> listaSql = new ArrayList<>();
		listaSql.add(sentenciaSql1);
		listaSql.add(sentenciaSql2);
		listaSql.add(sentenciaSql3);
		listaSql.add(sentenciaSql4);

		return listaSql;
	}

	/**
	 * Ejecuta sentencia SQL
	 * 
	 * @param sql Sentencia SQL
	 * 
	 * @return void
	 * 
	 */
	private void executeSql(String sql) {
		try {
			jdbcTemplate.execute(sql);
		} catch (DataAccessException e) {
			log.error("Error: " + e.getMessage());
		}
	}

}
