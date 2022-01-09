/**
 * 
 */
package com.pichincha.inventario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.PedidoDetalle;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface PedidoDetalleRepository extends CrudRepository<PedidoDetalle, Long> {

}
