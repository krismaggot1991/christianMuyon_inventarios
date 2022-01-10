/**
 * 
 */
package com.pichincha.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.dto.ReporteNumeroTransaccionesDTO;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface ReporteNumeroTransaccionesRepository extends CrudRepository<ReporteNumeroTransaccionesDTO, Long> {
	@Query(value = "SELECT row_number() over(ORDER BY nombre_tienda) AS id, resultado.* FROM "
			+ "(SELECT count(1) as transaccion, pedido.fecha as fecha, tienda.nombre as nombre_tienda "
			+ "FROM pedido pedido, pedido_detalle pedidoDetalle, tienda_producto tiendaProducto, tienda tienda "
			+ "WHERE pedido.codigo = pedidoDetalle.codigo_pedido "
			+ "AND tiendaProducto.codigo_tienda_producto = pedidoDetalle.codigo_tienda_producto "
			+ "AND tiendaProducto.codigo_tienda = tienda.codigo "
			+ "GROUP BY pedido.fecha, tienda.nombre) resultado", nativeQuery = true)
	List<ReporteNumeroTransaccionesDTO> obtenerReporteNumeroTransacciones();

}
