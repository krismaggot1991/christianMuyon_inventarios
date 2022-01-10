/**
 * 
 */
package com.pichincha.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.dto.ReporteTransaccionesClienteDTO;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface ReporteTransaccionesClienteRepository extends CrudRepository<ReporteTransaccionesClienteDTO, Long> {
	@Query(value = "SELECT row_number() over(ORDER BY fecha_transaccion) AS id, resultado.* FROM "
			+ "(select count(1) as transaccion, pedido.fecha as fecha_transaccion, cliente.identificacion as cliente_identificacion, "
			+ "cliente.nombre as cliente_nombre " + "from pedido pedido, cliente cliente "
			+ "where pedido.codigo_cliente = cliente.codigo " + "and pedido.codigo_cliente = ?1 "
			+ "and pedido.fecha BETWEEN ?2 AND ?3 "
			+ "GROUP BY pedido.fecha,cliente.identificacion) resultado", nativeQuery = true)
	List<ReporteTransaccionesClienteDTO> obtenerReporteTransaccionesCliente(Long codigoCliente, String fechaInicio,
			String fechaFin);

}
