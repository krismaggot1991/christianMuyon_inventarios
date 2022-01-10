/**
 * 
 */
package com.pichincha.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.dto.ReporteMontoVendidoTiendaDTO;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface ReporteMontoVendidoTiendaRepository extends CrudRepository<ReporteMontoVendidoTiendaDTO, Long> {
	@Query(value = "SELECT row_number() over(ORDER BY nombre_tienda) AS id, resultado.* FROM "
			+ "(SELECT sum(pedidoDetalle.cantidad * producto.precio) monto_vendido, tienda.nombre nombre_tienda, "
			+ "producto.nombre nombre_producto FROM pedido_detalle pedidoDetalle, tienda_producto tiendaProducto, tienda tienda, producto producto "
			+ "WHERE pedidoDetalle.codigo_tienda_producto = tiendaProducto.codigo_tienda_producto "
			+ "AND tiendaProducto.codigo_tienda = tienda.codigo " + "AND tiendaProducto.id_producto = producto.id "
			+ "GROUP BY tienda.nombre, producto.nombre) resultado", nativeQuery = true)
	List<ReporteMontoVendidoTiendaDTO> obtenerReporteMontoVendidoTienda();

}
