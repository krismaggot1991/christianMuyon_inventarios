/**
 * 
 */
package com.pichincha.inventario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.Pedido;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
