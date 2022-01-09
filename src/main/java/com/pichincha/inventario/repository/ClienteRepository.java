/**
 * 
 */
package com.pichincha.inventario.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.inventario.entity.Cliente;

/**
 * @author Christian Muyon
 *
 */
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	List<Cliente> findByIdentificacion(String identificacion);

}
