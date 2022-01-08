/**
 * 
 */
package com.pichincha.inventario.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.Tienda;
import com.pichincha.inventario.entity.TiendaProducto;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface TiendaProductoRepository extends CrudRepository<TiendaProducto, Long> {
	List<TiendaProducto> findByTienda(Tienda tienda);

}
