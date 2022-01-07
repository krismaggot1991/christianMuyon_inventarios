/**
 * 
 */
package com.pichincha.inventario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.Producto;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
