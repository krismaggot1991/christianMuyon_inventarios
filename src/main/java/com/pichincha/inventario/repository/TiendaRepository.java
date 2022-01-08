/**
 * 
 */
package com.pichincha.inventario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.Tienda;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface TiendaRepository extends CrudRepository<Tienda, Long> {

}
